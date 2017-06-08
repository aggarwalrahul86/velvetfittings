package com.velvet.vo;

import java.io.Serializable;

import com.velvet.vo.navigation.RoleAccessBean;


public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userID = null;
	private String password = null;
	private String userFullName=null;
	private String email = null;
	private String phone = null;
	private String currentRoleID;
	private String currentRoleName;
	private String statusID;
	private String status;
	private RoleAccessBean roleAccessBean;
	private String roleType;
	private String firstName;
	private String lastName;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	/**
	 * @return the roleAccessBean
	 */
	public RoleAccessBean getRoleAccessBean() {
		return roleAccessBean;
	}

	/**
	 * @param roleAccessBean the roleAccessBean to set
	 */
	public void setRoleAccessBean(RoleAccessBean roleAccessBean) {
		this.roleAccessBean = roleAccessBean;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusID() {
		return statusID;
	}

	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return this.phone;
	}


	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getCurrentRoleID() {
		return currentRoleID;
	}

	public void setCurrentRoleID(String currentRoleID) {
		this.currentRoleID = currentRoleID;
	}

	public String getCurrentRoleName() {
		return currentRoleName;
	}

	public void setCurrentRoleName(String currentRoleName) {
		this.currentRoleName = currentRoleName;
	}

	public UserBean(String userID, String password, String userFullName,
			String email, String phone, String currentRoleID,
			String currentRoleName, String statusID, String status,
			RoleAccessBean roleAccessBean, String roleType,
			String firstName, String lastName) {
		super();
		this.userID = userID;
		this.password = password;
		this.userFullName = userFullName;
		this.email = email;
		this.phone = phone;
		this.currentRoleID = currentRoleID;
		this.currentRoleName = currentRoleName;
		this.statusID = statusID;
		this.status = status;
		this.roleAccessBean = roleAccessBean;
		this.roleType = roleType;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	

	
}
