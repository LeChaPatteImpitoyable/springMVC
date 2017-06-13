package com.doshr.xmen.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.doshr.xmen.backend.dao.po.DevicePO;

public interface DeviceMapper{

	public DevicePO getDeviceByBraceletNO(@Param(value = "braceletNO") String braceletNO);
	
	public List<DevicePO> getDeviceByAll(@Param(value = "braceletNO") String braceletNO,@Param(value = "rfid") String rfid,@Param(value = "mac") String mac);

	public int insertDevice(DevicePO devicePO);
}
