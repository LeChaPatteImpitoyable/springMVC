package com.doshr.xmen.backend.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.doshr.xmen.backend.dao.po.ExaminationPO;

public interface ExaminationMapper{

	public List<ExaminationPO> getExamination(@Param(value = "classId") int classId,@Param(value = "studentId") int studentId);
	public List<ExaminationPO> getExaminationByUUID(@Param(value = "uuid") String uuid);
	public int insertExamination(ExaminationPO examinationPO);
}
