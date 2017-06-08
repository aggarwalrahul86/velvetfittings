package com.velvet.vo;


public class OrderSearchBean {

	private String item;
	private String orderFromDate;
	private String orderToDate;
	private String clientName;
	
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getOrderFromDate() {
		return orderFromDate;
	}
	public void setOrderFromDate(String orderFromDate) {
		this.orderFromDate = orderFromDate;
	}
	public String getOrderToDate() {
		return orderToDate;
	}
	public void setOrderToDate(String orderToDate) {
		this.orderToDate = orderToDate;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderSearchBean [item=");
		builder.append(item);
		builder.append(", orderFromDate=");
		builder.append(orderFromDate);
		builder.append(", orderToDate=");
		builder.append(orderToDate);
		builder.append(", clientName=");
		builder.append(clientName);
		builder.append("]");
		return builder.toString();
	}
	
}
