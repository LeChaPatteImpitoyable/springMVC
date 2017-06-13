package com.doshr.xmen.backend.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.doshr.xmen.backend.client.monitordata.api.MonitorDataApi;
import com.doshr.xmen.backend.client.monitordata.dto.MonitorDataDTO;
import com.doshr.xmen.backend.client.monitordata.request.UploadDataReq;

@Controller
@RequestMapping("/monitordata")
public class MonitorDataController extends JsonScreen{
	
	@Autowired
	private MonitorDataApi monitorDataApi;
	
	/**
	 * 上传数据
	 * @param customerId
	 * @param data
	 */
	@RequestMapping("/uploadData")
	public void uploadData(@Param("customerId") int customerId, @Param("data") String data){
		List<MonitorDataDTO> monitorDataDTOs = JSON.parseArray(data, MonitorDataDTO.class);
		UploadDataReq req = new UploadDataReq();
		req.setCustomerId(customerId);
		req.setMonitorDataDTOs(monitorDataDTOs);
		returnResult(monitorDataApi.uploadData(req));
	}


}
