package com.ying.client.account.dto;

import java.io.Serializable;

public class AccountDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5057869430678547298L;
	
	private int id;
	private String userName;
	private String headerPath;
	private String account;
	private String sex;
	private String age; // birthday[string type, need modify]
	private String registerDatatime;
	private String loginDatatime;
	private String blacklist;
	private int status;
	private int roleNum;
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public String getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}
	public int getRoleNum() {
		return roleNum;
	}
	public void setRoleNum(int roleNum) {
		this.roleNum = roleNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
