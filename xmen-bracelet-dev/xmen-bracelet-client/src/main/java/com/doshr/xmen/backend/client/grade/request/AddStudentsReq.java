package com.doshr.xmen.backend.client.grade.request;

import java.util.List;

import com.doshr.xmen.backend.client.base.BaseRequest;
import com.doshr.xmen.backend.client.grade.dto.StudentDTO;

public class AddStudentsReq extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7280623320680868294L;
	
	private List<StudentDTO> studentDTOs;

	public List<StudentDTO> getStudentDTOs() {
		return studentDTOs;
	}

	public void setStudentDTOs(List<StudentDTO> studentDTOs) {
		this.studentDTOs = studentDTOs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
