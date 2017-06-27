package com.ying.dao.po;

import java.util.Date;

public class CustomerPO {

	private int id;
	private String userName;
	private String password;
	private String headerPath;
	private String loginAccount;
	private String sex;
	private String age;
	private Date createTime;
	private Date modifyTime;
	private String token;
	private Date loginDatatime;
	private int status;
	private String deviceToken;
	private int roleNum;
	private int isDelete;// 是否删除
	private String openId;//微信公众号中获取到的id

	public int getRoleNum() {
		return roleNum;
	}

	public void setRoleNum(int roleNum) {
		this.roleNum = roleNum;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeaderPath() {
		return headerPath;
	}

	public void setHeaderPath(String headerPath) {
		this.headerPath = headerPath;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getLoginDatatime() {
		return loginDatatime;
	}

	public void setLoginDatatime(Date loginDatatime) {
		this.loginDatatime = loginDatatime;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
}
