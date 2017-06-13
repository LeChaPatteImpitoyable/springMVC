package com.doshr.xmen.backend.services.grade.api.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doshr.xmen.backend.client.base.RespCode;
import com.doshr.xmen.backend.client.base.RespStatus;
import com.doshr.xmen.backend.client.grade.api.ClassApi;
import com.doshr.xmen.backend.client.grade.dto.ClassDTO;
import com.doshr.xmen.backend.client.grade.dto.StudentDTO;
import com.doshr.xmen.backend.client.grade.request.AddStudentsReq;
import com.doshr.xmen.backend.client.grade.request.GetAllClassAndGradeReq;
import com.doshr.xmen.backend.client.grade.request.GetAllStudentReq;
import com.doshr.xmen.backend.client.grade.response.AddStudentsResp;
import com.doshr.xmen.backend.client.grade.response.GetAllClassAndGradeResp;
import com.doshr.xmen.backend.client.grade.response.GetAllStudentResp;
import com.doshr.xmen.backend.common.exception.BackendException;
import com.doshr.xmen.backend.services.grade.IClassService;

public class ClassApiImpl implements ClassApi {

	private static final Logger LOG = LoggerFactory.getLogger(ClassApiImpl.class);
	
	@Autowired
	private IClassService classService;
	
	@Override
	public GetAllStudentResp getAllStudent(GetAllStudentReq req) {
		GetAllStudentResp resp = new GetAllStudentResp();
		try {
			List<StudentDTO> studentDTOs = classService.getAllStudent();
			resp.setStudentDTOs(studentDTOs);
		} catch (BackendException e) {
			LOG.error(e.getMessage(), e);
			resp.setRespStatus(new RespStatus(e.getRespCode()));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resp.setRespStatus(new RespStatus(RespCode.SYSTEM_ERROR));
		}
		return resp;
	}
	
	@Override
	public GetAllStudentResp getAllStudentByClass(GetAllStudentReq req) {
		GetAllStudentResp resp = new GetAllStudentResp();
		try {
			int classId = req.getClassId();
			List<StudentDTO> studentDTOs = classService.getAllStudentByClass(classId);
			resp.setStudentDTOs(studentDTOs);
		} catch (BackendException e) {
			LOG.error(e.getMessage(), e);
			resp.setRespStatus(new RespStatus(e.getRespCode()));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resp.setRespStatus(new RespStatus(RespCode.SYSTEM_ERROR));
		}
		return resp;
	}

	@Override
	public GetAllClassAndGradeResp getAllClassAndGrade(GetAllClassAndGradeReq req) {
		GetAllClassAndGradeResp resp = new GetAllClassAndGradeResp();
		try {
			List<ClassDTO> classDTOs = classService.getAllClass();
			resp.setClassDTOs(classDTOs);
		} catch (BackendException e) {
			LOG.error(e.getMessage(), e);
			resp.setRespStatus(new RespStatus(e.getRespCode()));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resp.setRespStatus(new RespStatus(RespCode.SYSTEM_ERROR));
		}
		return resp;
	}

	@Override
	public AddStudentsResp addStudents(AddStudentsReq req) {
		AddStudentsResp resp = new AddStudentsResp();
		try {
			List<StudentDTO> studentDTOs = req.getStudentDTOs();
			int result = classService.addStudents(studentDTOs);
			resp.setResult(result);
		} catch (BackendException e) {
			LOG.error(e.getMessage(), e);
			resp.setRespStatus(new RespStatus(e.getRespCode()));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resp.setRespStatus(new RespStatus(RespCode.SYSTEM_ERROR));
		}
		return resp;
	}

}
