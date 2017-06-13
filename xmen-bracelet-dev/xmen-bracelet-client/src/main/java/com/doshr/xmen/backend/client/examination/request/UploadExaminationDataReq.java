package com.doshr.xmen.backend.client.examination.request;

import java.util.List;

import com.doshr.xmen.backend.client.base.BaseRequest;
import com.doshr.xmen.backend.client.examination.dto.ExaminationDTO;

public class UploadExaminationDataReq extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6516652324313869922L;
	
	private List<ExaminationDTO> examinationDTOs;

	public List<ExaminationDTO> getExaminationDTOs() {
		return examinationDTOs;
	}

	public void setExaminationDTOs(List<ExaminationDTO> examinationDTOs) {
		this.examinationDTOs = examinationDTOs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
