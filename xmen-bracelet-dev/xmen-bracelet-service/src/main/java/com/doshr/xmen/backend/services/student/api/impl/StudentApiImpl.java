package com.doshr.xmen.backend.services.student.api.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doshr.xmen.backend.client.base.RespCode;
import com.doshr.xmen.backend.client.base.RespStatus;
import com.doshr.xmen.backend.client.grade.dto.StudentDTO;
import com.doshr.xmen.backend.client.student.api.StudentApi;
import com.doshr.xmen.backend.client.student.dto.StudentInfoDTO;
import com.doshr.xmen.backend.client.student.request.BoundBraceletReq;
import com.doshr.xmen.backend.client.student.request.SignInStudentsReq;
import com.doshr.xmen.backend.client.student.request.UnboundBraceletReq;
import com.doshr.xmen.backend.client.student.request.UpdateStudentReq;
import com.doshr.xmen.backend.client.student.response.BoundBraceletResp;
import com.doshr.xmen.backend.client.student.response.SignInStudentsResp;
import com.doshr.xmen.backend.client.student.response.UnboundBraceletResp;
import com.doshr.xmen.backend.client.student.response.UpdateStudentResp;
import com.doshr.xmen.backend.common.exception.BackendException;
import com.doshr.xmen.backend.common.util.XmenUtils;
import com.doshr.xmen.backend.services.student.IStudentService;

public class StudentApiImpl implements StudentApi {
	
	private static final Logger LOG = LoggerFactory.getLogger(StudentApiImpl.class);

	@Autowired
	private IStudentService studentService;
	
	@Override
	public UpdateStudentResp updateStudent(UpdateStudentReq req) {
		UpdateStudentResp resp = new UpdateStudentResp();
		try {
			StudentDTO studentDTO = req.getStudentDTO();
			int result = studentService.updateStudent(studentDTO);
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

	@Override
	public BoundBraceletResp boundBracelet(BoundBraceletReq req) {
		BoundBraceletResp resp = new BoundBraceletResp();
		try {
			String relationIds = req.getRelationIds();
			int[] reIds = XmenUtils.splitSemstrToArray(relationIds);
			int result = studentService.boundBracelet(reIds);
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

	@Override
	public UnboundBraceletResp unboundBracelet(UnboundBraceletReq req) {
		UnboundBraceletResp resp = new UnboundBraceletResp();
		try {
			String relationIds = req.getRelationIds();
			int[] relIds = XmenUtils.splitSemstrToArray(relationIds);
			int result = studentService.unboundBracelet(relIds);
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

	@Override
	public SignInStudentsResp signInStudents(SignInStudentsReq req) {
		SignInStudentsResp resp = new SignInStudentsResp();
		try {
			List<StudentInfoDTO> studentInfoDTOs = req.getStudentInfoDTOs();
			int result = studentService.signInStudents(studentInfoDTOs);
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
