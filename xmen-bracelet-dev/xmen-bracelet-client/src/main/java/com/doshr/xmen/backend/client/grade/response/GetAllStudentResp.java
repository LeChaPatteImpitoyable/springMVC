package com.doshr.xmen.backend.client.grade.response;

import java.util.List;

import com.doshr.xmen.backend.client.base.BaseResponse;
import com.doshr.xmen.backend.client.grade.dto.StudentDTO;

public class GetAllStudentResp extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4844112296092330537L;
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
