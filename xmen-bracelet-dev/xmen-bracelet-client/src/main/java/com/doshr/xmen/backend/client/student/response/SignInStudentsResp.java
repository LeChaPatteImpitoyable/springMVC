package com.doshr.xmen.backend.client.student.response;

import com.doshr.xmen.backend.client.base.BaseResponse;

public class SignInStudentsResp extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3512343102842731768L;

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
