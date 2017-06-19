package com.ying.dao.po;

public class CustomerPO {

	private int id;
	private String userName;
	private String password;
	private String headerPath;
	private String mobile;
	private String sex;
	private String age; // birthday[string type, need modify]
	private String introduce;
	// private String friendIds;
	// private String fansIds;
	private String registerDatatime;
	private String token;
	private String loginDatatime;
	// private String nicePostId;
	private String customerType;
	private String blacklist;
	private String idStatus;
	private String deviceToken;
	private String point;

	private int type;// 0用户 1会员

	private int isdelete;// 是否删除
	
	private String openId;//微信公众号中获取到的id

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

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	// public String getFriendIds ( )
	// {
	// return friendIds;
	// }
	//
	// public void setFriendIds ( String friendIds )
	// {
	// this.friendIds = friendIds;
	// }
	//
	// public String getFansIds ( )
	// {
	// return fansIds;
	// }
	//
	// public void setFansIds ( String fansIds )
	// {
	// this.fansIds = fansIds;
	// }

	public String getRegisterDatatime() {
		return registerDatatime;
	}

	public void setRegisterDatatime(String registerDatatime) {
		this.registerDatatime = registerDatatime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLoginDatatime() {
		return loginDatatime;
	}

	public void setLoginDatatime(String loginDatatime) {
		this.loginDatatime = loginDatatime;
	}

	// public String getNicePostId ( )
	// {
	// return nicePostId;
	// }
	//
	// public void setNicePostId ( String nicePostId )
	// {
	// this.nicePostId = nicePostId;
	// }

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

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

}
