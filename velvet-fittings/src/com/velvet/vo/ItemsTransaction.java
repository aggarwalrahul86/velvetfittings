package com.velvet.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ItemsTransaction implements Serializable{
	
	private String itemid;
	
	private String itemName;
	
	private String itemCode;
	
	private String itemPrice;
	
	private String itemCategory;
	
	private String transactedQnty;
	
	private String transactedPrice;
	

	public String getTransactedPrice() {
		return transactedPrice;
	}

	public void setTransactedPrice(String transactedPrice) {
		this.transactedPrice = transactedPrice;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getTransactedQnty() {
		return transactedQnty;
	}

	public void setTransactedQnty(String transactedQnty) {
		this.transactedQnty = transactedQnty;
	}                                        
	
	

}
