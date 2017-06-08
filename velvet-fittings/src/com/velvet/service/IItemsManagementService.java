package com.velvet.service;

import java.util.List;
import java.util.Map;

import com.velvet.vo.ItemOrderBean;
import com.velvet.vo.ItemPartMappingBean;
import com.velvet.vo.ManageItemOrderBean;
import com.velvet.vo.NameValuePairBean;
import com.velvet.vo.OrderBean;
import com.velvet.vo.OrderSearchBean;
import com.velvet.vo.OrderSearchResultBean;
import com.velvet.vo.PartAvailabilityBean;
import com.velvet.vo.Parts;

public interface IItemsManagementService {

	public List<NameValuePairBean> getCategoryList() throws Exception;
	
	public List<NameValuePairBean> getItemList(String category, String itemNamePattern) throws Exception;
	
	public Map<String, Parts> getPartsInfoMapForItems(String itemIdCSV) throws Exception;
	
	public Map<String, Map<String, ItemPartMappingBean>> getItemPartMappingInfoMapForItems(String itemIdCSV) throws Exception;
	
	public OrderBean placeOrderForItems(ManageItemOrderBean manageItemOrderBean, Map<String, String> itemOrderQuantityMap, Map<String, PartAvailabilityBean> partAvailabilityMap) throws Exception;
	
	public List<OrderSearchResultBean> getOrderSearchResults(OrderSearchBean orderSearchBean) throws Exception;
	
	public List<ItemOrderBean> getItemDetailsForOrderId(String orderId) throws Exception;
	
	public ManageItemOrderBean getOrderDetailsForOrderId(String orderId, ManageItemOrderBean manageItemOrderBean) throws Exception;
	
	public List<ItemOrderBean> getItemOrderDetailsForOrderId(String orderId) throws Exception;
	
	public OrderBean updateOrderForItems(String orderId, ManageItemOrderBean manageItemOrderBean, Map<String, String> itemOrderQuantityMap, 
			Map<String, PartAvailabilityBean> partAvailabilityMap, Map<String, PartAvailabilityBean> unusedPartsInUpdatedOrderMap) throws Exception;
	
	public List<NameValuePairBean> getClientList(String clientNamePattern) throws Exception;
}
