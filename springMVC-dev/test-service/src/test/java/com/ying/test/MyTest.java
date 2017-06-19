package com.ying.test;

import java.io.File;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.ying.client.account.dto.AccountDTO;
import com.ying.common.util.XmenUtils;
import com.ying.services.customer.ICustomerService;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


public class MyTest extends BaseTest{

	private static final Logger LOG = LoggerFactory.getLogger(MyTest.class);
	
	@Autowired
	private ICustomerService customerService;
	
	@Test
	public void test(){
		AccountDTO u = customerService.getCustomer(1);
		System.out.println(u.getMobile());
		LOG.info(u.getUserName());
		System.out.println(JSON.toJSONString(u));
	}
	
	@Test
	public void test3(){
		System.out.println(XmenUtils.checkBraceletNO("An0000"));
	}
	
}
