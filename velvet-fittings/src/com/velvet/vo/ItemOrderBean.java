package com.velvet.vo;


public class ItemOrderBean { 
	
	private String category;
	private String item;
	private String quantity;
	private String isDeleted ="N";
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public String toString() {
		return "ItemOrderBean [category=" + category + ", item=" + item
				+ ", quantity=" + quantity + ", isDeleted=" + isDeleted + "]";
	}

	
	

}
