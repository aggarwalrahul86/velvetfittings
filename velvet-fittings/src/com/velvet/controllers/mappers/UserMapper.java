/*
 * @(#)UserMapper.java        1.0  12,Jan 2012
 *
 * Copyright (c) 2012 EGIL
 * All rights reserved.
 *
 * Revision History
 * 
 * Revision   		Date          Author        				Description
 * ======================================================================
 * 	1		 	 Jan 12, 2012  		Ericsson			Maps UserNavigationBean to RoleAccessBean
 */
package com.velvet.controllers.mappers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.velvet.constants.IActionConstants;
import com.velvet.constants.IApplicationConstants;
import com.velvet.vo.navigation.MenuNavigationBean;
import com.velvet.vo.navigation.RoleAccessBean;
import com.velvet.vo.navigation.TabNavigationBean;
import com.velvet.vo.navigation.UserNavigationBean;

/**
 * UserMapper
 * 
 * @version 1.0 12 Jan 2012
 * @author Ericsson
 */
public class UserMapper implements IActionConstants, IApplicationConstants {

	/**
	 * 
	 * @param userNavigationList
	 * @return RoleAccessBean
	 * @throws Exception
	 * 
	 * This method is used for Mapping UserNavigationBean to RoleAccessBean for use in Web Layer
	 */
	public static RoleAccessBean mapNavigationBean(List<UserNavigationBean> userNavigationList, Properties menuUrlMappingProperties) throws Exception {
		RoleAccessBean roleAccessBean = null;
		if(userNavigationList!=null){
			
			if(menuUrlMappingProperties!=null){
				Map<String, TabNavigationBean> tabAssociations = null;
				Map<String, MenuNavigationBean> menuAssociations = null;
				TabNavigationBean tabNavigationBean = null;
				MenuNavigationBean menuNavigationBean = null;
				for(UserNavigationBean userNavigationBean : userNavigationList){
					if(userNavigationBean!=null){
						if(roleAccessBean==null){
							tabAssociations = new LinkedHashMap<String, TabNavigationBean>();
							roleAccessBean = new RoleAccessBean(userNavigationBean.getLandingTabId(), tabAssociations);
						}
						tabNavigationBean = tabAssociations.get(userNavigationBean.getTabId());
						if(tabAssociations.get(userNavigationBean.getTabId())==null){
							menuAssociations = new LinkedHashMap<String, MenuNavigationBean>();
							tabNavigationBean = new TabNavigationBean(userNavigationBean.getTabId(), userNavigationBean.getTabName(), menuUrlMappingProperties.getProperty(userNavigationBean.getTabId()),
									userNavigationBean.getLandingMenuId(), menuAssociations);
							tabAssociations.put(userNavigationBean.getTabId(), tabNavigationBean);
						}
						menuNavigationBean = menuAssociations.get(userNavigationBean.getMenuId());
						if(menuAssociations.get(userNavigationBean.getMenuId())==null){
							menuNavigationBean = new MenuNavigationBean(userNavigationBean.getMenuId(), userNavigationBean.getMenuName(), menuUrlMappingProperties.getProperty(userNavigationBean.getMenuId()));
							menuAssociations.put(userNavigationBean.getMenuId(), menuNavigationBean);
						}
					}
				}
			}
		}
		
		System.out.println("roleAccessBean ::::\n"+roleAccessBean);
		return roleAccessBean;
	}
	
}
