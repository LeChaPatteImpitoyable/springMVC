package com.doshr.xmen.backend.client.grade.api;

import com.doshr.xmen.backend.client.grade.request.AddStudentsReq;
import com.doshr.xmen.backend.client.grade.request.GetAllClassAndGradeReq;
import com.doshr.xmen.backend.client.grade.request.GetAllStudentReq;
import com.doshr.xmen.backend.client.grade.response.AddStudentsResp;
import com.doshr.xmen.backend.client.grade.response.GetAllClassAndGradeResp;
import com.doshr.xmen.backend.client.grade.response.GetAllStudentResp;

public interface ClassApi {

	/**
	 * 获取所有学生
	 * @param req
	 * @return
	 */
	public GetAllStudentResp getAllStudent(GetAllStudentReq req);
	
	/**
	 * 获取一个班级的所有学生
	 * @param req
	 * @return
	 */
	public GetAllStudentResp getAllStudentByClass(GetAllStudentReq req);
	
	/**
	 * 获取所有年级班级
	 * @param req
	 * @return
	 */
	public GetAllClassAndGradeResp getAllClassAndGrade(GetAllClassAndGradeReq req);
	
	/**
	 * 上传学生数据
	 * @param req
	 * @return
	 */
	public AddStudentsResp addStudents(AddStudentsReq req);
}
