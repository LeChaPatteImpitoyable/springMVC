package com.doshr.xmen.backend.services.device.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.doshr.xmen.backend.client.base.RespCode;
import com.doshr.xmen.backend.client.device.dto.DeviceDTO;
import com.doshr.xmen.backend.common.Constants;
import com.doshr.xmen.backend.common.exception.BackendException;
import com.doshr.xmen.backend.common.util.XmenUtils;
import com.doshr.xmen.backend.dao.mapper.DeviceMapper;
import com.doshr.xmen.backend.dao.po.DevicePO;
import com.doshr.xmen.backend.services.device.IDeviceService;

public class DeviceServiceImpl implements IDeviceService {

	private static final Logger LOG = LoggerFactory.getLogger(DeviceServiceImpl.class);
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override
	public DeviceDTO getDevice(String braceletNO) {
		
		DevicePO devicePO = deviceMapper.getDeviceByBraceletNO(braceletNO);
		
		return conver2DTO(devicePO);
	}
	
	private DeviceDTO conver2DTO(DevicePO devicePO){
		if(devicePO == null){
			return null;
		}
		DeviceDTO deviceDTO = new DeviceDTO();
		deviceDTO.setBraceletNO(devicePO.getBraceletNO());
		deviceDTO.setMac(devicePO.getMac());
		deviceDTO.setRfid(devicePO.getRfid());
		return deviceDTO;
	}
	
	private void checkData(List<DeviceDTO> deviceDTOs){
		for(DeviceDTO deviceDTO : deviceDTOs){
			String braceletNO = deviceDTO.getBraceletNO();
			if(!XmenUtils.checkBraceletNO(braceletNO)){
				LOG.error("校验设备数据时,设备信息不完整! braceletNO:"+braceletNO);
				throw new BackendException(RespCode.DEVICE_BRACELETNO_ILLEGAL);
			}
			String rfid = deviceDTO.getRfid();
			String mac = deviceDTO.getMac();
			if(StringUtils.isEmpty(braceletNO) || StringUtils.isEmpty(rfid) || StringUtils.isEmpty(mac)){
				LOG.error("校验设备数据时,设备信息不完整! braceletNO:"+braceletNO+" rfid:"+rfid+" mac:"+mac);
				throw new BackendException(RespCode.DEVICE_INCOMPLETE_INFORMATION);
			}
			List<DevicePO> devicePOs = deviceMapper.getDeviceByAll(braceletNO, rfid, mac);
			if(!XmenUtils.isEmpty(devicePOs)){
				LOG.error("校验设备数据时,设备已存在! braceletNO:"+braceletNO+" rfid:"+rfid+" mac:"+mac);
				throw new BackendException(RespCode.DEVICE_IS_EXIST);
			}
			
		}
	}

	@Transactional
	@Override
	public int addDevices(List<DeviceDTO> deviceDTOs){
		if(XmenUtils.isEmpty(deviceDTOs)){
			LOG.error("初始化设备数据时,请求参数为空! ");
			throw new BackendException(RespCode.REQUEST_PARAM_IS_NULL);
		}
		checkData(deviceDTOs);
		for(DeviceDTO deviceDTO : deviceDTOs){
			DevicePO devicePO = conver2PO(deviceDTO);
			deviceMapper.insertDevice(devicePO);
		}
		return Constants.NUMBER_TRUE;
	}
	
	private DevicePO conver2PO(DeviceDTO deviceDTO){
		if(deviceDTO == null){
			return null;
		}
		DevicePO devicePO = new DevicePO();
		devicePO.setBraceletNO(deviceDTO.getBraceletNO());
		devicePO.setMac(deviceDTO.getMac());
		devicePO.setRfid(deviceDTO.getRfid());
		return devicePO;
		
	}
}
