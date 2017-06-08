
package com.velvet.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NameValuePairBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String code;
	private String desc;
	private String name;

	/**
	 * 
	 */
	public NameValuePairBean() {
		super();
	}

	public NameValuePairBean(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}


	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
}
