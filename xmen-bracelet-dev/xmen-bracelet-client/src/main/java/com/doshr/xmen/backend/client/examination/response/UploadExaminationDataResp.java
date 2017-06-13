package com.doshr.xmen.backend.client.examination.response;

import com.doshr.xmen.backend.client.base.BaseResponse;

public class UploadExaminationDataResp extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1907086593425688644L;

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
