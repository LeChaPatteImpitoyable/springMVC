package com.doshr.xmen.backend.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.doshr.xmen.backend.dao.po.StudentPO;

public interface StudentMapper{

	public StudentPO getStudentByNO(@Param(value = "studentNO") String studentNO);
	public StudentPO getStudentById(@Param(value = "studentId") int studentId);
	public int updateStudentName(@Param(value = "studentId") int studentId,@Param(value = "name") String name);
	public int updateStudent(StudentPO studentPO);
	public int insertStudent(StudentPO studentPO);
}
