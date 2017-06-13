package com.doshr.xmen.backend.client.grade.response;

import java.util.List;

import com.doshr.xmen.backend.client.base.BaseResponse;
import com.doshr.xmen.backend.client.grade.dto.ClassDTO;

public class GetAllClassAndGradeResp extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -586797288650606314L;
	
	private List<ClassDTO> classDTOs;
	
	public List<ClassDTO> getClassDTOs() {
		return classDTOs;
	}
	public void setClassDTOs(List<ClassDTO> classDTOs) {
		this.classDTOs = classDTOs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
