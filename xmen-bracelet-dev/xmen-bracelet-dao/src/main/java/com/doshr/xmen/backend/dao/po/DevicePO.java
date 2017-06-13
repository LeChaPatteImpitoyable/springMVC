package com.doshr.xmen.backend.dao.po;

import java.util.Date;

public class DevicePO {

	private int id;
	private String braceletNO;//手环编号
	private String rfid;
	private String mac;//蓝牙MAC地址
	private Date createTime;
	private Date modifyTime;
	private int isDelete;
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBraceletNO() {
		return braceletNO;
	}
	public void setBraceletNO(String braceletNO) {
		this.braceletNO = braceletNO;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
}
