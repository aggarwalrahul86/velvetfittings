package com.velvet.dao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.velvet.constants.IApplicationConstants;
import com.velvet.vo.Bill;
import com.velvet.vo.ItemsTransaction;
import com.velvet.vo.NameValuePairBean;
import com.velvet.vo.Order;
import com.velvet.vo.PartAvailabilityBean;
import com.velvet.vo.Parts;
import com.velvet.vo.PartsSearchBean;
import com.velvet.vo.PartsSearchResultsBean;

@Repository("PartsManagementDao")
public class PartsManagementDao implements IPartsManagementDao{
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate = null;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addParts(Parts parts) throws Exception{
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().saveOrUpdate(parts);
		System.out.println("The parts key returned is "+parts.getPartsId());
		
/*		 KeyHolder keyHolder = null;
		 keyHolder = new GeneratedKeyHolder();
		 String partsKey = "";
		final String insertPartQuery = INSERT_PARTS_QUERY;

			
		jdbcTemplate.update(new PreparedStatementCreator() {
          	  public PreparedStatement createPreparedStatement(Connection connection)
          	      throws SQLException {
          		PreparedStatement statement = connection.prepareStatement(insertPartQuery,
   					Statement.RETURN_GENERATED_KEYS);
   			statement.setString(1, parts.getName());
   			statement.setString(2, parts.getCode());
   			statement.setString(3, parts.getThreshold());
   			statement.setString(4, parts.getQuantity());
   			return statement;
          	  }
          	},keyHolder);
		 
		    partsKey = String.valueOf(keyHolder.getKey());
		    throw new Exception();*/

	
		
	}
	@Override
	public List<NameValuePairBean> getCategoryList() throws Exception{
		// TODO Auto-generated method stub
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(GET_PARTS_CATEGORY_QUERY);
		List<Object> objectList = query.list();
		Iterator iterator = objectList.iterator();
		List<NameValuePairBean> categoryList = new ArrayList<NameValuePairBean>();
		NameValuePairBean category = null;
		while(iterator.hasNext()){ 
		category = new NameValuePairBean();
		Object []obj = (Object[])iterator.next();
		category.setCode(String.valueOf(obj[0]));
		category.setDesc(String.valueOf(obj[1]));
		category.setName(String.valueOf(obj[2]));
		categoryList.add(category);
		}
		return categoryList;
	}
	
	@Override
	public List<NameValuePairBean> getPartList() throws Exception{
		// TODO Auto-generated method stub
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(GET_PARTS_QUERY);
		List<Object> objectList = query.list();
		Iterator iterator = objectList.iterator();
		List<NameValuePairBean> partList = new ArrayList<NameValuePairBean>();
		NameValuePairBean part = null;
		while(iterator.hasNext()){ 
		part = new NameValuePairBean();
		Object []obj = (Object[])iterator.next();
		part.setCode(String.valueOf(obj[0]));
		part.setDesc(String.valueOf(obj[1]));
		part.setName(String.valueOf(obj[2]));
		partList.add(part);
		}
		return partList;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}
	
	public Parts getPartsInfo(String partsId) throws Exception{
		// TODO Auto-generated method stub
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(SQL_GET_PARTS_INFO)
				.addEntity(Parts.class).setParameter("partId", partsId);
		List<Object> objectList = query.list();
		Parts part = null;
		if(objectList!=null && objectList.size() > 0){
			part = (Parts)objectList.get(0);
		}
		return part;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}
	
	public int getPartsQnty(Parts parts) throws Exception{
		Query query = sessionFactory.getCurrentSession().createSQLQuery(SQL_GET_PARTS_QNTY_INFO).setParameter("partId", parts.getPartsId());
		List<Object> objectList = query.list();
		Iterator iterator = objectList.iterator();
		int quantity = 0;
		while(iterator.hasNext()){ 
		Object obj = (Object)iterator.next();
		quantity = Integer.parseInt(String.valueOf(obj));
		}
		return quantity;
	}
	
	public int submitAddDeleteParts(Parts parts,String action,String updatedQnty,String date) throws Exception{
		int netQty = 0;
		int quantity = getPartsQnty(parts);
		int retVal = 0;
		if(!StringUtils.isBlank(action) && "ADD".equalsIgnoreCase(action)){
			netQty = Integer.parseInt(updatedQnty) + quantity;
		}else if(!StringUtils.isBlank(action) && "DELETE".equalsIgnoreCase(action)){
			netQty = quantity - Integer.parseInt(updatedQnty);
		}
		if(netQty > 0 || (!StringUtils.isBlank(action) && "DELETE".equalsIgnoreCase(action) && netQty ==0)){
			Query updateQuery = sessionFactory.getCurrentSession().createSQLQuery(SQL_UPDATE_PARTS_QUANTITY);
			updateQuery.setParameter("partId", parts.getPartsId());
			updateQuery.setParameter("partQnty",netQty);
			retVal = updateQuery.executeUpdate();
			if(retVal > 0){
				Query insertQuery = sessionFactory.getCurrentSession().createSQLQuery(INSERT_PARTS_STAG_QUERY);
				insertQuery.setParameter("partId", parts.getPartsId());
				insertQuery.setParameter("partQnty",updatedQnty);
				insertQuery.setParameter("created_on",date);
				insertQuery.setParameter("operation",action);
				insertQuery.setParameter("threshold",parts.getThreshold());
				int retValue = insertQuery.executeUpdate();	
			}
		}else{
			retVal = 2;
		}
		return retVal;
	}
	
	@Override
	public List<NameValuePairBean> getPartListByCategory(String categoryId) throws Exception{
		// TODO Auto-generated method stub
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(GET_PARTS_QUERY_BY_CATEGORY).setParameter("categoryId",categoryId);
		List<Object> objectList = query.list();
		Iterator iterator = objectList.iterator();
		List<NameValuePairBean> partList = new ArrayList<NameValuePairBean>();
		NameValuePairBean part = null;
		while(iterator.hasNext()){ 
		part = new NameValuePairBean();
		Object []obj = (Object[])iterator.next();
		part.setCode(String.valueOf(obj[0]));
		part.setDesc(String.valueOf(obj[1]));
		part.setName(String.valueOf(obj[2]));
		partList.add(part);
		}
		return partList;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}
	
	public List<NameValuePairBean> getPartListByPattern(String patternId,String categoryId) throws Exception{
		// TODO Auto-generated method stub
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(GET_PARTS_QUERY_BY_PATTERN.replace(IApplicationConstants.PART_PATTERN_STRING,patternId ));
		query.setParameter("categoryId", categoryId);
		List<Object> objectList = query.list();
		Iterator iterator = objectList.iterator();
		List<NameValuePairBean> partList = new ArrayList<NameValuePairBean>();
		NameValuePairBean part = null;
		while(iterator.hasNext()){ 
		part = new NameValuePairBean();
		Object []obj = (Object[])iterator.next();
		part.setCode(String.valueOf(obj[0]));
		part.setDesc(String.valueOf(obj[1]));
		part.setName(String.valueOf(obj[2]));
		partList.add(part);
		}
		return partList;
	}
	
	public List<NameValuePairBean> getOrderList() throws Exception{
		Query query = sessionFactory.getCurrentSession().createSQLQuery(GET_ORDERS_LIST);
		List<Object> objectList = query.list();
		Iterator iterator = objectList.iterator();
		List<NameValuePairBean> orderList = new ArrayList<NameValuePairBean>();
		NameValuePairBean order = null;
		while(iterator.hasNext()){ 
		order = new NameValuePairBean();
		Object []obj = (Object[])iterator.next();
		order.setName(String.valueOf(obj[0]));
		order.setCode(String.valueOf(obj[1]));
		orderList.add(order);
		}
		return orderList;
	}
	
	public Map<String, String> fetchItemOrderQuantityMap(String orderId) throws Exception{
		// TODO Auto-generated method stub
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(GET_ORDER_INFO).setParameter("orderId", orderId);
		List<Object> objectList = query.list();
		Map<String, String> itemOrderQuantityMap = new HashMap<String, String>();
		
		if(objectList!=null && objectList.size() > 0){
			Iterator iterator = objectList.iterator();
			while(iterator.hasNext()){ 
				Object []obj = (Object[])iterator.next();
				itemOrderQuantityMap.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
			}
		}

		return itemOrderQuantityMap;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}
	
	public Order getOrdersInfo(String orderId) throws Exception{
		// TODO Auto-generated method stub
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(GET_ORDER_INFO).setParameter("orderId", orderId);
		List<Object> objectList = query.list();
		Order order = null;
		
		if(objectList!=null && objectList.size() > 0){
	
			List<ItemsTransaction> itemList = new ArrayList<ItemsTransaction>();
			ItemsTransaction itemTrans = null;;
			Iterator iterator = objectList.iterator();
			while(iterator.hasNext()){ 
				order = new Order();
				itemTrans = new ItemsTransaction();
				Object []obj = (Object[])iterator.next();
				itemTrans.setItemid(String.valueOf(obj[0]));
				itemTrans.setTransactedQnty(String.valueOf(obj[1]));
				itemTrans.setItemCode(String.valueOf(obj[2]));
				itemTrans.setItemPrice(String.valueOf(obj[3]));
				itemTrans.setItemName(String.valueOf(obj[4]));
				itemTrans.setTransactedPrice(String.valueOf(Float.parseFloat(itemTrans.getItemPrice())*Float.parseFloat(itemTrans.getTransactedQnty())));
				order.setClientName(String.valueOf(obj[5]));
				order.setOrderDate(String.valueOf(obj[6]));
				itemList.add(itemTrans);
				}
			order.setOrderId(orderId);
			order.setItemsList(itemList);
		}

		return order;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}
	
	public Bill insertBillDetails(Bill bill) throws Exception{
		int retVal = 0;
				Query insertQuery = sessionFactory.getCurrentSession().createSQLQuery(INSERT_BILL_DETAILS);
				insertQuery.setParameter("orderId", bill.getOrder().getOrderId());
				insertQuery.setParameter("billDate", bill.getBillDate());
				insertQuery.setParameter("totalAmnt",bill.getTotalAmnt());
				insertQuery.setParameter("taxAmount",bill.getTaxAmnt());
				insertQuery.setParameter("discAmount",bill.getDscntAmnt());
				int retValue = insertQuery.executeUpdate();	
				if(retValue > 0){
					Query query = sessionFactory.getCurrentSession().createSQLQuery(GET_BILL_ID).setParameter("orderId", bill.getOrder().getOrderId());
					List<Object> objectList = query.list();
					Iterator iterator = objectList.iterator();
					while(iterator.hasNext()){ 
					Object obj = (Object)iterator.next();
					bill.setBillNo(String.valueOf(obj));
					}
					Query updateQuery = sessionFactory.getCurrentSession().createSQLQuery(SQL_UPDATE_ORDER_BILL_FLAG);
					updateQuery.setParameter("orderId", bill.getOrder().getOrderId());
					retVal = updateQuery.executeUpdate();
				}

		return bill;
	}
	
	
	public Bill getBillInfo(String billId) throws Exception{
		// TODO Auto-generated method stub
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(SQL_GET_BILL_DETAILS).setParameter("billId", billId);
		List<Object> objectList = query.list();
		Bill bill = null;
		Order order = null;
		String orderId = "";
		float discountPerc;
		float totalAmount;
		double discountAmount;
		NumberFormat formatter = new DecimalFormat("#0.00");
		if(objectList!=null && objectList.size() > 0){
			
			Iterator iterator = objectList.iterator();
			while(iterator.hasNext()){ 
				bill = new Bill();
				
				Object []obj = (Object[])iterator.next();
				bill.setBillNo(String.valueOf(obj[0]));
				orderId = String.valueOf(obj[1]);
				discountPerc = Float.parseFloat(com.velvet.utils.StringUtils.IsEmpty(String.valueOf(obj[5])) ? "0" : String.valueOf(obj[5]));
				totalAmount = Float.parseFloat(com.velvet.utils.StringUtils.IsEmpty(String.valueOf(obj[3])) ? "0" : String.valueOf(obj[3]));
				discountAmount = 0.01*discountPerc*totalAmount;
				bill.setBillDate(String.valueOf(obj[2]));
				bill.setTotalAmnt(formatter.format(totalAmount));
				bill.setTaxAmnt(formatter.format(Float.parseFloat(com.velvet.utils.StringUtils.IsEmpty(String.valueOf(obj[4])) ? "0" : String.valueOf(obj[4]))));
				bill.setDscntAmnt(formatter.format(discountPerc));
				bill.setDiscountAmount(formatter.format(discountAmount));
			}
		}
		order = getOrdersInfo(orderId);
		bill.setOrder(order);
		return bill;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}
	
	public void placeOrderForItems(String orderId, Map<String, PartAvailabilityBean> partAvailabilityMap) throws Exception{
		
		Query cancelOrderQuery = sessionFactory.getCurrentSession().createSQLQuery(SQL_CANCEL_ORDER);
		cancelOrderQuery.setParameter("orderId", orderId);
		int retVal = cancelOrderQuery.executeUpdate();
		
		for (Iterator<String> partAvailabilityMapItr = partAvailabilityMap.keySet().iterator(); partAvailabilityMapItr.hasNext();) {
			PartAvailabilityBean partAvailabilityBean = partAvailabilityMap.get(partAvailabilityMapItr.next());
			Query updateQuery = sessionFactory.getCurrentSession().createSQLQuery(SQL_UPDATE_PARTS_QUANTITY);
			updateQuery.setParameter("partId", partAvailabilityBean.getPartId());
			updateQuery.setParameter("partQnty",String.valueOf(Integer.parseInt(partAvailabilityBean.getCurrentQuantity())));
			int retUpdateVal = updateQuery.executeUpdate();
			System.out.println("updated partId : "+partAvailabilityBean.getPartId()+" : with qty :"+
					String.valueOf(Integer.parseInt(partAvailabilityBean.getCurrentQuantity())));
		}
		
		return;
	}
	
	@Override
	public List<PartsSearchResultsBean> getPartsSearchResults(PartsSearchBean partBean) throws Exception{
		// TODO Auto-generated method stub
		Parts parts = partBean.getParts();
		Query query = sessionFactory.getCurrentSession().createSQLQuery(SQL_PART_SEARCH_QUERY);
		query.setParameter("categoryId",(parts.getCategoryId()!=null && "-1".equals(parts.getCategoryId()) ) ? null:parts.getCategoryId());
		query.setParameter("categoryId", (parts.getCategoryId()!=null && "-1".equals(parts.getCategoryId()) ) ? null:parts.getCategoryId());
		query.setParameter("partId",(parts.getPartsId()!=null && "-1".equals(parts.getPartsId()) ) ? null:parts.getPartsId());
		query.setParameter("partId",(parts.getPartsId()!=null && "-1".equals(parts.getPartsId()) ) ? null:parts.getPartsId());
		query.setParameter("fromDate", (partBean.getFromDate()!=null && "".equals(partBean.getFromDate()) ) ? null:partBean.getFromDate());
		query.setParameter("fromDate", (partBean.getFromDate()!=null && "".equals(partBean.getFromDate()) ) ? null:partBean.getFromDate());
		query.setParameter("toDate", (partBean.getToDate()!=null && "".equals(partBean.getToDate()) ) ? null:partBean.getToDate());
		query.setParameter("toDate", (partBean.getToDate()!=null && "".equals(partBean.getToDate()) ) ? null:partBean.getToDate());
		List<Object> objectList = query.list();
		Iterator iterator = objectList.iterator();
		List<PartsSearchResultsBean> partResultList = new ArrayList<PartsSearchResultsBean>();
		PartsSearchResultsBean partResult = null;
		while(iterator.hasNext()){ 
		partResult = new PartsSearchResultsBean();
		Object []obj = (Object[])iterator.next();
		partResult.setCreatedOn(String.valueOf(obj[0]));
		partResult.setPartsName(String.valueOf(obj[1]));
		partResult.setPartsCode(String.valueOf(obj[2]));
		partResult.setPartsQuantity(String.valueOf(obj[3]));
		partResult.setOperation(String.valueOf(obj[4]));
	
		partResultList.add(partResult);
		}
		return partResultList;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}
	
	public List<PartsSearchResultsBean> getPartsThresholdResults(PartsSearchBean partBean) throws Exception{
		// TODO Auto-generated method stub
		Parts parts = partBean.getParts();
		Query query = sessionFactory.getCurrentSession().createSQLQuery(SQL_PART_THRESHOLD_SEARCH_QUERY);
		query.setParameter("categoryId",(parts.getCategoryId()!=null && "-1".equals(parts.getCategoryId()) ) ? null:parts.getCategoryId());
		query.setParameter("categoryId", (parts.getCategoryId()!=null && "-1".equals(parts.getCategoryId()) ) ? null:parts.getCategoryId());
		query.setParameter("partId",(parts.getPartsId()!=null && "-1".equals(parts.getPartsId()) ) ? null:parts.getPartsId());
		query.setParameter("partId",(parts.getPartsId()!=null && "-1".equals(parts.getPartsId()) ) ? null:parts.getPartsId());
		List<Object> objectList = query.list();
		Iterator iterator = objectList.iterator();
		List<PartsSearchResultsBean> partResultList = new ArrayList<PartsSearchResultsBean>();
		PartsSearchResultsBean partResult = null;
		while(iterator.hasNext()){ 
		partResult = new PartsSearchResultsBean();
		Object []obj = (Object[])iterator.next();
		partResult.setCreatedOn(String.valueOf(obj[0]));
		partResult.setPartsName(String.valueOf(obj[1]));
		partResult.setPartsCode(String.valueOf(obj[2]));
		partResult.setThresholdQuantity(String.valueOf(obj[3]));
		partResult.setPartsQuantity(String.valueOf(obj[4]));
		partResultList.add(partResult);
		}
		return partResultList;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}


}
