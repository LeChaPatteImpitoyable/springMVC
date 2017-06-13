package com.doshr.xmen.backend.client.grade.response;

import com.doshr.xmen.backend.client.base.BaseResponse;

public class AddStudentsResp extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 249736332512684346L;

	private int result;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
