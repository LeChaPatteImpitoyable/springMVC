package com.doshr.xmen.backend.services.device.api.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doshr.xmen.backend.client.base.RespCode;
import com.doshr.xmen.backend.client.base.RespStatus;
import com.doshr.xmen.backend.client.device.api.DeviceApi;
import com.doshr.xmen.backend.client.device.dto.DeviceDTO;
import com.doshr.xmen.backend.client.device.request.AddDevicesReq;
import com.doshr.xmen.backend.client.device.response.AddDevicesResp;
import com.doshr.xmen.backend.common.exception.BackendException;
import com.doshr.xmen.backend.services.device.IDeviceService;

public class DeviceApiImpl implements DeviceApi {
	
	private static final Logger LOG = LoggerFactory.getLogger(DeviceApiImpl.class);

	@Autowired
	private IDeviceService deviceService;
	
	@Override
	public AddDevicesResp addDevices(AddDevicesReq req) {
		AddDevicesResp resp = new AddDevicesResp();
		try {
			List<DeviceDTO> deviceDTOs = req.getDeviceDTOs();
			int result = deviceService.addDevices(deviceDTOs);
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
