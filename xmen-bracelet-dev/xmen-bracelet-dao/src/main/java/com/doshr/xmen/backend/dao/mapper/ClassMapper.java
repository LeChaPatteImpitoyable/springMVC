package com.doshr.xmen.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.doshr.xmen.backend.dao.po.ClassPO;

public interface ClassMapper{

	public int insertClass(ClassPO classPO);
	/**
	 * 根据id获取某个班级
	 * @param classId
	 * @return
	 */
	public ClassPO getClassById(@Param(value = "classId") int classId);
	
	/**
	 * 获取某个年级的所有班级
	 * @param grade
	 * @return
	 */
	public List<ClassPO> getAllClassByGrade(@Param(value = "grade") int grade);
	
	/**
	 * 获取所有班级
	 * @return
	 */
	public List<ClassPO> getAllClass();
	
	/**
	 * 根据班级名称获取班级
	 * @param name
	 * @return
	 */
	public ClassPO getClassByName(@Param(value = "name") String name);
	
	/**
	 * 获取所有年级
	 * @return
	 */
	public List<Integer> getAllGrade();
	
	/**
	 * 根据年级、班级获取班级信息
	 * @param clas
	 * @param grade
	 * @return
	 */
	public ClassPO getClassByClaAndGrade(@Param(value = "clas") int clas, @Param(value = "grade") int grade);
}
