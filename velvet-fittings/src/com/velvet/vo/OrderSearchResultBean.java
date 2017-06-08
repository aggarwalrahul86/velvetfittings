package com.velvet.vo;


public class OrderSearchResultBean {

	private String orderId;
	private String orderDate;
	private String orderName;
	private String clientName;
	private String billId;
	private String billDate;
	
	
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the orderName
	 */
	public String getOrderName() {
		return orderName;
	}
	/**
	 * @param orderName the orderName to set
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}
	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * @return the billId
	 */
	public String getBillId() {
		return billId;
	}
	/**
	 * @param billId the billId to set
	 */
	public void setBillId(String billId) {
		this.billId = billId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderSearchResultBean [orderId=");
		builder.append(orderId);
		builder.append(", orderDate=");
		builder.append(orderDate);
		builder.append(", orderName=");
		builder.append(orderName);
		builder.append(", clientName=");
		builder.append(clientName);
		builder.append(", billId=");
		builder.append(billId);
		builder.append(", billDate=");
		builder.append(billDate);
		builder.append("]");
		return builder.toString();
	}
	
}
