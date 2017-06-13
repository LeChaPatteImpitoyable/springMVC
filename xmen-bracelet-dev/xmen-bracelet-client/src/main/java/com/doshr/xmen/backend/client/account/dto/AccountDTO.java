package com.doshr.xmen.backend.client.account.dto;

import java.io.Serializable;

public class AccountDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5057869430678547298L;
	
	private int id;
	private String userName;
	private String headerPath;
	private String mobile;
	private String sex;
	private String age; // birthday[string type, need modify]
	private String registerDatatime;
	private String loginDatatime;
	private String customerType;
	private String blacklist;
	private String idStatus;
	private int type;// 0用户 1会员
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeaderPath() {
		return headerPath;
	}
	public void setHeaderPath(String headerPath) {
		this.headerPath = headerPath;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getRegisterDatatime() {
		return registerDatatime;
	}
	public void setRegisterDatatime(String registerDatatime) {
		this.registerDatatime = registerDatatime;
	}
	public String getLoginDatatime() {
		return loginDatatime;
	}
	public void setLoginDatatime(String loginDatatime) {
		this.loginDatatime = loginDatatime;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}
	public String getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
