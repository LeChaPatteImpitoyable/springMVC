package com.doshr.xmen.backend.services.monitordata;

import java.util.List;

import com.doshr.xmen.backend.client.monitordata.dto.MonitorDataDTO;

public interface IMonitorDataService {

	
	public int uploadData(List<MonitorDataDTO> monitorDataDTOs);
	
	public List<MonitorDataDTO> getMonitorDataByStudent(int classId, int studentId);
}
