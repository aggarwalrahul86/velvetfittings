package com.velvet.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.velvet.utils.StringUtils;
import com.velvet.vo.ItemOrderBean;
import com.velvet.vo.ItemPartMappingBean;
import com.velvet.vo.ManageItemOrderBean;
import com.velvet.vo.NameValuePairBean;
import com.velvet.vo.OrderBean;
import com.velvet.vo.OrderSearchBean;
import com.velvet.vo.OrderSearchResultBean;
import com.velvet.vo.PartAvailabilityBean;
import com.velvet.vo.Parts;

@Repository("ItemsManagementDao")
public class ItemsManagementDao implements IItemsManagementDao{
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate = null;
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<NameValuePairBean> getCategoryList() throws Exception{
		// TODO Auto-generated method stub
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(GET_CATEGORY_QUERY);
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
	public List<NameValuePairBean> getItemList(String category, String itemNamePattern) throws Exception{
		// TODO Auto-generated method stub
		String queryString = GET_ITEMS_QUERY.replaceFirst(ITEM_NAME_PATTERN, itemNamePattern);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(queryString).setParameter("category", category);
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
	
	@Override
	public List<NameValuePairBean> getClientList(String clientNamePattern) throws Exception{
		// TODO Auto-generated method stub
		String queryString = GET_CLIENTS_QUERY.replaceFirst(CLIENT_NAME_PATTERN, clientNamePattern);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(queryString);
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
	
	public Map<String, Parts> getPartsInfoMapForItems(String itemIdCSV) throws Exception{
		// TODO Auto-generated method stub
		
		StringBuilder queryStr = new StringBuilder().append(" select tpm.part_sl_no, concat(tpm.part_name, ' (', tpcm.category_name, ')') as part_name, ")
				.append(" tpm.part_code, tpm.part_quantity, tpm.part_threshold, tpm.category_sl_no ")
				.append(" from tbl_part_master tpm, tbl_part_category_master tpcm ")
				.append(" where tpm.logical_delete='N' ")
				.append(" and tpm.category_sl_no = tpcm.category_sl_no ")
				.append(" and tpm.part_sl_no in ( select part_sl_no from tbl_item_part_mapping where item_sl_no in (").append(itemIdCSV).append(")) ");
			
		Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr.toString())
				.addEntity(Parts.class);
		List<Object> objectList = query.list();
		Map<String, Parts> partsInfoMap = new HashMap<String, Parts>();
		if(objectList!=null && objectList.size() > 0){
			for(Object partObj : objectList){
				partsInfoMap.put(((Parts)partObj).getPartsId(), (Parts)partObj);
			}
		}
		return partsInfoMap;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}
	
	
	public Map<String, Map<String, ItemPartMappingBean>> getItemPartMappingInfoMapForItems(String itemIdCSV) throws Exception{
		// TODO Auto-generated method stub
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery("select * from tbl_item_part_mapping where logical_delete='N' and item_sl_no in ("+itemIdCSV+")")
				.addEntity(ItemPartMappingBean.class);
		List<Object> objectList = query.list();
		Map<String, Map<String, ItemPartMappingBean>> itemPartMappingMap = new HashMap<String, Map<String, ItemPartMappingBean>>();
		if(objectList!=null && objectList.size() > 0){
			for(Object itemPartMappingObj : objectList){
				if(itemPartMappingMap.get(((ItemPartMappingBean)itemPartMappingObj).getItemId())==null){
					itemPartMappingMap.put(((ItemPartMappingBean)itemPartMappingObj).getItemId(), new HashMap<String, ItemPartMappingBean>());
				}
				itemPartMappingMap.get(((ItemPartMappingBean)itemPartMappingObj).getItemId()).put(((ItemPartMappingBean)itemPartMappingObj).getPartId(), (ItemPartMappingBean)itemPartMappingObj);
			}
		}
		return itemPartMappingMap;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}
	
	public OrderBean placeOrderForItems(ManageItemOrderBean manageItemOrderBean, Map<String, String> itemOrderQuantityMap, Map<String, PartAvailabilityBean> partAvailabilityMap) throws Exception{
		
		String currentDateStr = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date());
		OrderBean orderBean = new OrderBean(null, manageItemOrderBean.getClientName(),manageItemOrderBean.getOrderDate(), 
				"admin", currentDateStr, "admin", currentDateStr, "N");
		
		OrderBean insrtdOrderBean = (OrderBean)sessionFactory.getCurrentSession().merge(orderBean);
		
		for (Iterator<String> itemOrderQuantityMapItr = itemOrderQuantityMap.keySet().iterator(); itemOrderQuantityMapItr.hasNext();) {
			String itemId = itemOrderQuantityMapItr.next();
			Query insertQuery = sessionFactory.getCurrentSession().createSQLQuery(INSERT_ITEM_TRANSACTION_QUERY);
			insertQuery.setParameter("item_sl_no", itemId);
			insertQuery.setParameter("order_sl_no", insrtdOrderBean.getOrderId());
			insertQuery.setParameter("item_quantity", itemOrderQuantityMap.get(itemId));
			int insertRetVal = insertQuery.executeUpdate();
			System.out.println("inserted in tbl_order -- : "+itemId+" : "+insrtdOrderBean.getOrderId()+" : "+itemOrderQuantityMap.get(itemId)); 
		}
		
		for (Iterator<String> partAvailabilityMapItr = partAvailabilityMap.keySet().iterator(); partAvailabilityMapItr.hasNext();) {
			PartAvailabilityBean partAvailabilityBean = partAvailabilityMap.get(partAvailabilityMapItr.next());
			Query updateQuery = sessionFactory.getCurrentSession().createSQLQuery(SQL_UPDATE_PARTS_QUANTITY);
			updateQuery.setParameter("partId", partAvailabilityBean.getPartId());
			updateQuery.setParameter("partQnty",String.valueOf(Integer.parseInt(partAvailabilityBean.getCurrentQuantity()) - Integer.parseInt(partAvailabilityBean.getRequiredQuantity())));
			int retVal = updateQuery.executeUpdate();
			System.out.println("updated partId : "+partAvailabilityBean.getPartId()+" : with qty :"+
					String.valueOf(Integer.parseInt(partAvailabilityBean.getCurrentQuantity()) - Integer.parseInt(partAvailabilityBean.getRequiredQuantity())));
		}
		
		return insrtdOrderBean;
	}
	
	@Override
	public List<OrderSearchResultBean> getOrderSearchResults(OrderSearchBean orderSearchBean) throws Exception{
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder()
			.append(" select tordr.order_sl_no, DATE_FORMAT(tordr.order_date,'%d-%m-%Y') as order_date, tordr.client_name, concat(tordr.client_name, '_', tordr.order_sl_no) as order_name, tbll.bill_sl_no, DATE_FORMAT(tbll.bill_date,'%d-%m-%Y') as bill_date ")
			.append(" from tbl_order tordr left join tbl_bill tbll on tordr.order_sl_no = tbll.order_sl_no where tordr.logical_delete = 'N' ");
		
		if(!StringUtils.isEmptyDrpDwn(orderSearchBean.getItem())){
			queryStr.append(" and tordr.order_sl_no in (select order_sl_no from tbl_item_transaction where item_sl_no = ").append(orderSearchBean.getItem()).append(") ");
		}
		if(!StringUtils.isEmptyDrpDwn(orderSearchBean.getClientName())){
			queryStr.append(" and tordr.client_name =  '").append(orderSearchBean.getClientName()).append("'");
		}
		if(!StringUtils.IsEmpty(orderSearchBean.getOrderFromDate())){
			queryStr.append(" and DATE(tordr.order_date) >= '").append(orderSearchBean.getOrderFromDate()).append("'");
		}
		if(!StringUtils.IsEmpty(orderSearchBean.getOrderToDate())){
			queryStr.append(" and DATE(tordr.order_date) <= '").append(orderSearchBean.getOrderToDate()).append("'");
		}
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr.toString());
		
		List<Object> objectList = query.list();
		Iterator<Object> iterator = objectList.iterator();
		List<OrderSearchResultBean> OrderSearchResultBeanList = new ArrayList<OrderSearchResultBean>();
		while(iterator.hasNext()){ 
			OrderSearchResultBean orderSearchResultBean = new OrderSearchResultBean();
			Object[] obj = (Object[]) iterator.next();
			orderSearchResultBean.setOrderId(String.valueOf(obj[0]));
			orderSearchResultBean.setOrderDate(String.valueOf(obj[1]));
			orderSearchResultBean.setClientName(String.valueOf(obj[2]));
			orderSearchResultBean.setOrderName(String.valueOf(obj[3]));
			orderSearchResultBean.setBillId(obj[4]==null ? "" : String.valueOf(obj[4]));
			orderSearchResultBean.setBillDate(obj[5]==null ? "" : String.valueOf(obj[5]));
			OrderSearchResultBeanList.add(orderSearchResultBean);
		}
		return OrderSearchResultBeanList;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}
	
	@Override
	public List<ItemOrderBean> getItemDetailsForOrderId(String orderId) throws Exception{
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder()
			.append(" select item_sl_no, item_quantity from tbl_item_transaction where order_sl_no = :orderId order by item_transaction_sl_no ");
		
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr.toString()).setParameter("orderId", orderId);;
		
		List<Object> objectList = query.list();
		Iterator<Object> iterator = objectList.iterator();
		List<ItemOrderBean> itemOrderBeanList = new ArrayList<ItemOrderBean>();
		while(iterator.hasNext()){ 
			ItemOrderBean itemOrderBean = new ItemOrderBean();
			Object[] obj = (Object[]) iterator.next();
			itemOrderBean.setItem(String.valueOf(obj[0]));
			itemOrderBean.setQuantity(String.valueOf(obj[1]));
			itemOrderBeanList.add(itemOrderBean);
		}
		return itemOrderBeanList;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}
	
	@Override
	public ManageItemOrderBean getOrderDetailsForOrderId(String orderId, ManageItemOrderBean manageItemOrderBean) throws Exception{
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder()
			.append(" select client_name, date(order_date) as order_date from tbl_order where order_sl_no = :orderId ");
		
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr.toString()).setParameter("orderId", orderId);;
		
		List<Object> objectList = query.list();
		Iterator<Object> iterator = objectList.iterator();
		while(iterator.hasNext()){ 
			Object[] obj = (Object[]) iterator.next();
			manageItemOrderBean.setClientName(String.valueOf(obj[0]));
			manageItemOrderBean.setOrderDate(String.valueOf(obj[1]));
		}
		return manageItemOrderBean;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}
	
	@Override
	public List<ItemOrderBean> getItemOrderDetailsForOrderId(String orderId) throws Exception{
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder()
			.append(" select item_sl_no, item_quantity from tbl_item_transaction where order_sl_no = :orderId ");
		
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr.toString()).setParameter("orderId", orderId);;
		
		List<Object> objectList = query.list();
		List<ItemOrderBean> itemOrderListForOrder = new ArrayList<ItemOrderBean>();
		Iterator<Object> iterator = objectList.iterator();
		while(iterator.hasNext()){ 
			Object[] obj = (Object[]) iterator.next();
			ItemOrderBean itemOrderBean = new ItemOrderBean();
			itemOrderBean.setItem(String.valueOf(obj[0]));
			itemOrderBean.setQuantity(String.valueOf(obj[1]));
			itemOrderListForOrder.add(itemOrderBean);
		}
		return itemOrderListForOrder;
	//	System.out.println("The parts key returned is "+parts.getPartsId());		
	}

	public OrderBean updateOrderForItems(String orderId, ManageItemOrderBean manageItemOrderBean, Map<String, String> itemOrderQuantityMap, 
			Map<String, PartAvailabilityBean> partAvailabilityMap, Map<String, PartAvailabilityBean> unusedPartsInUpdatedOrderMap) throws Exception{
		
		String currentDateStr = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date());
		OrderBean orderBean = new OrderBean(orderId, manageItemOrderBean.getClientName(),manageItemOrderBean.getOrderDate(), 
				"admin", currentDateStr, "admin", currentDateStr, "N");
		
		OrderBean updtdOrderBean = (OrderBean)sessionFactory.getCurrentSession().merge(orderBean);
		
		Query deleteStmt = sessionFactory.getCurrentSession().createSQLQuery(DELETE_ITEM_TRANSACTION_FOR_ORDER);
		deleteStmt.setParameter("orderSlNo", updtdOrderBean.getOrderId());
		int deleteRetVal = deleteStmt.executeUpdate();
		
		for (Iterator<String> itemOrderQuantityMapItr = itemOrderQuantityMap.keySet().iterator(); itemOrderQuantityMapItr.hasNext();) {
			String itemId = itemOrderQuantityMapItr.next();
			Query insertQuery = sessionFactory.getCurrentSession().createSQLQuery(INSERT_ITEM_TRANSACTION_QUERY);
			insertQuery.setParameter("item_sl_no", itemId);
			insertQuery.setParameter("order_sl_no", updtdOrderBean.getOrderId());
			insertQuery.setParameter("item_quantity", itemOrderQuantityMap.get(itemId));
			int insertRetVal = insertQuery.executeUpdate();
			System.out.println("inserted in tbl_order -- : "+itemId+" : "+updtdOrderBean.getOrderId()+" : "+itemOrderQuantityMap.get(itemId)); 
		}
		
		for (Iterator<String> partAvailabilityMapItr = partAvailabilityMap.keySet().iterator(); partAvailabilityMapItr.hasNext();) {
			PartAvailabilityBean partAvailabilityBean = partAvailabilityMap.get(partAvailabilityMapItr.next());
			Query updateQuery = sessionFactory.getCurrentSession().createSQLQuery(SQL_UPDATE_PARTS_QUANTITY);
			updateQuery.setParameter("partId", partAvailabilityBean.getPartId());
			updateQuery.setParameter("partQnty",String.valueOf(Integer.parseInt(partAvailabilityBean.getCurrentQuantity()) - Integer.parseInt(partAvailabilityBean.getRequiredQuantity())));
			int retVal = updateQuery.executeUpdate();
			System.out.println("updated partId : "+partAvailabilityBean.getPartId()+" : with qty :"+
					String.valueOf(Integer.parseInt(partAvailabilityBean.getCurrentQuantity()) - Integer.parseInt(partAvailabilityBean.getRequiredQuantity())));
		}
		
		for (Iterator<String> unusedPartsInUpdatedOrderMapItr = unusedPartsInUpdatedOrderMap.keySet().iterator(); unusedPartsInUpdatedOrderMapItr.hasNext();) {
			PartAvailabilityBean partAvailabilityBean = unusedPartsInUpdatedOrderMap.get(unusedPartsInUpdatedOrderMapItr.next());
			Query updateQuery = sessionFactory.getCurrentSession().createSQLQuery(SQL_UPDATE_PARTS_QUANTITY);
			updateQuery.setParameter("partId", partAvailabilityBean.getPartId());
			updateQuery.setParameter("partQnty",String.valueOf(Integer.parseInt(partAvailabilityBean.getCurrentQuantity())));
			int retVal = updateQuery.executeUpdate();
			System.out.println("updated partId : "+partAvailabilityBean.getPartId()+" : with qty :"+
					String.valueOf(Integer.parseInt(partAvailabilityBean.getCurrentQuantity())));
		}
		
		return updtdOrderBean;
	}

	
}
