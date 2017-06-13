package com.doshr.xmen.backend.dao.mapper;

import org.apache.ibatis.annotations.Param;
import com.doshr.xmen.backend.dao.po.SignInPO;

public interface SignInMapper{

	/**
	 * 获取此学生当天是否签到
	 * @param classId
	 * @param studentId
	 * @return
	 */
	public SignInPO getTodaySignIn(@Param(value = "classId") int classId,@Param(value = "studentId") int studentId);
	
	/**
	 * 签到
	 * @param signInPO
	 * @return
	 */
	public int insertSignIn(SignInPO signInPO);
}
