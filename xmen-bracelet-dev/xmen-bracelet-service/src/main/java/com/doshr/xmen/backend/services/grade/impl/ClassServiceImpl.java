package com.doshr.xmen.backend.services.grade.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.doshr.xmen.backend.client.base.RespCode;
import com.doshr.xmen.backend.client.device.dto.DeviceDTO;
import com.doshr.xmen.backend.client.examination.dto.ExaminationDTO;
import com.doshr.xmen.backend.client.grade.dto.ClassDTO;
import com.doshr.xmen.backend.client.grade.dto.GradeDTO;
import com.doshr.xmen.backend.client.grade.dto.StudentDTO;
import com.doshr.xmen.backend.client.monitordata.dto.MonitorDataDTO;
import com.doshr.xmen.backend.common.Constants;
import com.doshr.xmen.backend.common.exception.BackendException;
import com.doshr.xmen.backend.common.util.XmenUtils;
import com.doshr.xmen.backend.dao.mapper.ClassMapper;
import com.doshr.xmen.backend.dao.mapper.RelationMapper;
import com.doshr.xmen.backend.dao.mapper.SignInMapper;
import com.doshr.xmen.backend.dao.mapper.StudentMapper;
import com.doshr.xmen.backend.dao.po.ClassPO;
import com.doshr.xmen.backend.dao.po.RelationPO;
import com.doshr.xmen.backend.dao.po.SignInPO;
import com.doshr.xmen.backend.dao.po.StudentPO;
import com.doshr.xmen.backend.services.device.IDeviceService;
import com.doshr.xmen.backend.services.examination.IExaminationService;
import com.doshr.xmen.backend.services.grade.IClassService;
import com.doshr.xmen.backend.services.monitordata.IMonitorDataService;
import com.doshr.xmen.backend.services.student.IStudentService;

public class ClassServiceImpl implements IClassService {

	private static final Logger LOG = LoggerFactory.getLogger(ClassServiceImpl.class);
	
	@Autowired
	private ClassMapper classMapper;
	
	@Autowired
	private RelationMapper relationMapper;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private IDeviceService deviceService;
	
	@Autowired
	private SignInMapper signInMapper;
	
	@Autowired
	private IExaminationService examinationService;
	
	@Autowired
	private IMonitorDataService monitorDataService;
	
	@Override
	public List<StudentDTO> getAllStudentByClass(int classId) {
		ClassPO classPO = classMapper.getClassById(classId);
		if(classPO == null){
			LOG.error("获取班级所有学生时,班级不存在! classId:"+classId);
			throw new BackendException(RespCode.CLASS_IS_NULL);
		}
		List<RelationPO> relationPOs = relationMapper.getRelationByClassId(classId);
		
		return conver2StudentDTO(classPO, relationPOs);
	}
	
	private List<StudentDTO> conver2StudentDTO(ClassPO classPO,List<RelationPO> relationPOs){
		if(XmenUtils.isEmpty(relationPOs)){
			return null;
		}
		int classId = classPO.getId();
		String className = classPO.getName();
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		for(RelationPO relationPO : relationPOs){
			int studentId = relationPO.getStudentId();
			String braceletNO = relationPO.getBraceletNO();
			StudentPO studentPO = studentMapper.getStudentById(studentId);
			if(studentPO == null){
				continue;
			}
			StudentDTO st = new StudentDTO();
			st.setClassId(classId);
			st.setClassName(className);
			st.setStudentId(studentId);
			st.setStudentName(studentPO.getName());
			st.setStudentNO(studentPO.getStudentNO());
			st.setRelationId(relationPO.getId());
			st.setStatus(relationPO.getStatus());
			st.setBraceletNO(braceletNO);
			st.setAge(studentPO.getAge());
			st.setStature(XmenUtils.changeI2D(studentPO.getStature()));
			st.setWeight(XmenUtils.changeI2D(studentPO.getWeight()));
			st.setSex(studentPO.getSex());
			st.setClas(classPO.getClas());
			st.setGrade(classPO.getGrade());
			SignInPO signInPO  = signInMapper.getTodaySignIn(classId, studentId);
			if(signInPO != null){
				st.setIsSignIn(Constants.IS_SIGN_IN.YES);
			}
			if(!StringUtils.isEmpty(braceletNO)){
				DeviceDTO deviceDTO = deviceService.getDevice(braceletNO);
				st.setDeviceDTO(deviceDTO);
			}
			List<ExaminationDTO> examinationDTOs = examinationService.getExaminationByStudent(classId, studentId);
			st.setExaminationDTOs(examinationDTOs);
			List<MonitorDataDTO> monitorDataDTOs = monitorDataService.getMonitorDataByStudent(classId, studentId);
			st.setMonitorDataDTOs(monitorDataDTOs);
			studentDTOs.add(st);
		}
		return studentDTOs;
	}

	@Override
	public List<GradeDTO> getAllGrade() {
		List<Integer> grades = classMapper.getAllGrade();
		if(XmenUtils.isEmpty(grades)){
			LOG.error("获取所有年级时,还没有年级! ");
			throw new BackendException(RespCode.CLASS_IS_NULL);
		}
		List<GradeDTO> gradeDTOs = new ArrayList<>();
		for(Integer i : grades){
			GradeDTO gradeDTO = new GradeDTO();
			List<ClassPO> classPOs = classMapper.getAllClassByGrade(i);
			List<ClassDTO> classDTOs = new ArrayList<>();
			for(ClassPO classPO : classPOs){
				ClassDTO classDTO = conver2ClassDTO(classPO);
				classDTOs.add(classDTO);
			}
			gradeDTO.setGrade(i);
			gradeDTO.setClassDTOs(classDTOs);
			gradeDTOs.add(gradeDTO);
		}
		return gradeDTOs;
	}
	
	private ClassDTO conver2ClassDTO(ClassPO classPO){
		if(classPO == null){
			return null;
		}
		ClassDTO classDTO = new ClassDTO();
		classDTO.setId(classPO.getId());
		classDTO.setName(classPO.getName());
		return classDTO;
	}

	@Override
	public List<StudentDTO> getAllStudent() {
		List<ClassDTO> classDTOs = getAllClass();
		if(XmenUtils.isEmpty(classDTOs)){
			return null;
		}
		List<StudentDTO> studentDTOs = new ArrayList<>();
		for(ClassDTO classDTO : classDTOs){
			List<StudentDTO> studentDTOs2 = getAllStudentByClass(classDTO.getId());
			if(XmenUtils.isEmpty(studentDTOs2)){
				continue;
			}else{
				studentDTOs.addAll(studentDTOs2);
			}
		}
		return studentDTOs;
	}
	
	private void checkData(List<StudentDTO> studentDTOs){
		String className = studentDTOs.get(0).getClassName();
		if(StringUtils.isEmpty(className)){
			LOG.error("初始化学生数据时,班级不存在! className:"+className);
			throw new BackendException(RespCode.CLASS_IS_NULL);
		}
		boolean b = true;
		String studentNO = "";
		for(StudentDTO studentDTO : studentDTOs){
			String studentNO1 = studentDTO.getStudentNO();
			if(studentNO.equals(studentNO1)){
				LOG.error("初始化学生数据时,班级不存在! studentNO:"+studentNO+" studentNO1:"+studentNO1);
				throw new BackendException(RespCode.CLASS_IS_NULL);
			}
			String className1 = studentDTO.getClassName();
			if(!className.equals(className1)){
				LOG.error("初始化学生数据时,班级不存在! className:"+className);
				throw new BackendException(RespCode.CLASS_NAME_DISAFFINITY_FOR_EXCEL);
			}
			if(b){
				ClassPO classPO = classMapper.getClassByName(className);
				if(classPO != null){
					LOG.error("初始化学生数据时,班级不存在! className:"+className);
					throw new BackendException(RespCode.CLASS_NAME_IS_EXIST);
				}
				b = false;
			}
		}
			
	}

	@Transactional
	@Override
	public int addStudents(List<StudentDTO> studentDTOs) {
		if(XmenUtils.isEmpty(studentDTOs)){
			LOG.error("导入学生数据时,请求参数为空! ");
			throw new BackendException(RespCode.REQUEST_PARAM_IS_NULL);
		}
		checkData(studentDTOs);
		for(StudentDTO studentDTO : studentDTOs){
			String className = studentDTO.getClassName();
			if(studentDTO.getAge() <= 0 || StringUtils.isEmpty(className)){
				continue;
			}
			int clas = studentDTO.getClas();
			int grade = studentDTO.getGrade();
			int classId = 0;
			int studentId = 0;
			String studentNO = studentDTO.getStudentNO();
			ClassPO classPO = classMapper.getClassByName(className);
			if(classPO == null){
				classPO = new ClassPO();
				classPO.setGrade(grade);
				classPO.setClas(clas);
				classPO.setName(studentDTO.getClassName());
				classMapper.insertClass(classPO);
			}
			classId = classPO.getId();
			studentDTO.setClassId(classId);
			StudentPO studentPO = studentMapper.getStudentByNO(studentNO);
			if(studentPO == null){
				studentId = studentService.addStudent(studentDTO);
				if(studentId == Constants.NUMBER_FALSE){
					continue;
				}
				studentDTO.setStudentId(studentId);
				RelationPO relationPO = new RelationPO();
				relationPO.setClassId(classId);
				relationPO.setStudentId(studentId);
				relationMapper.insertRelation(relationPO);
			}else{
				studentId = studentPO.getId();
				RelationPO relationPO = relationMapper.getRelation(classId, studentId);
				if(relationPO == null){
					relationPO = new RelationPO();
					relationPO.setClassId(classId);
					relationPO.setStudentId(studentId);
					relationMapper.insertRelation(relationPO);
				}
				studentDTO.setRelationId(relationPO.getId());
				studentDTO.setStudentId(studentId);
				studentService.updateStudent(studentDTO);
				continue;
			}
		}
		return Constants.NUMBER_TRUE;
	}

	@Override
	public List<ClassDTO> getAllClass() {
		List<ClassPO> classPOs = classMapper.getAllClass();
		if(XmenUtils.isEmpty(classPOs)){
			return null;
		}
		List<ClassDTO> classDTOs = new ArrayList<>();
		for(ClassPO classPO : classPOs){
			ClassDTO classDTO = conver2ClassDTO(classPO);
			classDTOs.add(classDTO);
		}
		return classDTOs;
	}
}
