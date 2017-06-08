package com.velvet.vo.navigation;

import java.io.Serializable;

public class UserNavigationBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String userId = null;
	private String roleId = null;
	private String roleType = null;
	private String landingTabId = null;
	private String tabId = null;
	private String tabName = null;
	private String landingMenuId = null;
	private String menuId = null;
	private String menuName = null;
	private String access = null;
	
	
	public UserNavigationBean(String userId, String roleId, String roleType,
			String landingTabId, String tabId, String tabName,
			String landingMenuId, String menuId, String menuName, String access) {
		super();
		this.userId = userId;
		this.roleId = roleId;
		this.roleType = roleType;
		this.landingTabId = landingTabId;
		this.tabId = tabId;
		this.tabName = tabName;
		this.landingMenuId = landingMenuId;
		this.menuId = menuId;
		this.menuName = menuName;
		this.access = access;
	}


	/**
	 * @return the tabName
	 */
	public String getTabName() {
		return tabName;
	}


	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}


	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @return the roleType
	 */
	public String getRoleType() {
		return roleType;
	}
	/**
	 * @return the landingTabId
	 */
	public String getLandingTabId() {
		return landingTabId;
	}
	/**
	 * @return the tabId
	 */
	public String getTabId() {
		return tabId;
	}
	/**
	 * @return the landingMenuId
	 */
	public String getLandingMenuId() {
		return landingMenuId;
	}
	/**
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * @return the access
	 */
	public String getAccess() {
		return access;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserNavigationBean [userId=" + userId + ", roleId=" + roleId
				+ ", roleType=" + roleType + ", landingTabId=" + landingTabId
				+ ", tabId=" + tabId + ", tabName=" + tabName
				+ ", landingMenuId=" + landingMenuId + ", menuId=" + menuId
				+ ", menuName=" + menuName + ", access=" + access + "]";
	}
}
