package com.doshr.xmen.backend.client.student.request;

import com.doshr.xmen.backend.client.base.BaseRequest;
import com.doshr.xmen.backend.client.grade.dto.StudentDTO;

public class UpdateStudentReq extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1362513258718134890L;

	private StudentDTO studentDTO;

	public StudentDTO getStudentDTO() {
		return studentDTO;
	}

	public void setStudentDTO(StudentDTO studentDTO) {
		this.studentDTO = studentDTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
