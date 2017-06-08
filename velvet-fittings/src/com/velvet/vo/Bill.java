package com.velvet.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bill implements Serializable{
	
	private String billNo;
	
	private String billDate;
	
	private String totalAmnt;
	
	private String dscntAmnt;
	
	private String discountAmount;
	
	private String taxAmnt;
	
	private String netAmount;
	
	private Order order;

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getTotalAmnt() {
		return totalAmnt;
	}

	public void setTotalAmnt(String totalAmnt) {
		this.totalAmnt = totalAmnt;
	}

	public String getDscntAmnt() {
		return dscntAmnt;
	}

	public void setDscntAmnt(String dscntAmnt) {
		this.dscntAmnt = dscntAmnt;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getTaxAmnt() {
		return taxAmnt;
	}

	public void setTaxAmnt(String taxAmnt) {
		this.taxAmnt = taxAmnt;
	}
		
}
