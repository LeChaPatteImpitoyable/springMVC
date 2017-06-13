package com.doshr.xmen.backend.client.monitordata.dto;

import java.io.Serializable;

public class MonitorDataDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1449496402499104474L;

	private int classId;
	private int studentId;
	private String timeStamp;
	private int type;//1心率 2运动 3睡眠
	private int subType;
	private int num;
	private String uuid;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSubType() {
		return subType;
	}
	public void setSubType(int subType) {
		this.subType = subType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
}
