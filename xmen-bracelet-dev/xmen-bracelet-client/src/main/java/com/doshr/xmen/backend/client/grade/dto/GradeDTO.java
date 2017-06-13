package com.doshr.xmen.backend.client.grade.dto;

import java.io.Serializable;
import java.util.List;

public class GradeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7978618269789218055L;
	
	private int grade;
	private List<ClassDTO> classDTOs;

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ClassDTO> getClassDTOs() {
		return classDTOs;
	}

	public void setClassDTOs(List<ClassDTO> classDTOs) {
		this.classDTOs = classDTOs;
	}

}
