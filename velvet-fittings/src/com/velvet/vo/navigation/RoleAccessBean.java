package com.velvet.vo.navigation;

import java.io.Serializable;
import java.util.Map;


public class RoleAccessBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String landingTabId;
	private Map<String, TabNavigationBean> tabAssociations;
	/**
	 * @param roleId
	 * @param landingTabId
	 * @param tabAssociations
	 */
	public RoleAccessBean(String landingTabId,
			Map<String, TabNavigationBean> tabAssociations) {
		super();
		this.landingTabId = landingTabId;
		this.tabAssociations = tabAssociations;
	}
	
	/**
	 * @return the landingTabId
	 */
	public String getLandingTabId() {
		return landingTabId;
	}
	
	/**
	 * @return the tabAssociations
	 */
	public Map<String, TabNavigationBean> getTabAssociations() {
		return tabAssociations;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleAccessBean [landingTabId=" + landingTabId
				+ ", tabAssociations=" + tabAssociations + "]";
	}
		
}
