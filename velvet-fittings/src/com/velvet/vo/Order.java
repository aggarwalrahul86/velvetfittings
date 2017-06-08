package com.velvet.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order implements Serializable{
	
	private String orderId;
	
	private String clientName;
	
	private String orderDate;
	
	private List<ItemsTransaction> itemsList = null;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public List<ItemsTransaction> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<ItemsTransaction> itemsList) {
		this.itemsList = itemsList;
	}

	
}
