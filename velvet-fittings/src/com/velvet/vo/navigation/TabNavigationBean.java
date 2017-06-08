package com.velvet.vo.navigation;

import java.io.Serializable;
import java.util.Map;

public class TabNavigationBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String tabId;
	private String tabName;
	private String tabNavigationUrl;
	private String landingMenuId;
	private Map<String, MenuNavigationBean> menuAssociations;

	/**
	 * @param tabId
	 * @param tabName
	 * @param tabNavigationUrl
	 * @param landingMenuId
	 * @param menuAssociations
	 */
	public TabNavigationBean(String tabId, String tabName,
			String tabNavigationUrl, String landingMenuId,
			Map<String, MenuNavigationBean> menuAssociations) {
		super();
		this.tabId = tabId;
		this.tabName = tabName;
		this.tabNavigationUrl = tabNavigationUrl;
		this.landingMenuId = landingMenuId;
		this.menuAssociations = menuAssociations;
	}

	/**
	 * @return the tabId
	 */
	public String getTabId() {
		return tabId;
	}
	
	

	/**
	 * @return the tabNavigationUrl
	 */
	public String getTabNavigationUrl() {
		return tabNavigationUrl;
	}

	/**
	 * @return the landingMenuId
	 */
	public String getLandingMenuId() {
		return landingMenuId;
	}


	/**
	 * @return the tabName
	 */
	public String getTabName() {
		return tabName;
	}

	/**
	 * @return the menuAssociations
	 */
	public Map<String, MenuNavigationBean> getMenuAssociations() {
		return menuAssociations;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TabNavigationBean [tabId=" + tabId + ", tabNavigationUrl="
				+ tabNavigationUrl + ", landingMenuId=" + landingMenuId
				+ ", menuAssociations=" + menuAssociations + "]";
	}
	
}
