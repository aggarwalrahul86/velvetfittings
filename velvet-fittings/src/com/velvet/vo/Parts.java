package com.velvet.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tbl_part_master")
public class Parts implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name ="part_sl_no")
	private String partsId;
	
	@Column(name = "part_name")
	private String name;
	
	@Column(name = "part_code")
	private String code;
	
	@Column(name = "part_quantity")
	private String quantity;
	
	@Column(name = "part_threshold")
	private String threshold;

	@Column(name = "category_sl_no")
	private String categoryId;
	

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
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
	public String getPartsId() {
		return partsId;
	}
	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}


}
