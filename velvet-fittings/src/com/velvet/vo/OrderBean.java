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
@Table(name = "tbl_order")
public class OrderBean implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name ="order_sl_no")
	private String orderId;
	
	@Column(name = "client_name")
	private String clientName;
	
	@Column(name = "order_date")
	private String orderDate;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_on")
	private String createdOn;

	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_on")
	private String modifiedOn;
	
	@Column(name = "logical_delete")
	private String logicalDelete;

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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}


	public String getLogicalDelete() {
		return logicalDelete;
	}

	public void setLogicalDelete(String logicalDelete) {
		this.logicalDelete = logicalDelete;
	}

	public OrderBean(String orderId, String clientName, String orderDate,
			String createdBy, String createdOn, String modifiedBy,
			String modifiedOn, String logicalDelete) {
		super();
		this.orderId = orderId;
		this.clientName = clientName;
		this.orderDate = orderDate;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.modifiedBy = modifiedBy;
		this.modifiedOn = modifiedOn;
		this.logicalDelete = logicalDelete;
	}

	public OrderBean() {
		super();
	}
	
}
