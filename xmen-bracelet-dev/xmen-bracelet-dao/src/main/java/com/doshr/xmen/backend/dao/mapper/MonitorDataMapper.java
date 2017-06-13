package com.doshr.xmen.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.doshr.xmen.backend.dao.po.MonitorDataPO;

public interface MonitorDataMapper{

	public List<MonitorDataPO> getMonitorData(@Param(value = "studentId") int studentId,@Param(value = "classId") int classId);
	public List<MonitorDataPO> getMonitorDataByUUID(@Param(value = "uuid") String uuid);
	public int insertMonitorData(MonitorDataPO monitorDataPO);
}
