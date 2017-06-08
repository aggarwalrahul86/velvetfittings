/*
 * @(#)MessageBean.java        1.0 Jan 6, 2012
 *
 * Copyright (c) 2012 EGIL Project Governance System.
 * All rights reserved.
 *
 * Revision History
 * 
 * Revision   Date          Author        Description
 * ======================================================================
 * 
 */
package com.velvet.vo;

import java.io.Serializable;

import com.velvet.utils.StringUtils;


/**
 * MessageBean
 * 
 * @version 1.0 Jan 6, 2012
 * @author Ericsson
 */
public class MessageBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	

	public static final int CONFIRMATION = 3;
	public static final String CONFIRMATION_HEADER = "Confirmation";
	public static final String CONFIRMATION_STYLE = "message confirmation";

	public static final int ERROR = 1;
	public static final String ERROR_HEADER = "Error(s) Occured";
	public static final String ERROR_STYLE = "message error";

	public static final int INFO = 0;
	public static final String INFO_HEADER = "Information";
	public static final String INFO_STYLE = "message info";

	public static final int WARNING = 2;
	public static final String WARNING_HEADER = "Warning";
	public static final String WARNING_STYLE = "message warning";

	private String message = null;
	private int severity = 0;

	/**
	 * @param severity
	 * @param description
	 */
	public MessageBean(int severity, String description) {
		this.severity = severity;
		this.message = description;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @return the severity
	 */
	public int getSeverity() {
		return this.severity;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param severity
	 *            the severity to set
	 */
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	
	/**
	 * @param severity
	 * @param description
	 * @param arguments
	 */
	public MessageBean(int severity, String description, String[] arguments) {
		this.severity = severity;
		this.message = StringUtils.Trim(description);
		if (arguments != null && arguments.length > 0) {
			this.message = StringUtils.FormatMessage(this.message, arguments);
		}
	}

}
