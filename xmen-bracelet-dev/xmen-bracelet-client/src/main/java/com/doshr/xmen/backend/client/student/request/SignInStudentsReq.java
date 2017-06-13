package com.doshr.xmen.backend.client.student.request;

import java.util.List;

import com.doshr.xmen.backend.client.base.BaseRequest;
import com.doshr.xmen.backend.client.student.dto.StudentInfoDTO;

public class SignInStudentsReq extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3880906753920324028L;

	private List<StudentInfoDTO> studentInfoDTOs;

	public List<StudentInfoDTO> getStudentInfoDTOs() {
		return studentInfoDTOs;
	}

	public void setStudentInfoDTOs(List<StudentInfoDTO> studentInfoDTOs) {
		this.studentInfoDTOs = studentInfoDTOs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
