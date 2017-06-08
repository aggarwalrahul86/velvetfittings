package com.velvet.vo.navigation;

import java.io.Serializable;


public class MenuNavigationBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String menuId;
	private String menuName;
	private String menuNavigationUrl;
	
	/**
	 * @param menuId
	 * @param menuName
	 * @param menuNavigationUrl
	 */
	public MenuNavigationBean(String menuId, String menuName,
			String menuNavigationUrl) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuNavigationUrl = menuNavigationUrl;
	}

	/**
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}
	
	

	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @return the menuNavigationUrl
	 */
	public String getMenuNavigationUrl() {
		return menuNavigationUrl;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MenuNavigationBean [menuId=" + menuId + ", menuNavigationUrl="
				+ menuNavigationUrl + "]";
	}

}
