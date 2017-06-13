package com.doshr.xmen.backend.client.examination.api;

import com.doshr.xmen.backend.client.examination.request.UploadExaminationDataReq;
import com.doshr.xmen.backend.client.examination.response.UploadExaminationDataResp;

public interface ExaminationApi {

	/**
	 * 上传考核数据
	 * @param req
	 * @return
	 */
	public UploadExaminationDataResp uploadExaminationData(UploadExaminationDataReq req);
}
