package com.doshr.xmen.backend.services.examination.api.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doshr.xmen.backend.client.base.RespCode;
import com.doshr.xmen.backend.client.base.RespStatus;
import com.doshr.xmen.backend.client.examination.api.ExaminationApi;
import com.doshr.xmen.backend.client.examination.dto.ExaminationDTO;
import com.doshr.xmen.backend.client.examination.request.UploadExaminationDataReq;
import com.doshr.xmen.backend.client.examination.response.UploadExaminationDataResp;
import com.doshr.xmen.backend.common.exception.BackendException;
import com.doshr.xmen.backend.services.examination.IExaminationService;

public class ExaminationApiImpl implements ExaminationApi{
	
	private static final Logger LOG = LoggerFactory.getLogger(ExaminationApiImpl.class);

	@Autowired
	private IExaminationService examinationService;
	
	@Override
	public UploadExaminationDataResp uploadExaminationData(UploadExaminationDataReq req) {
		UploadExaminationDataResp resp = new UploadExaminationDataResp();
		try {
			List<ExaminationDTO> examinationDTOs = req.getExaminationDTOs();
			int result = examinationService.uploadData(examinationDTOs);
			resp.setResult(result);
		} catch (BackendException e) {
			LOG.error(e.getMessage(), e);
			resp.setRespStatus(new RespStatus(e.getRespCode()));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resp.setRespStatus(new RespStatus(RespCode.SYSTEM_ERROR));
		}
		return resp;
	}

	
}
