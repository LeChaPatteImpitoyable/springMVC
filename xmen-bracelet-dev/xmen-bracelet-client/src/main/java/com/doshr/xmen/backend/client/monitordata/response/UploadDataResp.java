package com.doshr.xmen.backend.client.monitordata.response;

import com.doshr.xmen.backend.client.base.BaseResponse;

public class UploadDataResp extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6846152681988713113L;

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
