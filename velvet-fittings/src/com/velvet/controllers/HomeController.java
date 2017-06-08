package com.velvet.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.velvet.constants.IActionConstants;
import com.velvet.constants.IApplicationConstants;
import com.velvet.controllers.mappers.UserMapper;
import com.velvet.vo.UserBean;
import com.velvet.vo.navigation.UserNavigationBean;
import com.velvet.web.SessionManager;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value="/initAdminTab" ,method = RequestMethod.GET)
	public String initAdminTab(ModelMap model){
		logger.info("Inside initAdminTab"); 
		
		return "adminTabContent";
	}
	

	
	@RequestMapping(value="/welcome" ,method = RequestMethod.GET)
	public String welcome(ModelMap model){
		logger.info("Inside welcome"); 
		
		return "home";
	}
	
	@RequestMapping(value="/login" ,method = RequestMethod.POST)
	public String login(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		logger.info("Inside loginSuccess"); 
		try {
			
			List<UserNavigationBean> userNavigationList = new ArrayList<UserNavigationBean>();
			if(IApplicationConstants.USER_NAME.equals(request.getParameter("username")) 
					&& IApplicationConstants.USER_PASSWORD.equals(request.getParameter("password"))){
				SessionManager sessionManager = new SessionManager(request);
				sessionManager.getHttpSession(request);
				
				Properties menuUrlMappingProperties = (Properties) WebApplicationContextUtils.getRequiredWebApplicationContext(sessionManager.getHttpSession(request)
						.getServletContext()).getBean(IApplicationConstants.FILE_ACCESS_URL_PROPERTIES);
				
				userNavigationList.add(new UserNavigationBean(request.getParameter("username"), IActionConstants.ROLE_ADMIN, IActionConstants.ROLE_TYPE_PRIMARY,
						IActionConstants.TAB_ADMIN, IActionConstants.TAB_ADMIN, IActionConstants.TAB_ADMIN_NAME,
						IActionConstants.MENU_ADD_DELETE_PARTS, IActionConstants.MENU_ADD_DELETE_PARTS, 
						IActionConstants.MENU_ADD_DELETE_PARTS_NAME, IActionConstants.ACCESS_FULL));
				
				userNavigationList.add(new UserNavigationBean(request.getParameter("username"), IActionConstants.ROLE_ADMIN, IActionConstants.ROLE_TYPE_PRIMARY,
						IActionConstants.TAB_ADMIN, IActionConstants.TAB_ADMIN, IActionConstants.TAB_ADMIN_NAME,
						IActionConstants.MENU_ADD_DELETE_PARTS, IActionConstants.MENU_PARTS_REPORT, 
						IActionConstants.MENU_PARTS_REPORT_NAME, IActionConstants.ACCESS_FULL));
				
				userNavigationList.add(new UserNavigationBean(request.getParameter("username"), IActionConstants.ROLE_ADMIN, IActionConstants.ROLE_TYPE_PRIMARY,
						IActionConstants.TAB_ADMIN, IActionConstants.TAB_ADMIN, IActionConstants.TAB_ADMIN_NAME,
						IActionConstants.MENU_ADD_DELETE_PARTS, IActionConstants.MENU_PARTS_THRESHOLD_REPORT, 
						IActionConstants.MENU_PARTS_THRESHOLD_REPORT_NAME, IActionConstants.ACCESS_FULL));

				userNavigationList.add(new UserNavigationBean(request.getParameter("username"), IActionConstants.ROLE_ADMIN, IActionConstants.ROLE_TYPE_PRIMARY,
						IActionConstants.TAB_ADMIN, IActionConstants.TAB_ADMIN, IActionConstants.TAB_ADMIN_NAME,
						IActionConstants.MENU_ADD_DELETE_PARTS, IActionConstants.MENU_MANAGE_ITEMS, 
						IActionConstants.MENU_MANAGE_ITEMS_NAME, IActionConstants.ACCESS_FULL));
				
				userNavigationList.add(new UserNavigationBean(request.getParameter("username"), IActionConstants.ROLE_ADMIN, IActionConstants.ROLE_TYPE_PRIMARY,
						IActionConstants.TAB_ADMIN, IActionConstants.TAB_ADMIN, IActionConstants.TAB_ADMIN_NAME,
						IActionConstants.MENU_ADD_DELETE_PARTS, IActionConstants.MENU_ORDER_REPORT, 
						IActionConstants.MENU_ORDER_REPORT_NAME, IActionConstants.ACCESS_FULL));
				
				userNavigationList.add(new UserNavigationBean(request.getParameter("username"), IActionConstants.ROLE_ADMIN, IActionConstants.ROLE_TYPE_PRIMARY,
						IActionConstants.TAB_ADMIN, IActionConstants.TAB_ADMIN, IActionConstants.TAB_ADMIN_NAME,
						IActionConstants.MENU_ADD_DELETE_PARTS, IActionConstants.MENU_GENERATE_BILL, 
						IActionConstants.MENU_GENERATE_BILL_NAME, IActionConstants.ACCESS_FULL));
				
				
				UserBean userBean = new UserBean(request.getParameter("username"), request.getParameter("password"), "Admin", "", "", IActionConstants.ROLE_ADMIN, 
						IActionConstants.ROLE_ADMIN_NAME, "", "", UserMapper.mapNavigationBean(userNavigationList, menuUrlMappingProperties), 
						IActionConstants.ROLE_TYPE_PRIMARY, "", "");
				
				(SessionManager.getSessionMap(request)).put(IActionConstants.USER_BEAN,userBean);
				
			}
			
		} catch (Exception e) {
			logger.error("Error In controller");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "landingPage";
	}
	
		
}
