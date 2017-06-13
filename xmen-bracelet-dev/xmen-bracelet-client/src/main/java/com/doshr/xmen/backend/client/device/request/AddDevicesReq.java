package com.doshr.xmen.backend.client.device.request;

import java.util.List;

import com.doshr.xmen.backend.client.base.BaseRequest;
import com.doshr.xmen.backend.client.device.dto.DeviceDTO;

public class AddDevicesReq extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7239686474044462921L;

	private List<DeviceDTO> deviceDTOs;

	public List<DeviceDTO> getDeviceDTOs() {
		return deviceDTOs;
	}

	public void setDeviceDTOs(List<DeviceDTO> deviceDTOs) {
		this.deviceDTOs = deviceDTOs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
