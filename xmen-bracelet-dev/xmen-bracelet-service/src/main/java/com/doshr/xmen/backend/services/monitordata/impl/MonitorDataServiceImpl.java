package com.doshr.xmen.backend.services.monitordata.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;

import com.doshr.xmen.backend.client.base.RespCode;
import com.doshr.xmen.backend.client.monitordata.dto.MonitorDataDTO;
import com.doshr.xmen.backend.common.Constants;
import com.doshr.xmen.backend.common.exception.BackendException;
import com.doshr.xmen.backend.common.util.XmenUtils;
import com.doshr.xmen.backend.dao.mapper.ClassMapper;
import com.doshr.xmen.backend.dao.mapper.MonitorDataMapper;
import com.doshr.xmen.backend.dao.mapper.StudentMapper;
import com.doshr.xmen.backend.dao.po.ClassPO;
import com.doshr.xmen.backend.dao.po.MonitorDataPO;
import com.doshr.xmen.backend.dao.po.StudentPO;
import com.doshr.xmen.backend.services.monitordata.IMonitorDataService;

public class MonitorDataServiceImpl implements IMonitorDataService {

	private static final Logger LOG = LoggerFactory.getLogger(MonitorDataServiceImpl.class);
	
	@Autowired
	private MonitorDataMapper monitorDataMapper;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private ClassMapper classMapper;
	
	@Transactional
	@Override
	public int uploadData(List<MonitorDataDTO> monitorDataDTOs) {
		if(XmenUtils.isEmpty(monitorDataDTOs)){
			LOG.error("上传运动数据时,请求参数为空! ");
			throw new BackendException(RespCode.REQUEST_PARAM_IS_NULL);
		}
		checkData(monitorDataDTOs);
		for(MonitorDataDTO monitorDataDTO : monitorDataDTOs){
			MonitorDataPO monitorDataPO = conver2PO(monitorDataDTO);
			monitorDataMapper.insertMonitorData(monitorDataPO);
		}
		return Constants.NUMBER_TRUE;
	}
	
	private void checkData(List<MonitorDataDTO> monitorDataDTOs){
		int classId = 0;
		int studentId = 0;
//		String uuid = null;
		for(MonitorDataDTO monitorDataDTO : monitorDataDTOs){
//			String uuid1 = monitorDataDTO.getUuid();
//			if(StringUtils.isEmpty(uuid1)){
//				LOG.error("上传考核数据时,班级不存在! classId:"+classId+",uuid:"+uuid1);
//				throw new BackendException(RespCode.UUID_IS_NULL);
//			}
//			if(!uuid.equals(uuid1)){
//				List<MonitorDataPO> monitorDataPOs = monitorDataMapper.getMonitorDataByUUID(uuid1);
//				if(XmenUtils.isEmpty(monitorDataPOs)){
//					LOG.error("上传考核数据时,班级不存在! classId:"+classId+",uuid:"+uuid1);
//					throw new BackendException(RespCode.UUID_IS_EXIST);
//				}else{
//					uuid = uuid1;
//				}
//			}
			int classId1 = monitorDataDTO.getClassId();
			if(classId1 == 0){
				LOG.error("上传数据时,班级不存在! classId:"+classId1);
				throw new BackendException(RespCode.CLASS_IS_NULL);
			}
			if(classId != classId1){
				ClassPO classPO = classMapper.getClassById(classId1);
				if(classPO == null){
					LOG.error("上传数据时,班级不存在! classId:"+classId1);
					throw new BackendException(RespCode.CLASS_IS_NULL);
				}else{
					classId = classId1;
				}
			}
			int studentId1 = monitorDataDTO.getStudentId();
			if(studentId1 == 0){
				LOG.error("上传数据时,学生不存在! classId:"+classId+" studentId:"+studentId1);
				throw new BackendException(RespCode.STUDENT_IS_NULL);
			}
			if(studentId != studentId1){
				StudentPO studentPO = studentMapper.getStudentById(studentId1);
				if(studentPO == null){
					LOG.error("上传数据时,学生不存在! classId:"+classId+" studentId:"+studentId1);
					throw new BackendException(RespCode.STUDENT_IS_NULL);
				}else{
					studentId = studentId1;
				}
			}
		}
	}
	
	private MonitorDataPO conver2PO(MonitorDataDTO monitorDataDTO){
		if(monitorDataDTO == null){
			return null;
		}
		MonitorDataPO monitorDataPO = new MonitorDataPO();
		monitorDataPO.setClassId(monitorDataDTO.getClassId());
		monitorDataPO.setStudentId(monitorDataDTO.getStudentId());
		monitorDataPO.setTimeStamp(XmenUtils.stringToDateTime(monitorDataDTO.getTimeStamp(), XmenUtils.TIME_DISPLAY));
		monitorDataPO.setSubType(monitorDataDTO.getSubType());
		monitorDataPO.setNum(monitorDataDTO.getNum());
		return monitorDataPO;
	}

	@Override
	public List<MonitorDataDTO> getMonitorDataByStudent(int classId, int studentId) {
		List<MonitorDataPO> monitorDataPOs = monitorDataMapper.getMonitorData(studentId, classId);
		if(XmenUtils.isEmpty(monitorDataPOs)){
			return null;
		}
		List<MonitorDataDTO> monitorDataDTOs = new ArrayList<MonitorDataDTO>();
		for(MonitorDataPO monitorDataPO : monitorDataPOs){
			MonitorDataDTO monitorDataDTO = conver2DTO(monitorDataPO);
			monitorDataDTOs.add(monitorDataDTO);
		}
		return monitorDataDTOs;
	}
	
	private MonitorDataDTO conver2DTO(MonitorDataPO monitorDataPO){
		if(monitorDataPO == null){
			return null;
		}
		MonitorDataDTO monitorDataDTO = new MonitorDataDTO();
		monitorDataDTO.setClassId(monitorDataPO.getClassId());
		monitorDataDTO.setStudentId(monitorDataPO.getStudentId());
		monitorDataDTO.setNum(monitorDataPO.getNum());
		monitorDataDTO.setType(monitorDataPO.getType());
		monitorDataDTO.setTimeStamp(XmenUtils.formatDate(monitorDataPO.getTimeStamp(), XmenUtils.DATE_FORMAT_SECONDS));
		monitorDataDTO.setSubType(monitorDataPO.getSubType());
		monitorDataDTO.setUuid(monitorDataPO.getUuid());
		return monitorDataDTO;
	}

}
