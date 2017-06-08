package com.velvet.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;

public class ManageItemOrderBean {

	@SuppressWarnings("unchecked")
	public ManageItemOrderBean() {
		addedItems = LazyList.decorate(new ArrayList<ItemOrderBean>(),
				FactoryUtils.instantiateFactory(ItemOrderBean.class));
	}
	
	private List<ItemOrderBean> addedItems;
	private String clientName;
	private String orderDate;
	
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public List<ItemOrderBean> getAddedItems() {
		return addedItems;
	}

	public void setAddedItems(List<ItemOrderBean> addedItems) {
		this.addedItems = addedItems;
	}

	@Override
	public String toString() {
		return "ManageItemOrderBean [addedItems=" + addedItems + "]";
	}

	
	
}
