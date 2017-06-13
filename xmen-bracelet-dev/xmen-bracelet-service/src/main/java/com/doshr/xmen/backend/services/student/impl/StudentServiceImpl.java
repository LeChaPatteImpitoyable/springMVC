package com.doshr.xmen.backend.services.student.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.doshr.xmen.backend.client.base.RespCode;
import com.doshr.xmen.backend.client.device.dto.DeviceDTO;
import com.doshr.xmen.backend.client.grade.dto.StudentDTO;
import com.doshr.xmen.backend.client.student.dto.StudentInfoDTO;
import com.doshr.xmen.backend.common.Constants;
import com.doshr.xmen.backend.common.exception.BackendException;
import com.doshr.xmen.backend.common.util.XmenUtils;
import com.doshr.xmen.backend.dao.mapper.ClassMapper;
import com.doshr.xmen.backend.dao.mapper.DeviceMapper;
import com.doshr.xmen.backend.dao.mapper.RelationMapper;
import com.doshr.xmen.backend.dao.mapper.SignInMapper;
import com.doshr.xmen.backend.dao.mapper.StudentMapper;
import com.doshr.xmen.backend.dao.po.ClassPO;
import com.doshr.xmen.backend.dao.po.DevicePO;
import com.doshr.xmen.backend.dao.po.RelationPO;
import com.doshr.xmen.backend.dao.po.SignInPO;
import com.doshr.xmen.backend.dao.po.StudentPO;
import com.doshr.xmen.backend.services.device.IDeviceService;
import com.doshr.xmen.backend.services.student.IStudentService;

public class StudentServiceImpl implements IStudentService{

	private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	private ClassMapper classMapper;
	
	@Autowired
	private RelationMapper relationMapper;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Autowired
	private SignInMapper signInMapper;
	
	@Autowired
	private IDeviceService deviceService;
	
	@Transactional
	@Override
	public int updateStudent(StudentDTO studentDTO) {
		if(studentDTO == null){
			LOG.error("修改学生信息时,请求参数为空! ");
			throw new BackendException(RespCode.REQUEST_PARAM_IS_NULL);
		}
		int classId = studentDTO.getClassId();
		int studentId = studentDTO.getStudentId();
		String studentNO = studentDTO.getStudentNO();
		String braceletNO = studentDTO.getBraceletNO();
		String studentName = studentDTO.getStudentName();
		int relationId = studentDTO.getRelationId();
		ClassPO classPO = classMapper.getClassById(classId);
		if(classPO == null){
			LOG.error("修改学生信息时,班级不存在! classId:"+classId+" studentId:"+studentId);
			throw new BackendException(RespCode.CLASS_IS_NULL);
		}
		StudentPO studentPO = studentMapper.getStudentByNO(studentNO);
		if(studentPO == null){
			LOG.error("修改学生信息时,学生不存在! classId:"+classId+" studentId:"+studentId);
			throw new BackendException(RespCode.STUDENT_IS_NULL);
		}
		RelationPO relationPO = relationMapper.getRelationById(relationId);
		if(relationPO == null){
			LOG.error("修改学生信息时,未建立学生与设备的关系! classId:"+classId+" studentId:"+studentId+"relationId:"+relationId);
			throw new BackendException(RespCode.RELATION_IS_NULL);
		}
		if(braceletNO != null){
			DevicePO devicePO = deviceMapper.getDeviceByBraceletNO(braceletNO);
			if(devicePO == null){
				LOG.error("修改学生信息时,设备不存在! classId:"+classId+" studentId:"+studentId+" braceletNO:"+braceletNO);
				throw new BackendException(RespCode.DEVICE_IS_NULL);
			}
//			RelationPO relationPO2 = relationMapper.getRelationByBraceletNO(braceletNO);
//			if(relationPO2 != null && relationPO2.getStudentId() != studentId){
//				LOG.error("修改学生信息时,设备已绑定! classId:"+classId+" studentId:"+studentId);
//				throw new BackendException(RespCode.DEVICE_IS_BOUNDED);
//			}
		}
		if(StringUtils.isEmpty(studentName)){
			LOG.error("修改学生信息时,学生姓名不能为空! classId:"+classId+" studentId:"+studentId+" studentName:"+studentName);
			throw new BackendException(RespCode.STUDENT_NAME_IS_NULL);
		}
		studentPO.setName(studentName);
		studentPO.setAge(studentDTO.getAge());
		studentPO.setStature(XmenUtils.changeD2I(studentDTO.getStature()));
		studentPO.setWeight(XmenUtils.changeD2I(studentDTO.getWeight()));
		studentMapper.updateStudent(studentPO);
		relationPO.setBraceletNO(braceletNO);
		relationPO.setClassId(classId);
		relationMapper.updateRelation(relationPO);
		return Constants.NUMBER_TRUE;
	}

	@Transactional
	@Override
	public int boundBracelet(int[] relationIds) {
		if(relationIds == null){
			LOG.error("绑定时,请求参数为空! ");
			throw new BackendException(RespCode.REQUEST_PARAM_IS_NULL);
		}
		for(int relationId : relationIds){
			RelationPO relationPO = relationMapper.getRelationById(relationId);
			if(relationPO == null){
				LOG.error("绑定时,关系不存在! relationId:"+relationId);
				throw new BackendException(RespCode.RELATION_IS_NULL);
			}
			StudentPO studentPO = studentMapper.getStudentById(relationPO.getStudentId());
			if(studentPO == null){
				LOG.error("绑定时,学生不存在! relationId:"+relationId);
				throw new BackendException(RespCode.STUDENT_IS_NULL);
			}
			String braceletNO = relationPO.getBraceletNO();
			if(StringUtils.isEmpty(braceletNO)){
				LOG.error("绑定时,设备不存在,无法绑定! braceletNO:"+braceletNO);
				throw new BackendException(RespCode.DEVICE_IS_NULL);
			}else{
				DevicePO devicePO = deviceMapper.getDeviceByBraceletNO(braceletNO);
				if(devicePO == null){
					LOG.error("绑定时,设备不存在! braceletNO:"+braceletNO);
					throw new BackendException(RespCode.DEVICE_IS_NULL);
				}
				RelationPO relationPO2 = relationMapper.getRelationByBraceletNO(braceletNO);
				if(relationPO2 != null && relationPO2.getStudentId() != relationPO.getStudentId()){
					int[] reIds = new int[1];
					reIds[0] = relationPO2.getId();
					LOG.info("绑定时,设备已绑定,解除绑定! braceletNO:"+braceletNO+" relationId:"+relationPO2.getId());
					unboundBracelet(reIds);
				}
			}
			relationMapper.boundBracelet(relationId);
		}
		return Constants.NUMBER_TRUE;
	}

	@Transactional
	@Override
	public int unboundBracelet(int[] relationIds) {
		if(relationIds == null){
			LOG.error("解除绑定时,请求参数为空! ");
			throw new BackendException(RespCode.REQUEST_PARAM_IS_NULL);
		}
		for(int relationId : relationIds){
			RelationPO relationPO = relationMapper.getRelationById(relationId);
			if(relationPO == null){
				LOG.error("解除绑定时,关系不存在! relationId:"+relationId);
				throw new BackendException(RespCode.RELATION_IS_NULL);
			}
			if(relationPO.getStatus() != Constants.RELATION_STATUS.BOUNDED){
				LOG.error("解除绑定时,设备不是绑定状态! relationId:"+relationId);
				throw new BackendException(RespCode.DEVICE_IS_UNBOUNDED);
			}
			relationMapper.unboundBracelet(relationId);
		}
		return Constants.NUMBER_TRUE;
	}

	@Override
	public StudentDTO getStudentDetail(int classId, int studentId) {
		if(classId <= 0 || studentId <= 0){
			LOG.error("获取学生信息时,请求参数为空! ");
			throw new BackendException(RespCode.REQUEST_PARAM_IS_NULL);
		}
		StudentPO studentPO = studentMapper.getStudentById(studentId);
		return conver2DTO(classId, studentPO);
	}
	
	private StudentDTO conver2DTO(int classId, StudentPO studentPO){
		if(studentPO == null){
			return null;
		}
		StudentDTO st = new StudentDTO();
		ClassPO classPO = classMapper.getClassById(classId);
		st.setClas(classPO.getClas());
		st.setGrade(classPO.getGrade());
		st.setClassId(classId);
		st.setClassName(classPO.getName());
		int studentId = studentPO.getId();
		st.setStudentId(studentId);
		st.setStudentName(studentPO.getName());
		st.setStudentNO(studentPO.getStudentNO());
		RelationPO relationPO = relationMapper.getRelation(classId, studentId);
		st.setRelationId(relationPO.getId());
		st.setStatus(relationPO.getStatus());
		String braceletNO = relationPO.getBraceletNO();
		st.setBraceletNO(braceletNO);
		st.setAge(studentPO.getAge());
		st.setStature(XmenUtils.changeI2D(studentPO.getStature()));
		st.setWeight(XmenUtils.changeI2D(studentPO.getWeight()));
		st.setSex(studentPO.getSex());
		SignInPO signInPO  = signInMapper.getTodaySignIn(classId, studentId);
		if(signInPO != null){
			st.setIsSignIn(Constants.IS_SIGN_IN.YES);
		}
		if(!StringUtils.isEmpty(braceletNO)){
			DeviceDTO deviceDTO = deviceService.getDevice(braceletNO);
			st.setDeviceDTO(deviceDTO);
		}
		return st;
	}

	@Transactional
	@Override
	public int signInStudents(List<StudentInfoDTO> studentInfoDTOs) {
		if(XmenUtils.isEmpty(studentInfoDTOs)){
			LOG.error("学生签到时,请求参数为空! ");
			throw new BackendException(RespCode.REQUEST_PARAM_IS_NULL);
		}
		for(StudentInfoDTO studentInfoDTO : studentInfoDTOs){
			int classId = studentInfoDTO.getClassId();
			int studentId = studentInfoDTO.getStudentId();
			RelationPO relationPO = relationMapper.getRelation(classId, studentId);
			if(relationPO == null){
				LOG.error("学生签到时,设备不是绑定状态! classId:"+classId+",studentId:"+studentId);
				throw new BackendException(RespCode.DEVICE_IS_UNBOUNDED);
			}
			SignInPO signInPO = new SignInPO();
			signInPO.setClassId(classId);
			signInPO.setStudentId(studentId);
			signInMapper.insertSignIn(signInPO);
		}
		return Constants.NUMBER_TRUE;
	}

	private StudentPO conver2PO(StudentDTO studentDTO){
		if(studentDTO == null){
			return null;
		}
		StudentPO studentPO = new StudentPO();
		studentPO.setAge(studentDTO.getAge());
		studentPO.setName(studentDTO.getStudentName());
		studentPO.setStudentNO(studentDTO.getStudentNO());
		studentPO.setSex(studentDTO.getSex());
		studentPO.setStature(XmenUtils.changeD2I(studentDTO.getStature()));
		studentPO.setWeight(XmenUtils.changeD2I(studentDTO.getWeight()));
		return studentPO;
	}
	
	@Transactional
	@Override
	public int addStudent(StudentDTO studentDTO) {
		if(studentDTO == null){
			return Constants.NUMBER_FALSE;
		}
		StudentPO studentPO = conver2PO(studentDTO);
		studentMapper.insertStudent(studentPO);
		return studentPO.getId();
	}

}
