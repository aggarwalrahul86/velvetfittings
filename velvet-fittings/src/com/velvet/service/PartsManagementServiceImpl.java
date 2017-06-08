package com.velvet.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.velvet.annotations.ReadableTransaction;
import com.velvet.annotations.WritableTransaction;
import com.velvet.dao.IPartsManagementDao;
import com.velvet.vo.Bill;
import com.velvet.vo.NameValuePairBean;
import com.velvet.vo.PartAvailabilityBean;
import com.velvet.vo.Parts;
import com.velvet.vo.PartsSearchBean;
import com.velvet.vo.PartsSearchResultsBean;

@Service("partsManagementServiceImpl")
public class PartsManagementServiceImpl implements IPartsManagementService {
	
	@Autowired
	@Qualifier("PartsManagementDao")
	IPartsManagementDao partsDao = null;
	
	@WritableTransaction
	public void addParts(Parts parts) throws Exception{
		 partsDao.addParts(parts);
	}
	
	@ReadableTransaction
	public List<NameValuePairBean> getCategoryList() throws Exception{
		 return partsDao.getCategoryList();
	}
	
	@ReadableTransaction
	public List<NameValuePairBean> getPartList() throws Exception{
		 return partsDao.getPartList();
	}
	
	@ReadableTransaction
	public Parts getPartsInfo(String partsId) throws Exception{
		return partsDao.getPartsInfo(partsId);
	}
	@WritableTransaction
	public int submitAddDeleteParts(Parts parts,String updatedQnty,String action,String date) throws Exception{
		return partsDao.submitAddDeleteParts(parts,action,updatedQnty,date);
	}
	
	@ReadableTransaction
	public List<NameValuePairBean> getPartListByCategory(String categoryId) throws Exception{
		return partsDao.getPartListByCategory(categoryId);
	}
	
	@ReadableTransaction
	public List<NameValuePairBean> getPartListByPattern(String patternId,String categoryId) throws Exception{
		return partsDao.getPartListByPattern(patternId,categoryId);
	}
	@ReadableTransaction
	public List<NameValuePairBean> getOrderList() throws Exception{
		return partsDao.getOrderList();
	}
	
	@ReadableTransaction
	public Bill getOrdersInfo(String orderId) throws Exception{
		Bill bill = new Bill();
		bill.setOrder(partsDao.getOrdersInfo(orderId));
		return bill;
	}
	
	@WritableTransaction
	public Bill insertBillDetails(Bill bill) throws Exception{
		return partsDao.insertBillDetails(bill);
	}
	
	@ReadableTransaction
	public Bill getBillInfo(String billId) throws Exception{
		return partsDao.getBillInfo(billId);
	}
	
	@ReadableTransaction
	public Map<String, String> fetchItemOrderQuantityMap(String orderId) throws Exception{
		return partsDao.fetchItemOrderQuantityMap(orderId);
	}
	
	@WritableTransaction
	public void placeOrderForItems(String orderId, Map<String, PartAvailabilityBean> partAvailabilityMap) throws Exception{
		partsDao.placeOrderForItems(orderId, partAvailabilityMap);
		return ;
	}
	
	@ReadableTransaction
	public List<PartsSearchResultsBean> getPartsSearchResults(PartsSearchBean partBean) throws Exception{
		return partsDao.getPartsSearchResults(partBean);
	}
	
	@ReadableTransaction
	public List<PartsSearchResultsBean> getPartsThresholdResults(PartsSearchBean partBean) throws Exception{
		return partsDao.getPartsThresholdResults(partBean);
	}
	

}
