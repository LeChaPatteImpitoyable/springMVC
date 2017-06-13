package com.doshr.xmen.backend.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doshr.xmen.backend.client.grade.api.ClassApi;
import com.doshr.xmen.backend.client.grade.request.GetAllClassAndGradeReq;
import com.doshr.xmen.backend.client.grade.request.GetAllStudentReq;

@Controller
@RequestMapping("/class")
public class ClassController extends JsonScreen{
	@Autowired
	private ClassApi classApi;
	
	@RequestMapping("/getAllStudent")
	public void getAllStudent(@Param("customerId") int customerId){
		GetAllStudentReq req = new GetAllStudentReq();
		req.setCustomerId(customerId);
		returnResult(classApi.getAllStudent(req));
	}
	
	@RequestMapping("/getAllStudentByClass")
	public void getAllStudentByClass(@Param("customerId") int customerId,@Param("classId") int classId){
		GetAllStudentReq req = new GetAllStudentReq();
		req.setCustomerId(customerId);
		req.setClassId(classId);
		returnResult(classApi.getAllStudentByClass(req));
	}
	
	@RequestMapping("/getAllClass")
	public void getAllClassAndGrade(@Param("customerId") int customerId){
		GetAllClassAndGradeReq req = new GetAllClassAndGradeReq();
		req.setCustomerId(customerId);
		returnResult(classApi.getAllClassAndGrade(req));
	}
	
	
}
