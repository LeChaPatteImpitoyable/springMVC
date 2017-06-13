package com.doshr.xmen.backend.services.grade;

import java.util.List;

import com.doshr.xmen.backend.client.grade.dto.ClassDTO;
import com.doshr.xmen.backend.client.grade.dto.GradeDTO;
import com.doshr.xmen.backend.client.grade.dto.StudentDTO;

public interface IClassService {
	
	/**
	 * 获取某个班级的所有学生(不分页)
	 * @param classId
	 * @return
	 */
	public List<StudentDTO> getAllStudentByClass(int classId);
	
	/**
	 * 获取所有学生
	 * @return
	 */
	public List<StudentDTO> getAllStudent();
	
	/**
	 * 获取所有年级 班级
	 * @return
	 */
	public List<GradeDTO> getAllGrade();
	
	/**
	 * 导入学生数据
	 * @param studentDTOs
	 * @return
	 */
	public int addStudents(List<StudentDTO> studentDTOs);
	
	public List<ClassDTO> getAllClass();
}
