package com.velvet.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.velvet.constants.IActionConstants;
import com.velvet.constants.IMessageConstants;
import com.velvet.service.IItemsManagementService;
import com.velvet.utils.StringUtils;
import com.velvet.vo.ItemOrderBean;
import com.velvet.vo.ItemPartMappingBean;
import com.velvet.vo.ManageItemOrderBean;
import com.velvet.vo.MessageBean;
import com.velvet.vo.NameValuePairBean;
import com.velvet.vo.OrderBean;
import com.velvet.vo.OrderReportBean;
import com.velvet.vo.OrderSearchResultBean;
import com.velvet.vo.PartAvailabilityBean;
import com.velvet.vo.Parts;

@Controller
@RequestMapping("/items")
public class ItemsManagementController {
	
	/*
	 * Logger file to initialize logger
	 */
	private Logger logger = LoggerFactory
			.getLogger(ItemsManagementController.class);
	
	@Autowired
	@Qualifier("itemsManagementServiceImpl")
	private IItemsManagementService itemsService;


	@RequestMapping(value="/initManageItems" ,method = RequestMethod.GET)
	public String initManageItems(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside initManageItems"); 
		
		try {
				model.addAttribute("manageItemOrder", new ManageItemOrderBean());
			    model.addAttribute("categoryList", itemsService.getCategoryList());
			    model.addAttribute("itemList", itemsService.getItemList(null, ""));
			    model.addAttribute(IActionConstants.PAGE_IDENTIFIER, "MANAGE_ITMES");
			    model.addAttribute("contextPath", request.getContextPath());
			
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "manageItems";
	}
	
	
	@RequestMapping(value="/fetchItemsAutoSuggest")
	public @ResponseBody List<NameValuePairBean> fetchItemsAutoSuggest(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside fetchItemsAutoSuggest"); 
		List<NameValuePairBean> itemList = null;
		try {
				String category = !StringUtils.isEmptyDrpDwn(request.getParameter("category")) ? request.getParameter("category") : null;
				String itemNamePattern = request.getParameter("textName");
				itemList = itemsService.getItemList(category, itemNamePattern);
			
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemList;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/checkAvailabilityForItems")
	public String checkAvailabilityForItems(ModelMap model,HttpServletRequest request,HttpServletResponse response,ManageItemOrderBean manageItemOrderBean){
		logger.info("Inside checkAvailabilityForItems"); 
		try {
			
			HashMap<String, Object> availabiltyCheckerMap =  checkAvailabilityForItemsHelper(manageItemOrderBean);
			String orderAvailability = (String)availabiltyCheckerMap.get("orderAvailability");
			Map<String, PartAvailabilityBean> partAvailabilityMap = (Map<String, PartAvailabilityBean>)availabiltyCheckerMap.get("partAvailabilityMap");
			
			System.out.println("partAvailabilityMap ::::\n"+(Map<String, PartAvailabilityBean>)availabiltyCheckerMap.get("partAvailabilityMap"));
			
			model.addAttribute("orderAvailability", orderAvailability);
			model.addAttribute("partAvailabilityMap", partAvailabilityMap);
			model.addAttribute("manageItemOrder", manageItemOrderBean);
			model.addAttribute("categoryList", itemsService.getCategoryList());
		    model.addAttribute("itemList", itemsService.getItemList(null, ""));
		    model.addAttribute(IActionConstants.PAGE_IDENTIFIER, "MANAGE_ITMES");
		    model.addAttribute("contextPath", request.getContextPath());
		} catch (Exception e) {
			logger.error("Error In controller");
			model.addAttribute("msgObject", IMessageConstants.MSG_CHECK_AVAILABILITY_FAILED);
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try{
				model.addAttribute("orderAvailability", "N");
				model.addAttribute("manageItemOrder", manageItemOrderBean);
				model.addAttribute("categoryList", itemsService.getCategoryList());
			    model.addAttribute("itemList", itemsService.getItemList(null, ""));
			    model.addAttribute(IActionConstants.PAGE_IDENTIFIER, "MANAGE_ITMES");
			    model.addAttribute("contextPath", request.getContextPath());
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
		return "manageItems";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/placeOrderForItems")
	public String placeOrderForItems(ModelMap model,HttpServletRequest request,HttpServletResponse response,ManageItemOrderBean manageItemOrderBean){
		logger.info("Inside placeOrderForItems"); 
		try {
			
			HashMap<String, Object> availabiltyCheckerMap =  checkAvailabilityForItemsHelper(manageItemOrderBean);
			
			System.out.println("partAvailabilityMap ::::\n"+(Map<String, PartAvailabilityBean>)availabiltyCheckerMap.get("partAvailabilityMap"));
			
			String orderAvailability = (String)availabiltyCheckerMap.get("orderAvailability");
			Map<String, PartAvailabilityBean> partAvailabilityMap = (Map<String, PartAvailabilityBean>)availabiltyCheckerMap.get("partAvailabilityMap");
			Map<String, String> itemOrderQuantityMap = (Map<String, String>)availabiltyCheckerMap.get("itemOrderQuantityMap");
			
			if("Y".equals(orderAvailability)){
				OrderBean orderBean = itemsService.placeOrderForItems(manageItemOrderBean, itemOrderQuantityMap, partAvailabilityMap);
				model.addAttribute("msgObject", new MessageBean(MessageBean.CONFIRMATION,"Order generated successfully with Order Id : "+orderBean.getOrderId()));
				manageItemOrderBean = new ManageItemOrderBean();
			}else{
				model.addAttribute("partAvailabilityMap", partAvailabilityMap);
				model.addAttribute("msgObject", IMessageConstants.MSG_PLACE_OREDER_FAILED);
			}
			
			model.addAttribute("orderAvailability", orderAvailability);
			model.addAttribute("manageItemOrder", manageItemOrderBean);
			model.addAttribute("categoryList", itemsService.getCategoryList());
		    model.addAttribute("itemList", itemsService.getItemList(null, ""));
		    model.addAttribute(IActionConstants.PAGE_IDENTIFIER, "MANAGE_ITMES");
		    model.addAttribute("contextPath", request.getContextPath());
		} catch (Exception e) {
			logger.error("Error In controller");
			model.addAttribute("msgObject", IMessageConstants.MSG_CHECK_AVAILABILITY_FAILED);
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try{
				model.addAttribute("orderAvailability", "N");
				model.addAttribute("manageItemOrder", manageItemOrderBean);
				model.addAttribute("categoryList", itemsService.getCategoryList());
			    model.addAttribute("itemList", itemsService.getItemList(null, ""));
			    model.addAttribute(IActionConstants.PAGE_IDENTIFIER, "MANAGE_ITMES");
			    model.addAttribute("contextPath", request.getContextPath());
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
		return "manageItems";
	}
	
	private HashMap<String, Object> checkAvailabilityForItemsHelper(ManageItemOrderBean manageItemOrderBean) throws Exception{
		
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		String orderAvailability = "Y";
		System.out.println("manageItemOrderBean ::\n"+manageItemOrderBean);
		StringBuilder itemIdCSV = new StringBuilder();
		LinkedHashMap<String, String> itemOrderQuantityMap = new LinkedHashMap<String, String>();
		List<ItemOrderBean> onlyAddedItemList = new ArrayList<ItemOrderBean>();
		for(ItemOrderBean itemOrderBean : manageItemOrderBean.getAddedItems()){
			if(itemOrderBean!=null && !"Y".equals(itemOrderBean.getIsDeleted())){
				onlyAddedItemList.add(itemOrderBean);
				if(itemIdCSV.length()>0){
					itemIdCSV.append(",");
				}
				itemIdCSV.append(itemOrderBean.getItem());
				
				itemOrderQuantityMap.put(itemOrderBean.getItem(), itemOrderBean.getQuantity());
			}
		}
		
		manageItemOrderBean.setAddedItems(onlyAddedItemList);
		Map<String, Parts> partsInfoMapForItems =  itemsService.getPartsInfoMapForItems(itemIdCSV.toString());
		Map<String, Map<String, ItemPartMappingBean>> itemPartMappingMap = itemsService.getItemPartMappingInfoMapForItems(itemIdCSV.toString());
		
		Map<String, PartAvailabilityBean> partAvailabilityMap = new HashMap<String, PartAvailabilityBean>();
		
		if(itemPartMappingMap!=null){
			for(Iterator<String> itemPartMappingMapItr = itemPartMappingMap.keySet().iterator(); itemPartMappingMapItr.hasNext();) {
				String itemId = itemPartMappingMapItr.next();
				Map<String, ItemPartMappingBean> itemPartMappingBeanMap = itemPartMappingMap.get(itemId);
				
				for(Iterator<String> itemPartMappingBeanMapItr = itemPartMappingBeanMap.keySet().iterator(); itemPartMappingBeanMapItr.hasNext();) {
					ItemPartMappingBean itemPartMappingBean = itemPartMappingBeanMap.get(itemPartMappingBeanMapItr.next());
					if(partAvailabilityMap.get(itemPartMappingBean.getPartId())==null){
						partAvailabilityMap.put(itemPartMappingBean.getPartId(), 
							new PartAvailabilityBean(itemPartMappingBean.getPartId(), partsInfoMapForItems.get(itemPartMappingBean.getPartId()).getName(), 
									partsInfoMapForItems.get(itemPartMappingBean.getPartId()).getCode(), partsInfoMapForItems.get(itemPartMappingBean.getPartId()).getQuantity(), 
									"0", partsInfoMapForItems.get(itemPartMappingBean.getPartId()).getThreshold(), partsInfoMapForItems.get(itemPartMappingBean.getPartId()).getCategoryId()));
					}
					
					int alreadyRqdQty = Integer.parseInt(partAvailabilityMap.get(itemPartMappingBean.getPartId()).getRequiredQuantity());
					int incrementedRqdQty = alreadyRqdQty + (Integer.parseInt(itemPartMappingBean.getPartQuantity()) * Integer.parseInt(itemOrderQuantityMap.get(itemId)));
					partAvailabilityMap.get(itemPartMappingBean.getPartId()).setRequiredQuantity(
							String.valueOf(incrementedRqdQty));
					
					if(incrementedRqdQty>Integer.parseInt(partAvailabilityMap.get(itemPartMappingBean.getPartId()).getCurrentQuantity())){
						partAvailabilityMap.get(itemPartMappingBean.getPartId()).setInsufficientParts(true);
						orderAvailability = "N";
					}
				}
			}
		}
		
		retMap.put("orderAvailability", orderAvailability);
		retMap.put("partAvailabilityMap", partAvailabilityMap);
		retMap.put("itemOrderQuantityMap", itemOrderQuantityMap);
		
		return retMap;
	}
	
	
	@RequestMapping(value="/initOrderReport" ,method = RequestMethod.GET)
	public String initOrderReport(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside initOrderReport"); 
		
		try {
				model.addAttribute("orderReport", new OrderReportBean());
				model.addAttribute("categoryList", itemsService.getCategoryList());
			    model.addAttribute("itemList", itemsService.getItemList(null, ""));
			    model.addAttribute("clientList", itemsService.getClientList(""));
			    model.addAttribute(IActionConstants.PAGE_IDENTIFIER, "ORDER_REPORT");
			    model.addAttribute("contextPath", request.getContextPath());
			
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "orderReport";
	}
	
	@RequestMapping(value="/getOrderSearchResults")
	public String getOrderSearchResults(ModelMap model,HttpServletRequest request,HttpServletResponse response,OrderReportBean orderReportBean){
		logger.info("Inside checkAvailabilityForItems"); 
		try {
			System.out.println("orderReportBean ::\n"+orderReportBean);
			
			List<OrderSearchResultBean> orderSearchResultBeanList = itemsService.getOrderSearchResults(orderReportBean.getOrderSearchBean());
			orderReportBean.setOrderSearchResultBeanList(orderSearchResultBeanList);
			
			if(orderSearchResultBeanList==null || orderSearchResultBeanList.isEmpty()){
				model.addAttribute("msgObject", IMessageConstants.MSG_NO_ORDER_FOUND);
			}
			
			model.addAttribute("orderReport", orderReportBean);
			model.addAttribute("categoryList", itemsService.getCategoryList());
		    model.addAttribute("itemList", itemsService.getItemList(null, ""));
		    model.addAttribute("clientList", itemsService.getClientList(""));
		    model.addAttribute(IActionConstants.PAGE_IDENTIFIER, "ORDER_REPORT");
		    model.addAttribute("contextPath", request.getContextPath());
		    
		} catch (Exception e) {
			logger.error("Error In controller");
			e.printStackTrace();
		}
		return "orderReport";
	}
	
	@RequestMapping(value="/getDetailsForOrderId")
	public String getDetailsForOrderId(ModelMap model,HttpServletRequest request,HttpServletResponse response,OrderReportBean orderReportBean){
		logger.info("Inside getItemDetailsForOrderId"); 
		try {
			System.out.println("orderReportBean ::\n"+orderReportBean);
			
			itemsService.getOrderDetailsForOrderId(orderReportBean.getOrderId(), orderReportBean.getManageItemOrderBean());
			orderReportBean.getManageItemOrderBean().setAddedItems(itemsService.getItemDetailsForOrderId(orderReportBean.getOrderId()));
			
			List<OrderSearchResultBean> orderSearchResultBeanList = itemsService.getOrderSearchResults(orderReportBean.getOrderSearchBean());
			orderReportBean.setOrderSearchResultBeanList(orderSearchResultBeanList);
			
			if(orderSearchResultBeanList==null || orderSearchResultBeanList.isEmpty()){
				model.addAttribute("msgObject", IMessageConstants.MSG_NO_ORDER_FOUND);
			}
			
			model.addAttribute("orderReport", orderReportBean);
			model.addAttribute("categoryList", itemsService.getCategoryList());
		    model.addAttribute("itemList", itemsService.getItemList(null, ""));
		    model.addAttribute("clientList", itemsService.getClientList(""));
		    model.addAttribute(IActionConstants.PAGE_IDENTIFIER, "ORDER_REPORT");
		    model.addAttribute("contextPath", request.getContextPath());
		    
		} catch (Exception e) {
			logger.error("Error In controller");
			e.printStackTrace();
		}
		return "orderReport";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/checkAvailabilityForItemsEditOrder")
	public String checkAvailabilityForItemsEditOrder(ModelMap model,HttpServletRequest request,HttpServletResponse response,OrderReportBean orderReportBean){
		logger.info("Inside checkAvailabilityForItemsEditOrder"); 
		try {
			System.out.println("orderReportBean ::\n"+orderReportBean);
			
			HashMap<String, Object> availabiltyCheckerMap =  checkAvailabilityForOrderEditHelper(orderReportBean.getOrderId(), orderReportBean.getManageItemOrderBean());
			String orderAvailability = (String)availabiltyCheckerMap.get("orderAvailability");
			Map<String, PartAvailabilityBean> partAvailabilityMap = (Map<String, PartAvailabilityBean>)availabiltyCheckerMap.get("partAvailabilityMap");
			
			System.out.println("partAvailabilityMap ::::\n"+(Map<String, PartAvailabilityBean>)availabiltyCheckerMap.get("partAvailabilityMap"));
			
			List<OrderSearchResultBean> orderSearchResultBeanList = itemsService.getOrderSearchResults(orderReportBean.getOrderSearchBean());
			orderReportBean.setOrderSearchResultBeanList(orderSearchResultBeanList);
			
			if(orderSearchResultBeanList==null || orderSearchResultBeanList.isEmpty()){
				model.addAttribute("msgObject", IMessageConstants.MSG_NO_ORDER_FOUND);
			}
			
			model.addAttribute("orderAvailability", orderAvailability);
			model.addAttribute("partAvailabilityMap", partAvailabilityMap);
			model.addAttribute("orderReport", orderReportBean);
			model.addAttribute("categoryList", itemsService.getCategoryList());
		    model.addAttribute("itemList", itemsService.getItemList(null, ""));
		    model.addAttribute("clientList", itemsService.getClientList(""));
		    model.addAttribute(IActionConstants.PAGE_IDENTIFIER, "ORDER_REPORT");
		    model.addAttribute("contextPath", request.getContextPath());
		    
		} catch (Exception e) {
			logger.error("Error In controller");
			e.printStackTrace();
		}
		return "orderReport";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/updateOrderForItemsEditOrder")
	public String updateOrderForItemsEditOrder(ModelMap model,HttpServletRequest request,HttpServletResponse response,OrderReportBean orderReportBean){
		logger.info("Inside updateOrderForItemsEditOrder"); 
		try {
			System.out.println("orderReportBean ::\n"+orderReportBean);
			
			HashMap<String, Object> availabiltyCheckerMap =  checkAvailabilityForOrderEditHelper(orderReportBean.getOrderId(), orderReportBean.getManageItemOrderBean());
			String orderAvailability = (String)availabiltyCheckerMap.get("orderAvailability");
			Map<String, PartAvailabilityBean> partAvailabilityMap = (Map<String, PartAvailabilityBean>)availabiltyCheckerMap.get("partAvailabilityMap");
			Map<String, String> itemOrderQuantityMap = (Map<String, String>)availabiltyCheckerMap.get("itemOrderQuantityMap");
			Map<String, PartAvailabilityBean> unusedPartsInUpdatedOrderMap = (Map<String, PartAvailabilityBean>)availabiltyCheckerMap.get("unusedPartsInUpdatedOrderMap");
			
			System.out.println("partAvailabilityMap ::::\n"+(Map<String, PartAvailabilityBean>)availabiltyCheckerMap.get("partAvailabilityMap"));
			
			if("Y".equals(orderAvailability)){
				OrderBean orderBean = itemsService.updateOrderForItems(orderReportBean.getOrderId(), orderReportBean.getManageItemOrderBean(), 
						itemOrderQuantityMap, partAvailabilityMap, unusedPartsInUpdatedOrderMap);
				model.addAttribute("msgObject", new MessageBean(MessageBean.CONFIRMATION,"Order with Order Id : "+orderBean.getOrderId()+" updated successfully"));
				orderReportBean.setManageItemOrderBean(new ManageItemOrderBean());
			}else{
				model.addAttribute("partAvailabilityMap", partAvailabilityMap);
				model.addAttribute("msgObject", IMessageConstants.MSG_PLACE_OREDER_FAILED);
			}
			
			List<OrderSearchResultBean> orderSearchResultBeanList = itemsService.getOrderSearchResults(orderReportBean.getOrderSearchBean());
			orderReportBean.setOrderSearchResultBeanList(orderSearchResultBeanList);
			
			if(orderSearchResultBeanList==null || orderSearchResultBeanList.isEmpty()){
				model.addAttribute("msgObject", IMessageConstants.MSG_NO_ORDER_FOUND);
			}
			
			model.addAttribute("orderAvailability", orderAvailability);
			model.addAttribute("partAvailabilityMap", partAvailabilityMap);
			model.addAttribute("orderReport", orderReportBean);
			model.addAttribute("categoryList", itemsService.getCategoryList());
		    model.addAttribute("itemList", itemsService.getItemList(null, ""));
		    model.addAttribute("clientList", itemsService.getClientList(""));
		    model.addAttribute(IActionConstants.PAGE_IDENTIFIER, "ORDER_REPORT");
		    model.addAttribute("contextPath", request.getContextPath());
		    
		} catch (Exception e) {
			logger.error("Error In controller");
			e.printStackTrace();
		}
		return "orderReport";
	}
	
	private HashMap<String, Object> checkAvailabilityForOrderEditHelper(String orderId, ManageItemOrderBean manageItemOrderBean) throws Exception{
		
		List<ItemOrderBean> itemOrderListForOrder = itemsService.getItemOrderDetailsForOrderId(orderId);
		StringBuilder itemIdCSVForOrder = new StringBuilder();
		LinkedHashMap<String, String> itemOrderQuantityMapForOrder = new LinkedHashMap<String, String>();
		for(ItemOrderBean itemOrderBean : itemOrderListForOrder){
			if(itemIdCSVForOrder.length()>0){
				itemIdCSVForOrder.append(",");
			}
			itemIdCSVForOrder.append(itemOrderBean.getItem());
			
			itemOrderQuantityMapForOrder.put(itemOrderBean.getItem(), itemOrderBean.getQuantity());
		}
		
		Map<String, Parts> partsInfoMapForItemsForOrder =  itemsService.getPartsInfoMapForItems(itemIdCSVForOrder.toString());
		Map<String, Map<String, ItemPartMappingBean>> itemPartMappingMapForOrder = itemsService.getItemPartMappingInfoMapForItems(itemIdCSVForOrder.toString());
		
		Map<String, PartAvailabilityBean> partAvailabilityMapForOrder = new HashMap<String, PartAvailabilityBean>();
		
		if(itemPartMappingMapForOrder!=null){
			for(Iterator<String> itemPartMappingMapItrForOrder = itemPartMappingMapForOrder.keySet().iterator(); itemPartMappingMapItrForOrder.hasNext();) {
				String itemId = itemPartMappingMapItrForOrder.next();
				Map<String, ItemPartMappingBean> itemPartMappingBeanMapForOrder = itemPartMappingMapForOrder.get(itemId);
				
				for(Iterator<String> itemPartMappingBeanMapItrForOrder = itemPartMappingBeanMapForOrder.keySet().iterator(); itemPartMappingBeanMapItrForOrder.hasNext();) {
					ItemPartMappingBean itemPartMappingBeanForOrder = itemPartMappingBeanMapForOrder.get(itemPartMappingBeanMapItrForOrder.next());
					if(partAvailabilityMapForOrder.get(itemPartMappingBeanForOrder.getPartId())==null){
						partAvailabilityMapForOrder.put(itemPartMappingBeanForOrder.getPartId(), 
							new PartAvailabilityBean(itemPartMappingBeanForOrder.getPartId(), partsInfoMapForItemsForOrder.get(itemPartMappingBeanForOrder.getPartId()).getName(), 
									partsInfoMapForItemsForOrder.get(itemPartMappingBeanForOrder.getPartId()).getCode(), partsInfoMapForItemsForOrder.get(itemPartMappingBeanForOrder.getPartId()).getQuantity(), 
									"0", partsInfoMapForItemsForOrder.get(itemPartMappingBeanForOrder.getPartId()).getThreshold(), partsInfoMapForItemsForOrder.get(itemPartMappingBeanForOrder.getPartId()).getCategoryId()));
					}
					
					int alreadyRqdQtyForOrder = Integer.parseInt(partAvailabilityMapForOrder.get(itemPartMappingBeanForOrder.getPartId()).getRequiredQuantity());
					int incrementedRqdQtyForOrder = alreadyRqdQtyForOrder + (Integer.parseInt(itemPartMappingBeanForOrder.getPartQuantity()) * Integer.parseInt(itemOrderQuantityMapForOrder.get(itemId)));
					partAvailabilityMapForOrder.get(itemPartMappingBeanForOrder.getPartId()).setCurrentQuantity(
							String.valueOf((Integer.parseInt(partAvailabilityMapForOrder.get(itemPartMappingBeanForOrder.getPartId()).getCurrentQuantity()) + incrementedRqdQtyForOrder)));
					
				}
			}
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		String orderAvailability = "Y";
		// System.out.println("manageItemOrderBean ::\n"+manageItemOrderBean);
		StringBuilder itemIdCSV = new StringBuilder();
		LinkedHashMap<String, String> itemOrderQuantityMap = new LinkedHashMap<String, String>();
		List<ItemOrderBean> onlyAddedItemList = new ArrayList<ItemOrderBean>();
		for(ItemOrderBean itemOrderBean : manageItemOrderBean.getAddedItems()){
			if(itemOrderBean!=null && !"Y".equals(itemOrderBean.getIsDeleted())){
				onlyAddedItemList.add(itemOrderBean);
				if(itemIdCSV.length()>0){
					itemIdCSV.append(",");
				}
				itemIdCSV.append(itemOrderBean.getItem());
				
				itemOrderQuantityMap.put(itemOrderBean.getItem(), itemOrderBean.getQuantity());
			}
		}
		
		manageItemOrderBean.setAddedItems(onlyAddedItemList);
		Map<String, Parts> partsInfoMapForItems =  itemsService.getPartsInfoMapForItems(itemIdCSV.toString());
		Map<String, Map<String, ItemPartMappingBean>> itemPartMappingMap = itemsService.getItemPartMappingInfoMapForItems(itemIdCSV.toString());
		
		Map<String, PartAvailabilityBean> partAvailabilityMap = new HashMap<String, PartAvailabilityBean>();
		
		if(itemPartMappingMap!=null){
			for(Iterator<String> itemPartMappingMapItr = itemPartMappingMap.keySet().iterator(); itemPartMappingMapItr.hasNext();) {
				String itemId = itemPartMappingMapItr.next();
				Map<String, ItemPartMappingBean> itemPartMappingBeanMap = itemPartMappingMap.get(itemId);
				
				for(Iterator<String> itemPartMappingBeanMapItr = itemPartMappingBeanMap.keySet().iterator(); itemPartMappingBeanMapItr.hasNext();) {
					ItemPartMappingBean itemPartMappingBean = itemPartMappingBeanMap.get(itemPartMappingBeanMapItr.next());
					if(partAvailabilityMap.get(itemPartMappingBean.getPartId())==null){
						String adjustedCurrentQuantity = partAvailabilityMapForOrder.get(itemPartMappingBean.getPartId()) != null ? 
								partAvailabilityMapForOrder.get(itemPartMappingBean.getPartId()).getCurrentQuantity() : 
									partsInfoMapForItems.get(itemPartMappingBean.getPartId()).getQuantity();
								
						partAvailabilityMap.put(itemPartMappingBean.getPartId(), 
							new PartAvailabilityBean(itemPartMappingBean.getPartId(), partsInfoMapForItems.get(itemPartMappingBean.getPartId()).getName(), 
									partsInfoMapForItems.get(itemPartMappingBean.getPartId()).getCode(), adjustedCurrentQuantity, 
									"0", partsInfoMapForItems.get(itemPartMappingBean.getPartId()).getThreshold(), partsInfoMapForItems.get(itemPartMappingBean.getPartId()).getCategoryId()));
					}
					
					int alreadyRqdQty = Integer.parseInt(partAvailabilityMap.get(itemPartMappingBean.getPartId()).getRequiredQuantity());
					int incrementedRqdQty = alreadyRqdQty + (Integer.parseInt(itemPartMappingBean.getPartQuantity()) * Integer.parseInt(itemOrderQuantityMap.get(itemId)));
					partAvailabilityMap.get(itemPartMappingBean.getPartId()).setRequiredQuantity(
							String.valueOf(incrementedRqdQty));
					
					if(incrementedRqdQty>Integer.parseInt(partAvailabilityMap.get(itemPartMappingBean.getPartId()).getCurrentQuantity())){
						partAvailabilityMap.get(itemPartMappingBean.getPartId()).setInsufficientParts(true);
						orderAvailability = "N";
					}
				}
			}
		}
		
		Map<String, PartAvailabilityBean> unusedPartsInUpdatedOrderMap = new HashMap<String, PartAvailabilityBean>();
		for(Iterator<String> partAvailabilityMapForOrderItr = partAvailabilityMapForOrder.keySet().iterator(); partAvailabilityMapForOrderItr.hasNext();) {
			String partId = partAvailabilityMapForOrderItr.next();
			if(partAvailabilityMap.get(partId)==null){
				unusedPartsInUpdatedOrderMap.put(partId, partAvailabilityMapForOrder.get(partId));
			}
		}
		
		retMap.put("orderAvailability", orderAvailability);
		retMap.put("partAvailabilityMap", partAvailabilityMap);
		retMap.put("itemOrderQuantityMap", itemOrderQuantityMap);
		retMap.put("unusedPartsInUpdatedOrderMap", unusedPartsInUpdatedOrderMap);
		
		return retMap;
	}
	
	@RequestMapping(value="/fetchClientsAutoSuggest")
	public @ResponseBody List<NameValuePairBean> fetchClientsAutoSuggest(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside fetchClientsAutoSuggest"); 
		List<NameValuePairBean> clientList = null;
		try {
				String clientNamePattern = request.getParameter("textName");
				clientList = itemsService.getClientList(clientNamePattern);
			
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientList;
	}
	
}
