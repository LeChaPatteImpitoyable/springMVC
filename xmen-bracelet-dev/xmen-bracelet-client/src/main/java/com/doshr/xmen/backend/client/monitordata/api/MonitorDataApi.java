package com.doshr.xmen.backend.client.monitordata.api;

import com.doshr.xmen.backend.client.monitordata.request.UploadDataReq;
import com.doshr.xmen.backend.client.monitordata.response.UploadDataResp;

public interface MonitorDataApi {

	/**
	 * 上传运动数据
	 * @param req
	 * @return
	 */
	public UploadDataResp uploadData(UploadDataReq req);
}
