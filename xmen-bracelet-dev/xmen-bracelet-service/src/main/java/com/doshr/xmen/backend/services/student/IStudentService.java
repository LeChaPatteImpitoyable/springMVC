package com.doshr.xmen.backend.services.student;

import java.util.List;

import com.doshr.xmen.backend.client.grade.dto.StudentDTO;
import com.doshr.xmen.backend.client.student.dto.StudentInfoDTO;

public interface IStudentService {

	/**
	 * 编辑学生信息
	 * @param studentDTO
	 * @return
	 */
	public int updateStudent(StudentDTO studentDTO);
	
	/**
	 * 绑定手环
	 * @param studentId
	 * @param classId
	 * @param braceletNO
	 * @return
	 */
	public int boundBracelet(int[] relationIds);
	
	/**
	 * 解除绑定手环
	 * @param relationId
	 * @return
	 */
	public int unboundBracelet(int[] relationIds);
	
	/**
	 * 获取学生详情
	 * @param classId
	 * @param studentId
	 * @return
	 */
	public StudentDTO getStudentDetail(int classId, int studentId);
	
	/**
	 * 学生签到
	 */
	public int signInStudents(List<StudentInfoDTO> studentInfoDTOs);
	
	/**
	 * 添加一个学生
	 * @param studentDTO
	 * @return
	 */
	public int addStudent(StudentDTO studentDTO);
	
}
