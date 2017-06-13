package com.doshr.xmen.backend.client.device.dto;

import java.io.Serializable;

public class DeviceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6090149054080524231L;

	private int serialId;
	private String braceletNO;
	private String rfid;
	private String mac;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getSerialId() {
		return serialId;
	}
	
	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}
	
	
}
