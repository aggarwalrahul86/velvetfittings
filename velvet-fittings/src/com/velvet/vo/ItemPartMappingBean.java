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
@Table(name = "tbl_item_part_mapping")
public class ItemPartMappingBean implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name ="item_part_sl_no")
	private String itemPartId;
	
	@Column(name ="item_sl_no")
	private String itemId;
	
	@Column(name ="part_sl_no")
	private String partId;
	
	@Column(name = "part_quantity")
	private String partQuantity;

	public String getItemPartId() {
		return itemPartId;
	}

	public void setItemPartId(String itemPartId) {
		this.itemPartId = itemPartId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getPartQuantity() {
		return partQuantity;
	}

	public void setPartQuantity(String partQuantity) {
		this.partQuantity = partQuantity;
	}
	
}
