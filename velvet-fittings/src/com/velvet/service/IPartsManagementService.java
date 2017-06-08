package com.velvet.service;

import java.util.List;
import java.util.Map;

import com.velvet.vo.Bill;
import com.velvet.vo.NameValuePairBean;
import com.velvet.vo.PartAvailabilityBean;
import com.velvet.vo.Parts;
import com.velvet.vo.PartsSearchBean;
import com.velvet.vo.PartsSearchResultsBean;

public interface IPartsManagementService {

	public void addParts(Parts parts) throws Exception;
	
	public List<NameValuePairBean> getCategoryList() throws Exception;
	
	public List<NameValuePairBean> getPartList() throws Exception;
	
	public Parts getPartsInfo(String partsId) throws Exception;
	
	public int submitAddDeleteParts(Parts parts,String updatedQnty,String action,String date) throws Exception;
	
	public List<NameValuePairBean> getPartListByCategory(String categoryId) throws Exception;
	
	public List<NameValuePairBean> getPartListByPattern(String patternId,String categoryId) throws Exception;
	
	public List<NameValuePairBean> getOrderList() throws Exception;
	
	public Bill getOrdersInfo(String orderId) throws Exception;
	
	public Bill insertBillDetails(Bill bill) throws Exception;
	
	public Bill getBillInfo(String billId) throws Exception;
	
	public Map<String, String> fetchItemOrderQuantityMap(String orderId) throws Exception;
	
	public void placeOrderForItems(String orderId, Map<String, PartAvailabilityBean> partAvailabilityMap) throws Exception;
	
	public List<PartsSearchResultsBean> getPartsSearchResults(PartsSearchBean partBean) throws Exception;
	
	public List<PartsSearchResultsBean> getPartsThresholdResults(PartsSearchBean partBean) throws Exception;
}
