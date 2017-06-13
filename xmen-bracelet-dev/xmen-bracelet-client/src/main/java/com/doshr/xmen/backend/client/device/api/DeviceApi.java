package com.doshr.xmen.backend.client.device.api;

import com.doshr.xmen.backend.client.device.request.AddDevicesReq;
import com.doshr.xmen.backend.client.device.response.AddDevicesResp;

public interface DeviceApi {

	public AddDevicesResp addDevices(AddDevicesReq req);
	
}
