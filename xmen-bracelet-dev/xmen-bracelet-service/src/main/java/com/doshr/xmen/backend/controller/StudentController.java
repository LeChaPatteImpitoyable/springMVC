package com.doshr.xmen.backend.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.doshr.xmen.backend.client.grade.dto.StudentDTO;
import com.doshr.xmen.backend.client.student.api.StudentApi;
import com.doshr.xmen.backend.client.student.dto.StudentInfoDTO;
import com.doshr.xmen.backend.client.student.request.BoundBraceletReq;
import com.doshr.xmen.backend.client.student.request.SignInStudentsReq;
import com.doshr.xmen.backend.client.student.request.UnboundBraceletReq;
import com.doshr.xmen.backend.client.student.request.UpdateStudentReq;

@Controller
@RequestMapping("/student")
public class StudentController extends JsonScreen{

	@Autowired
	private StudentApi studentApi;
	
	/**
	 * 修改学生信息
	 * @param customerId
	 * @param data
	 */
	@RequestMapping("/updateStudent")
	public void updateStudent(@Param("customerId") int customerId, @Param("data") String data){
		StudentDTO studentDTO = JSON.parseObject(data,StudentDTO.class);
		UpdateStudentReq req = new UpdateStudentReq();
		req.setCustomerId(customerId);
		req.setStudentDTO(studentDTO);
		returnResult(studentApi.updateStudent(req));
	}
	
	/**
	 * 设备绑定
	 * @param customerId
	 * @param data
	 */
	@RequestMapping("/boundBracelet")
	public void boundBracelet(@Param("customerId") int customerId, @Param("data") String data){
		BoundBraceletReq req = new BoundBraceletReq();
		req.setRelationIds(data);
		returnResult(studentApi.boundBracelet(req));
	}
	
	/**
	 * 解绑设备
	 * @param customerId
	 * @param data
	 */
	@RequestMapping("/unboundBracelet")
	public void unboundBracelet(@Param("customerId") int customerId, @Param("data") String data){
		UnboundBraceletReq req = new UnboundBraceletReq();
		req.setRelationIds(data);
		returnResult(studentApi.unboundBracelet(req));
	}
	
	/**
	 * 签到
	 * @param customerId
	 * @param data
	 */
	@RequestMapping("/signIn")
	public void signInStudents(@Param("customerId") int customerId, @Param("data") String data){
		List<StudentInfoDTO> studentInfoDTOs = JSON.parseArray(data, StudentInfoDTO.class);
		SignInStudentsReq req = new SignInStudentsReq();
		req.setCustomerId(customerId);
		req.setStudentInfoDTOs(studentInfoDTOs);
		returnResult(studentApi.signInStudents(req));
	}
}
