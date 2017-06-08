package com.velvet.vo;


public class PartAvailabilityBean {
	
	private String partId;
	private String partName;
	private String partCode;
	private String currentQuantity;
	private String requiredQuantity;
	private String threshold;
	private String categoryId;
	private boolean insufficientParts;
	
	public boolean isInsufficientParts() {
		return insufficientParts;
	}
	public void setInsufficientParts(boolean insufficientParts) {
		this.insufficientParts = insufficientParts;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getPartCode() {
		return partCode;
	}
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	public String getCurrentQuantity() {
		return currentQuantity;
	}
	public void setCurrentQuantity(String currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
	public String getRequiredQuantity() {
		return requiredQuantity;
	}
	public void setRequiredQuantity(String requiredQuantity) {
		this.requiredQuantity = requiredQuantity;
	}
	public String getThreshold() {
		return threshold;
	}
	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	
	public PartAvailabilityBean(String partId, String partName,
			String partCode, String currentQuantity, String requiredQuantity,
			String threshold, String categoryId) {
		super();
		this.partId = partId;
		this.partName = partName;
		this.partCode = partCode;
		this.currentQuantity = currentQuantity;
		this.requiredQuantity = requiredQuantity;
		this.threshold = threshold;
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "PartAvailabilityBean [partId=" + partId + ", partName="
				+ partName + ", partCode=" + partCode + ", currentQuantity="
				+ currentQuantity + ", requiredQuantity=" + requiredQuantity
				+ ", threshold=" + threshold + ", categoryId=" + categoryId
				+ "]";
	}
	
	
}
