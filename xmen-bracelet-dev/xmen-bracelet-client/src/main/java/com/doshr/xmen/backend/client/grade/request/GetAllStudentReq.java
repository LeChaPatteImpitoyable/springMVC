package com.doshr.xmen.backend.client.grade.request;

import com.doshr.xmen.backend.client.base.BaseRequest;

public class GetAllStudentReq extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4162006631678299203L;
	private int classId;
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
