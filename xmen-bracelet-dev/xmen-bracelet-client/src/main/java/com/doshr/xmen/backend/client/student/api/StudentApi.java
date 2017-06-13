package com.doshr.xmen.backend.client.student.api;

import com.doshr.xmen.backend.client.student.request.BoundBraceletReq;
import com.doshr.xmen.backend.client.student.request.SignInStudentsReq;
import com.doshr.xmen.backend.client.student.request.UnboundBraceletReq;
import com.doshr.xmen.backend.client.student.request.UpdateStudentReq;
import com.doshr.xmen.backend.client.student.response.BoundBraceletResp;
import com.doshr.xmen.backend.client.student.response.SignInStudentsResp;
import com.doshr.xmen.backend.client.student.response.UnboundBraceletResp;
import com.doshr.xmen.backend.client.student.response.UpdateStudentResp;

public interface StudentApi {

	/**
	 * 修改学生信息
	 * @param req
	 * @return
	 */
	public UpdateStudentResp updateStudent(UpdateStudentReq req);
	
	/**
	 * 绑定设备
	 * @param req
	 * @return
	 */
	public BoundBraceletResp boundBracelet(BoundBraceletReq req);
	
	/**
	 * 解绑设备
	 * @param req
	 * @return
	 */
	public UnboundBraceletResp unboundBracelet(UnboundBraceletReq req);
	
	/**
	 * 签到
	 * @param req
	 * @return
	 */
	public SignInStudentsResp signInStudents(SignInStudentsReq req);
}
