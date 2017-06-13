package com.doshr.xmen.backend.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.doshr.xmen.backend.client.examination.api.ExaminationApi;
import com.doshr.xmen.backend.client.examination.dto.ExaminationDTO;
import com.doshr.xmen.backend.client.examination.request.UploadExaminationDataReq;

@Controller
@RequestMapping("/examination")
public class ExaminationController extends JsonScreen{
	
	@Autowired
	private ExaminationApi examinationApi;
	
	/**
	 * 上传数据
	 * @param customerId
	 * @param data
	 */
	@RequestMapping("/uploadData")
	public void uploadData(@Param("customerId") int customerId, @Param("data") String data){
		List<ExaminationDTO> examinationDTOs = JSON.parseArray(data, ExaminationDTO.class);
		UploadExaminationDataReq req = new UploadExaminationDataReq();
		req.setCustomerId(customerId);
		req.setExaminationDTOs(examinationDTOs);
		returnResult(examinationApi.uploadExaminationData(req));
	}


}
