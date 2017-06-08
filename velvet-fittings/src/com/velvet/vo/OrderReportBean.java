package com.velvet.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;


public class OrderReportBean {

	@SuppressWarnings("unchecked")
	public OrderReportBean() {
		orderSearchBean = new OrderSearchBean();
		orderSearchResultBeanList = LazyList.decorate(new ArrayList<OrderSearchResultBean>(),
				FactoryUtils.instantiateFactory(OrderSearchResultBean.class));
		manageItemOrderBean = new ManageItemOrderBean();
	}
	
	private OrderSearchBean orderSearchBean;
	private List<OrderSearchResultBean> orderSearchResultBeanList;
	private ManageItemOrderBean manageItemOrderBean;
	
	private String orderId;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * @return the orderSearchBean
	 */
	public OrderSearchBean getOrderSearchBean() {
		return orderSearchBean;
	}
	/**
	 * @param orderSearchBean the orderSearchBean to set
	 */
	public void setOrderSearchBean(OrderSearchBean orderSearchBean) {
		this.orderSearchBean = orderSearchBean;
	}
	
	/**
	 * @return the orderSearchResultBeanList
	 */
	public List<OrderSearchResultBean> getOrderSearchResultBeanList() {
		return orderSearchResultBeanList;
	}
	/**
	 * @param orderSearchResultBeanList the orderSearchResultBeanList to set
	 */
	public void setOrderSearchResultBeanList(
			List<OrderSearchResultBean> orderSearchResultBeanList) {
		this.orderSearchResultBeanList = orderSearchResultBeanList;
	}
	/**
	 * @return the manageItemOrderBean
	 */
	public ManageItemOrderBean getManageItemOrderBean() {
		return manageItemOrderBean;
	}
	/**
	 * @param manageItemOrderBean the manageItemOrderBean to set
	 */
	public void setManageItemOrderBean(ManageItemOrderBean manageItemOrderBean) {
		this.manageItemOrderBean = manageItemOrderBean;
	}
	
	
	
}
