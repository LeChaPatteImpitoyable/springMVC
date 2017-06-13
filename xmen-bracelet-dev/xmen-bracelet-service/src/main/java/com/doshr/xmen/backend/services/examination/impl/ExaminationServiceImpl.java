package com.doshr.xmen.backend.services.examination.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.doshr.xmen.backend.client.base.RespCode;
import com.doshr.xmen.backend.client.examination.dto.ExaminationDTO;
import com.doshr.xmen.backend.common.Constants;
import com.doshr.xmen.backend.common.exception.BackendException;
import com.doshr.xmen.backend.common.util.XmenUtils;
import com.doshr.xmen.backend.dao.mapper.ClassMapper;
import com.doshr.xmen.backend.dao.mapper.ExaminationMapper;
import com.doshr.xmen.backend.dao.mapper.StudentMapper;
import com.doshr.xmen.backend.dao.po.ClassPO;
import com.doshr.xmen.backend.dao.po.ExaminationPO;
import com.doshr.xmen.backend.dao.po.StudentPO;
import com.doshr.xmen.backend.services.examination.IExaminationService;

public class ExaminationServiceImpl implements IExaminationService {

	private static final Logger LOG = LoggerFactory.getLogger(ExaminationServiceImpl.class);
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private ClassMapper classMapper;
	
	@Autowired
	private ExaminationMapper examinationMapper;
	
	@Transactional
	@Override
	public int uploadData(List<ExaminationDTO> examinationDTOs) {
		if(XmenUtils.isEmpty(examinationDTOs)){
			LOG.error("上传考核数据时,请求参数为空! ");
			throw new BackendException(RespCode.REQUEST_PARAM_IS_NULL);
		}
		checkData(examinationDTOs);
		for(ExaminationDTO examinationDTO : examinationDTOs){
			ExaminationPO examinationPO = conver2PO(examinationDTO);
			examinationMapper.insertExamination(examinationPO);
		}
		return Constants.NUMBER_TRUE;
	}
	
	private ExaminationPO conver2PO(ExaminationDTO examinationDTO){
		if(examinationDTO == null){
			return null;
		}
		int type = examinationDTO.getType();
		String score = examinationDTO.getScore();
		if(StringUtils.isEmpty(score)){
			score = "0";
		}
		ExaminationPO ex = new ExaminationPO();
		ex.setClassId(examinationDTO.getClassId());
		ex.setStudentId(examinationDTO.getStudentId());
		ex.setTimeStamp(XmenUtils.stringToDateTime(examinationDTO.getTimeStamp(), XmenUtils.TIME_DISPLAY));
		ex.setType(type);
		if(type == Constants.EXAMINATION_TYPE.SKIP || type == Constants.EXAMINATION_TYPE.SIT_UP){
			ex.setScore(Integer.valueOf(score));
		}else if(type == Constants.EXAMINATION_TYPE.SEATED_PRECURSOR){
			ex.setScore(XmenUtils.changeD2I(score));
		}else{
			ex.setScore(XmenUtils.changeYuan2Fen(score));
		}
		return ex;
	}

	private void checkData(List<ExaminationDTO> examinationDTOs){
		int classId = 0;
		int studentId = 0;
//		String uuid = null;
		for(ExaminationDTO examinationDTO : examinationDTOs){
//			String uuid1 = examinationDTO.getUuid();
//			if(StringUtils.isEmpty(uuid1)){
//				LOG.error("上传考核数据时,班级不存在! classId:"+classId+",uuid:"+uuid1);
//				throw new BackendException(RespCode.UUID_IS_NULL);
//			}
//			if(!uuid.equals(uuid1)){
//				List<ExaminationPO> examinationPOs = examinationMapper.getExaminationByUUID(uuid1);
//				if(XmenUtils.isEmpty(examinationPOs)){
//					LOG.error("上传考核数据时,班级不存在! classId:"+classId+",uuid:"+uuid1);
//					throw new BackendException(RespCode.UUID_IS_EXIST);
//				}else{
//					uuid = uuid1;
//				}
//			}
			int classId1 = examinationDTO.getClassId();
			if(classId1 == 0){
				LOG.error("上传考核数据时,班级不存在! classId:"+classId);
				throw new BackendException(RespCode.CLASS_IS_NULL);
			}
			if(classId != classId1){
				ClassPO classPO = classMapper.getClassById(classId1);
				if(classPO == null){
					LOG.error("上传考核数据时,班级不存在! classId:"+classId1);
					throw new BackendException(RespCode.CLASS_IS_NULL);
				}else{
					classId = classId1;
				}
			}
			int studentId1 = examinationDTO.getStudentId();
			if(studentId1 == 0){
				LOG.error("上传考核数据时,学生不存在! classId:"+classId+" studentId:"+studentId1);
				throw new BackendException(RespCode.STUDENT_IS_NULL);
			}
			if(studentId != studentId1){
				StudentPO studentPO = studentMapper.getStudentById(studentId1);
				if(studentPO == null){
					LOG.error("上传考核数据时,学生不存在! classId:"+classId+" studentId:"+studentId1);
					throw new BackendException(RespCode.STUDENT_IS_NULL);
				}else{
					studentId = studentId1;
				}
			}
		}
	}

	@Override
	public List<ExaminationDTO> getExaminationByStudent(int classId, int studentId) {
		List<ExaminationPO> examinationPOs = examinationMapper.getExamination(classId, studentId);
		if(XmenUtils.isEmpty(examinationPOs)){
			return null;
		}
		List<ExaminationDTO> examinationDTOs = new ArrayList<ExaminationDTO>();
		for(ExaminationPO examinationPO : examinationPOs){
			ExaminationDTO examinationDTO = conver2DTO(examinationPO);
			examinationDTOs.add(examinationDTO);
		}
		return examinationDTOs;
	}
	
	private ExaminationDTO conver2DTO(ExaminationPO examinationPO){
		if(examinationPO == null){
			return null;
		}
		int type = examinationPO.getType();
		int score = examinationPO.getScore();
		ExaminationDTO examinationDTO = new ExaminationDTO();
		examinationDTO.setClassId(examinationPO.getClassId());
		examinationDTO.setStudentId(examinationPO.getStudentId());
		examinationDTO.setTimeStamp(XmenUtils.formatDate(examinationPO.getTimeStamp(), XmenUtils.DATE_FORMAT_SECONDS));
		examinationDTO.setType(type);
		examinationDTO.setUuid(examinationPO.getUuid());
		if(type == Constants.EXAMINATION_TYPE.SKIP || type == Constants.EXAMINATION_TYPE.SIT_UP){
			examinationDTO.setScore(String.valueOf(score));
		}else if(type == Constants.EXAMINATION_TYPE.SEATED_PRECURSOR){
			examinationDTO.setScore(XmenUtils.changeI2D(score));
		}else{
			examinationDTO.setScore(XmenUtils.changeFen2Yuan(score));
		}
		return examinationDTO;
	}
}
