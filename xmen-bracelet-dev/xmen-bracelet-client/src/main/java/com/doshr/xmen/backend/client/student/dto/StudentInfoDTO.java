package com.doshr.xmen.backend.client.student.dto;

import java.io.Serializable;

public class StudentInfoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6542859090452306062L;
	private int serialId;  //读取excel文件  学生的序号
	private int studentId;
	private int classId;
	private int relationId;
	private int grade;  //年级
	private int clas;  //班级
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getClas() {
		return clas;
	}
	public void setClas(int clas) {
		this.clas = clas;
	}
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getRelationId() {
		return relationId;
	}
	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}
	
	public int getSerialId() {
		return serialId;
	}
	
	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}
	
	
}
