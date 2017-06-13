package com.doshr.xmen.test;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.doshr.xmen.backend.client.account.dto.AccountDTO;
import com.doshr.xmen.backend.client.grade.dto.StudentDTO;
import com.doshr.xmen.backend.common.util.XmenUtils;
import com.doshr.xmen.backend.services.customer.ICustomerService;
import com.doshr.xmen.backend.services.grade.IClassService;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


public class MyTest extends BaseTest{

	private static final Logger LOG = LoggerFactory.getLogger(MyTest.class);
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IClassService classService;
	
	@Test
	public void test(){
		AccountDTO u = customerService.getCustomer(1);
		System.out.println(u.getMobile());
		LOG.info(u.getUserName());
		System.out.println(JSON.toJSONString(u));
	}
	
	@Test
	public void test2(){
		List<StudentDTO> c = classService.getAllStudentByClass(1);
		System.out.println(JSON.toJSONString(c));
		System.out.println(XmenUtils.changeD2I("180.0"));
		System.out.println(XmenUtils.changeI2D(1700));
	}
	
	@Test
	public void test3(){
		System.out.println(XmenUtils.checkBraceletNO("An0000"));
	}
	
	@Test  
	public void test4() {  
	    File imageFile = new File("/home/ying/getVerifyCode.jpg");  
	       Tesseract tessreact = new Tesseract();  
	       tessreact.setDatapath("/home/ying/tesseract/tesseract");  
	       try {  
	    	   System.out.println(imageFile.isFile());
	           String result = tessreact.doOCR(imageFile);  
	           System.out.println(result);  
	       } catch (TesseractException e) {  
	           System.err.println(e.getMessage());  
	       }  
	} 
}
