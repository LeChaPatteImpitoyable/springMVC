package com.doshr.xmen.backend.client.student.request;

import com.doshr.xmen.backend.client.base.BaseRequest;

public class BoundBraceletReq extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5052799660425234872L;

	private String relationIds;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRelationIds() {
		return relationIds;
	}

	public void setRelationIds(String relationIds) {
		this.relationIds = relationIds;
	}
	
	
}
