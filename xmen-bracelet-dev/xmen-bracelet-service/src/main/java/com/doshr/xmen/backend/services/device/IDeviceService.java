package com.doshr.xmen.backend.services.device;

import java.util.List;

import com.doshr.xmen.backend.client.device.dto.DeviceDTO;

public interface IDeviceService {

	public DeviceDTO getDevice(String braceletNO);
	
	/**
	 * 添加设备
	 * @param deviceDTOs
	 * @return
	 */
	public int addDevices(List<DeviceDTO> deviceDTOs);
}
