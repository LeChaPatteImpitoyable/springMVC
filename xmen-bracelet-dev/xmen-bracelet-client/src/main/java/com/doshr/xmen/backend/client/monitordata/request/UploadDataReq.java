package com.doshr.xmen.backend.client.monitordata.request;

import java.util.List;

import com.doshr.xmen.backend.client.base.BaseRequest;
import com.doshr.xmen.backend.client.monitordata.dto.MonitorDataDTO;

public class UploadDataReq extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6581743367207346917L;
	
	private List<MonitorDataDTO> monitorDataDTOs;

	public List<MonitorDataDTO> getMonitorDataDTOs() {
		return monitorDataDTOs;
	}

	public void setMonitorDataDTOs(List<MonitorDataDTO> monitorDataDTOs) {
		this.monitorDataDTOs = monitorDataDTOs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
