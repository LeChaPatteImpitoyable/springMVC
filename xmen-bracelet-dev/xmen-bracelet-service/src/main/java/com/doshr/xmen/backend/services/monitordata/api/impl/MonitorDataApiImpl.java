package com.doshr.xmen.backend.services.monitordata.api.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doshr.xmen.backend.client.base.RespCode;
import com.doshr.xmen.backend.client.base.RespStatus;
import com.doshr.xmen.backend.client.monitordata.api.MonitorDataApi;
import com.doshr.xmen.backend.client.monitordata.dto.MonitorDataDTO;
import com.doshr.xmen.backend.client.monitordata.request.UploadDataReq;
import com.doshr.xmen.backend.client.monitordata.response.UploadDataResp;
import com.doshr.xmen.backend.common.exception.BackendException;
import com.doshr.xmen.backend.services.monitordata.IMonitorDataService;

public class MonitorDataApiImpl implements MonitorDataApi {
	
	private static final Logger LOG = LoggerFactory.getLogger(MonitorDataApiImpl.class);

	@Autowired
	private IMonitorDataService monitorDataService;
	
	@Override
	public UploadDataResp uploadData(UploadDataReq req) {
		UploadDataResp resp = new UploadDataResp();
		try {
			List<MonitorDataDTO> monitorDataDTOs = req.getMonitorDataDTOs();
			int result = monitorDataService.uploadData(monitorDataDTOs);
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
