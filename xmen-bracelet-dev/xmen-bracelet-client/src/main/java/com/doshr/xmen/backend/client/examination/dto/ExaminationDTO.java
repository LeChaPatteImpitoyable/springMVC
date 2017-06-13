package com.doshr.xmen.backend.client.examination.dto;

import java.io.Serializable;

public class ExaminationDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6880958945451696249L;
	
	private int classId;
	private int studentId;
	private String timeStamp;
	private int type;
	private String score;
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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
