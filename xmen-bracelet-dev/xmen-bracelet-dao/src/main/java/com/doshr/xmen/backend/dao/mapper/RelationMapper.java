package com.doshr.xmen.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.doshr.xmen.backend.dao.po.RelationPO;

public interface RelationMapper{

	/**
	 * 获取某个学生的绑定
	 * @param classId
	 * @param studentId
	 * @return
	 */
	public RelationPO getRelation(@Param(value = "classId") int classId,@Param(value = "studentId") int studentId);
	
	public RelationPO getRelationById(@Param(value = "id") int id);
	/**
	 * 获取某个班级的所有学生
	 * @param classId
	 * @return
	 */
	public List<RelationPO> getRelationByClassId(@Param(value = "classId") int classId);
	/**
	 * 绑定关系
	 * @param relationPO
	 * @return
	 */
	public int insertRelation(RelationPO relationPO);
	
	/**
	 * 根据设备查看绑定的学生
	 * @param braceletNO
	 * @return
	 */
	public RelationPO getRelationByBraceletNO(@Param(value = "braceletNO") String braceletNO);
	
	/**
	 * 修改学生关系
	 * @param relationPO
	 * @return
	 */
	public int updateRelation(RelationPO relationPO);
	
	/**
	 * 绑定设备
	 * @param id
	 * @return
	 */
	public int boundBracelet(@Param(value = "id") int id);
	
	/**
	 * 解绑设备
	 * @param id
	 * @return
	 */
	public int unboundBracelet(@Param(value = "id") int id);
}
