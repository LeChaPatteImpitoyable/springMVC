package com.doshr.xmen.backend.client.device.response;

import com.doshr.xmen.backend.client.base.BaseResponse;

public class AddDevicesResp extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1878579308384269043L;
	
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
