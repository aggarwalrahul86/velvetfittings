package com.velvet.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.velvet.annotations.ReadableTransaction;
import com.velvet.annotations.WritableTransaction;
import com.velvet.dao.IItemsManagementDao;
import com.velvet.vo.ItemOrderBean;
import com.velvet.vo.ItemPartMappingBean;
import com.velvet.vo.ManageItemOrderBean;
import com.velvet.vo.NameValuePairBean;
import com.velvet.vo.OrderBean;
import com.velvet.vo.OrderSearchBean;
import com.velvet.vo.OrderSearchResultBean;
import com.velvet.vo.PartAvailabilityBean;
import com.velvet.vo.Parts;

@Service("itemsManagementServiceImpl")
public class ItemsManagementServiceImpl implements IItemsManagementService {
	
	@Autowired
	@Qualifier("ItemsManagementDao")
	IItemsManagementDao itemsDao = null;
	
	
	@ReadableTransaction
	public List<NameValuePairBean> getCategoryList() throws Exception{
		 return itemsDao.getCategoryList();
	}
	
	@ReadableTransaction
	public List<NameValuePairBean> getItemList(String category, String itemNamePattern) throws Exception{
		 return itemsDao.getItemList(category, itemNamePattern);
	}
	
	@ReadableTransaction
	public Map<String, Parts> getPartsInfoMapForItems(String itemIdCSV) throws Exception{
		 return itemsDao.getPartsInfoMapForItems(itemIdCSV);
	}
	
	@ReadableTransaction
	public Map<String, Map<String, ItemPartMappingBean>> getItemPartMappingInfoMapForItems(String itemIdCSV) throws Exception{
		 return itemsDao.getItemPartMappingInfoMapForItems(itemIdCSV);
	}
	
	@WritableTransaction
	public OrderBean placeOrderForItems(ManageItemOrderBean manageItemOrderBean, Map<String, String> itemOrderQuantityMap, Map<String, PartAvailabilityBean> partAvailabilityMap) throws Exception{
		 return itemsDao.placeOrderForItems(manageItemOrderBean, itemOrderQuantityMap, partAvailabilityMap);
	}
	
	@ReadableTransaction
	public List<OrderSearchResultBean> getOrderSearchResults(OrderSearchBean orderSearchBean) throws Exception{
		 return itemsDao.getOrderSearchResults(orderSearchBean);
	}
	
	@ReadableTransaction
	public List<ItemOrderBean> getItemDetailsForOrderId(String orderId) throws Exception{
		 return itemsDao.getItemDetailsForOrderId(orderId);
	}
	
	@ReadableTransaction
	public ManageItemOrderBean getOrderDetailsForOrderId(String orderId, ManageItemOrderBean manageItemOrderBean) throws Exception{
		 return itemsDao.getOrderDetailsForOrderId(orderId, manageItemOrderBean);
	}
	
	@ReadableTransaction
	public List<ItemOrderBean> getItemOrderDetailsForOrderId(String orderId) throws Exception{
		 return itemsDao.getItemOrderDetailsForOrderId(orderId);
	}
	
	@WritableTransaction
	public OrderBean updateOrderForItems(String orderId, ManageItemOrderBean manageItemOrderBean, Map<String, String> itemOrderQuantityMap, 
			Map<String, PartAvailabilityBean> partAvailabilityMap, Map<String, PartAvailabilityBean> unusedPartsInUpdatedOrderMap) throws Exception{
		 return itemsDao.updateOrderForItems(orderId, manageItemOrderBean, itemOrderQuantityMap, partAvailabilityMap, unusedPartsInUpdatedOrderMap);
	}
	
	@ReadableTransaction
	public List<NameValuePairBean> getClientList(String clientNamePattern) throws Exception{
		 return itemsDao.getClientList(clientNamePattern);
	}

}
