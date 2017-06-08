package com.velvet.controllers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.velvet.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.velvet.constants.IMessageConstants;
import com.velvet.service.IItemsManagementService;
import com.velvet.service.IPartsManagementService;
import com.velvet.vo.Bill;
import com.velvet.vo.ItemPartMappingBean;
import com.velvet.vo.MessageBean;
import com.velvet.vo.NameValuePairBean;
import com.velvet.vo.PartAvailabilityBean;
import com.velvet.vo.Parts;
import com.velvet.vo.PartsSearchBean;
import com.velvet.vo.PartsSearchResultsBean;

@Controller
@RequestMapping("/parts")
public class PartsManagementController {
	
	/*
	 * Logger file to initialize logger
	 */
	private Logger logger = LoggerFactory
			.getLogger(PartsManagementController.class);
	
	@Autowired
	@Qualifier("partsManagementServiceImpl")
	private IPartsManagementService partsService;

	@Autowired
	@Qualifier("itemsManagementServiceImpl")
	private IItemsManagementService itemsService;

	@RequestMapping(value="/showParts",method = RequestMethod.GET)
	public String showUserForm(ModelMap model)
	{
		logger.info("Inside Show USer Form");
		Parts parts = new Parts();
		parts.setName("name");
		model.addAttribute("parts", parts);
		System.out.println("Inside Parts");
		logger.error("The Parts Info is",parts);
		return "showParts";
	}
	
	@RequestMapping(value="/addParts")
	public String addParts(@ModelAttribute("parts") Parts parts){
		logger.info("Inside addParts"); 
		try {
			partsService.addParts(parts);
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("parts name is "+parts.getName()+"key is");
		logger.info("The Parts Key is");
		return "partSuccess";
	}
	
	@RequestMapping(value="/showPrtsInvntry" ,method = RequestMethod.GET)
	public String showPrtsInvntry(ModelMap model){
		logger.info("Inside showPrtsInvntry"); 
		Parts parts = null;
		try {
			 parts = new Parts();
				parts.setName("name");
				model.addAttribute("parts", parts);
			    model.addAttribute("categoryList", partsService.getCategoryList());
			    model.addAttribute("partList", partsService.getPartList());
			
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "partsInventory";
	}
	
	
	
	@RequestMapping(value="/submitParts")
	public @ResponseBody Parts submitParts(HttpServletRequest request,HttpServletResponse response,@RequestBody Parts parts){
		logger.info("Inside addParts"); 
		List<NameValuePairBean> categoryList = null;
		try {
			//partsService.addParts(parts);
			categoryList = partsService.getCategoryList();
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("The Parts Key is");
		return parts;
	}
	
	@RequestMapping(value="/showPartsInfo")
	public @ResponseBody Parts showPartsInfo(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside showPrtsInvntry"); 
		Parts parts = null;
		try {
			 parts = new Parts();
				String partsId = ((request.getParameter("objectId") != null) ? request.getParameter("objectId"):"");
				parts = partsService.getPartsInfo(partsId);
			
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parts;
	}
	
	@RequestMapping(value="/initAddDeleteParts" ,method = RequestMethod.GET)
	public String initAddDeleteParts(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside initAddDeleteParts"); 
		
		Parts parts = null;
		try {
			 parts = new Parts();
				model.addAttribute("parts", parts);
			    model.addAttribute("categoryList", partsService.getCategoryList());
			    model.addAttribute("partList", partsService.getPartList());
			    model.addAttribute("contextPath", request.getContextPath());
			
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "addDeleteParts";
	}
	
	@RequestMapping(value="/submitAddDeleteParts")
	public String submitAddDeleteParts(ModelMap model,HttpServletRequest request,HttpServletResponse response,Parts parts){
		logger.info("Inside submitAddDeleteParts"); 
		String updatedQnty = "";
		String action = ""; 
		String date ="";
		try {
			action = (request.getParameter("action")!=null ? request.getParameter("action"):"");
			date = (request.getParameter("updatedDate")!=null ? request.getParameter("updatedDate"):"");
			
			if(!StringUtils.IsBlank(action) && "ADD".equalsIgnoreCase(action)){
				updatedQnty = (request.getParameter("addedQnty")!=null ? request.getParameter("addedQnty"):"0");
			}else if(!StringUtils.IsBlank(action) && "DELETE".equalsIgnoreCase(action)){
				updatedQnty = (request.getParameter("deleteQnty")!=null ? request.getParameter("deleteQnty"):"0");
			}
			int retVal = partsService.submitAddDeleteParts(parts,updatedQnty,action,date);
			if(retVal == 1){
				parts = partsService.getPartsInfo(parts.getPartsId());
				model.addAttribute("parts", parts);
			    model.addAttribute("categoryList", partsService.getCategoryList());
			    if(StringUtils.isEmptyDrpDwn(parts.getCategoryId())){
			    model.addAttribute("partList", partsService.getPartList());
			    }else{
			    	 model.addAttribute("partList", partsService.getPartListByCategory(parts.getCategoryId()));
			    }
			    if(Integer.parseInt(parts.getQuantity()) >= Integer.parseInt(parts.getThreshold())){
			    	model.addAttribute("msgObject", IMessageConstants.MSG_PARTS_UPDATED_SUCCESSFULLY);
			    }else{
			    	model.addAttribute("msgObject", IMessageConstants.MSG_ALERT_THRESHOLD_BREACHED);
			    	model.addAttribute("contextPath", request.getContextPath());
			    }
			    
			  
			}else if(retVal == 2){
		    	model.addAttribute("msgObject", IMessageConstants.MSG_UPDATION_FAILED);
		    	model.addAttribute("contextPath", request.getContextPath());
			}else{
				model.addAttribute("msgObject", IMessageConstants.MSG_PARTS_UPDATION_FAILED);
				
			}
			model.addAttribute("contextPath", request.getContextPath());
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("The Parts Key is");
		return "addDeleteParts";
		}
	
	@RequestMapping(value="/showPartsByCategory")
	public @ResponseBody List<NameValuePairBean> showPartsByCategory(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside showPartsByCategory"); 
		List<NameValuePairBean> partList = null;
		
		try {
	     		String categoryId = !StringUtils.isEmptyDrpDwn(request.getParameter("objectId")) ? request.getParameter("objectId") : null;
				partList = partsService.getPartListByCategory(categoryId);
				model.addAttribute("objectList", partList);
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return partList;
	}
	
	@RequestMapping(value="/getPartListByPattern")
	public @ResponseBody List<NameValuePairBean> getPartListByPattern(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside showPartsByCategory"); 
		List<NameValuePairBean> partList = null;
		try {
		    	String category = !StringUtils.isEmptyDrpDwn(request.getParameter("category")) ? request.getParameter("category") : null;
				String pattern = ((request.getParameter("textName") != null) ? request.getParameter("textName"):"");
				partList = partsService.getPartListByPattern(pattern,category);
				model.addAttribute("objectList", partList);
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return partList;
	}
	
	@RequestMapping(value="/initGenerateBill" ,method = RequestMethod.GET)
	public String initGenerateBill(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside initAddDeleteParts"); 
		
		try {
				
				Bill bill = new Bill();
				model.addAttribute("bill",bill);
			    model.addAttribute("orderList", partsService.getOrderList());	    
			
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "generateBill";
	}
	
	@RequestMapping(value="/getOrderInfo")
	public String getOrderInfo(ModelMap model,HttpServletRequest request,HttpServletResponse response,Bill bill){
		logger.info("Inside getOrderInfo"); 
		
		try {
			    String orderId = ((bill.getOrder() != null) ? bill.getOrder().getOrderId():"");
				bill = partsService.getOrdersInfo(orderId);
				model.addAttribute("bill",bill);
			    model.addAttribute("orderList", partsService.getOrderList());	    
			    model.addAttribute("contextPath", request.getContextPath());
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "generateBill";
	}
	
	@RequestMapping(value="/generateBillDetails")
	public String generateBillDetails(ModelMap model,HttpServletRequest request,HttpServletResponse response,Bill bill){
		logger.info("Inside getOrderInfo"); 
		
		try {
				bill = partsService.insertBillDetails(bill);
				String billId = bill.getBillNo();
				if(!StringUtils.IsBlank(billId)){
				
					MessageBean msgBean = new MessageBean(MessageBean.CONFIRMATION,"Bill Generated Successfully.The Bill number is {0}",new String[] {billId} );
					model.addAttribute("msgObject",msgBean );	
				}
				
				bill = partsService.getBillInfo(billId);
				model.addAttribute("bill",bill);
			    model.addAttribute("orderList", partsService.getOrderList());	    
			    model.addAttribute("contextPath", request.getContextPath());
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "generateBill";
	}
	@RequestMapping(value="/viewBillDetails")
	public String viewBillDetails(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside viewBillDetails"); 
		
		try {
				Bill bill = null;
			    String billId = ((request.getParameter("billId") != null) ? request.getParameter("billId"):"");
			    if(!StringUtils.IsBlank(billId)){
			    bill = new Bill();
				bill = partsService.getBillInfo(billId);
			    }
				model.addAttribute("bill",bill);
			    model.addAttribute("orderList", partsService.getOrderList());	    
			    model.addAttribute("contextPath", request.getContextPath());
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "viewBill";
	}
	
	@RequestMapping(value="/cancelOrderForItems")
	public String cancelOrderForItems(ModelMap model,HttpServletRequest request,HttpServletResponse response,Bill bill){
		logger.info("Inside placeOrderForItems"); 
		try {
			
			String orderId = ((bill.getOrder() != null) ? bill.getOrder().getOrderId():"");
			Map<String, String> itemOrderQuantityMap = partsService.fetchItemOrderQuantityMap(orderId);
			StringBuilder itemIdCSV = new StringBuilder();
			if(itemOrderQuantityMap!=null && !itemOrderQuantityMap.isEmpty()){
				for (Iterator<String> itemOrderQuantityMapItr = itemOrderQuantityMap.keySet().iterator(); itemOrderQuantityMapItr.hasNext();) {
					if(itemIdCSV.length()>0){
						itemIdCSV.append(",");
					}
					itemIdCSV.append(itemOrderQuantityMapItr.next());
				}
			}
			
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
						
						int presentQty = Integer.parseInt(partAvailabilityMap.get(itemPartMappingBean.getPartId()).getCurrentQuantity());
						int incrementedRqdQty = presentQty + (Integer.parseInt(itemPartMappingBean.getPartQuantity()) * Integer.parseInt(itemOrderQuantityMap.get(itemId)));
						partAvailabilityMap.get(itemPartMappingBean.getPartId()).setCurrentQuantity(
								String.valueOf(incrementedRqdQty));
						
					}
				}
			}
			
			System.out.println("partAvailabilityMap ::::\n"+partAvailabilityMap);
			
			partsService.placeOrderForItems(orderId, partAvailabilityMap);
			
			model.addAttribute("msgObject", new MessageBean(MessageBean.CONFIRMATION,"Order with Order Id : "+orderId+" has been cancelled successfully."));
			
			model.addAttribute("bill",new Bill());
		    model.addAttribute("orderList", partsService.getOrderList());	    
		    model.addAttribute("contextPath", request.getContextPath());
		} catch (Exception e) {
			logger.error("Error In cancelOrderForItems ::"+e.getStackTrace());
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try{
				model.addAttribute("msgObject", IMessageConstants.MSG_CANCEL_ORDER_FAILED);
				model.addAttribute("bill",bill);
			    model.addAttribute("orderList", partsService.getOrderList());	    
			    model.addAttribute("contextPath", request.getContextPath());
			}catch (Exception ex) {
				logger.error("Error In cancelOrderForItems ::"+e.getStackTrace());
				ex.printStackTrace();
			}
			
		}
		return "generateBill";
	}
	
	@RequestMapping(value="/initPartsSearch",method = RequestMethod.GET)
	public String initPartsSearch(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside initPartsSearch"); 
		
		PartsSearchBean partsSearch = new PartsSearchBean();
		try {
			 
				model.addAttribute("partsSearch", partsSearch);
			    model.addAttribute("categoryList", partsService.getCategoryList());
			    model.addAttribute("partList", partsService.getPartList());
			    model.addAttribute("contextPath", request.getContextPath());
			
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "partsInventoryReport";
	}
	
	@RequestMapping(value="/getPartSearchRslts")
	public String getPartSearchRslts(ModelMap model,HttpServletRequest request,HttpServletResponse response,PartsSearchBean partsSearch){
		logger.info("Inside getPartSearchRslts"); 
		
		List<PartsSearchResultsBean> partsResultsList = null;
		try {
			    partsResultsList = partsService.getPartsSearchResults(partsSearch);
			    partsSearch.setPartsSearchResults(partsResultsList);
				model.addAttribute("partsSearch", partsSearch);
			    model.addAttribute("categoryList", partsService.getCategoryList());
			    
			    model.addAttribute("partList", partsService.getPartList());
			    model.addAttribute("contextPath", request.getContextPath());
			
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "partsInventoryReport";
	}
	
	
	@RequestMapping(value="/initPartsThresholdSearch",method = RequestMethod.GET)
	public String initPartsThresholdSearch(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside initPartsSearch"); 
		
		PartsSearchBean partsSearch = new PartsSearchBean();
		try {
			 
				model.addAttribute("partsSearch", partsSearch);
			    model.addAttribute("categoryList", partsService.getCategoryList());
			    model.addAttribute("partList", partsService.getPartList());
			    model.addAttribute("contextPath", request.getContextPath());
			
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "partsThresholdReport";
	}
	
	@RequestMapping(value="/getPartthresholdRslts")
	public String getPartthresholdRslts(ModelMap model,HttpServletRequest request,HttpServletResponse response,PartsSearchBean partsSearch){
		logger.info("Inside getPartSearchRslts"); 
		
		List<PartsSearchResultsBean> partsResultsList = null;
		try {
			    partsResultsList = partsService.getPartsThresholdResults(partsSearch);
			    partsSearch.setPartsSearchResults(partsResultsList);
				model.addAttribute("partsSearch", partsSearch);
			    model.addAttribute("categoryList", partsService.getCategoryList());
			    model.addAttribute("partList", partsService.getPartList());
			    model.addAttribute("contextPath", request.getContextPath());
			
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "partsThresholdReport";
	}
	
	
}
