package com.doshr.xmen.backend.services.examination;

import java.util.List;

import com.doshr.xmen.backend.client.examination.dto.ExaminationDTO;

public interface IExaminationService {

	/**
	 * 上传考核数据
	 * @param examinationDTOs
	 * @return
	 */
	public int uploadData(List<ExaminationDTO> examinationDTOs);
	
	public List<ExaminationDTO> getExaminationByStudent(int classId, int studentId);
}
