package com.doshr.xmen.backend.client.student.request;

import com.doshr.xmen.backend.client.base.BaseRequest;

public class UnboundBraceletReq extends BaseRequest{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2139918551908499444L;
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
