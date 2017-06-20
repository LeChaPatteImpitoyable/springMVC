package com.ying.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;


import net.sf.jxls.transformer.XLSTransformer;
/**
 * <!-- 验证码识别 <dependency> <groupId>net.java.dev.jna</groupId> <artifactId>jna</artifactId> 
			<version>4.2.1</version> </dependency> <dependency> <groupId>net.sourceforge.tess4j</groupId> 
			<artifactId>tess4j</artifactId> <version>2.0.1</version> <exclusions> <exclusion> 
			<groupId>com.sun.jna</groupId> <artifactId>jna</artifactId> </exclusion> 
			</exclusions> </dependency> -->
		<dependency>
			<groupId>net.sf.jxls</groupId>
			<artifactId>jxls-core</artifactId>
			<version>1.0.5</version>
		</dependency>
		<dependency>
			<groupId>org.jxls</groupId>
			<artifactId>jxls</artifactId>
			<version>2.2.7</version>
		</dependency>
		<dependency>
			<groupId>org.jxls</groupId>
			<artifactId>jxls-poi</artifactId>
			<version>1.0.6</version>
		</dependency>
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi</artifactId>
			<version>3.9</version>
		</dependency>
 * @author ying
 *
 */
public class MyTest1 extends BaseTest{

	
//	@Test  
//	public void test4() {  
//	    File imageFile = new File("/home/ying/getVerifyCode.jpg");  
//	       Tesseract tessreact = new Tesseract();  
//	       tessreact.setDatapath("/home/ying/tesseract/tessdata");  
//	       try {  
//	    	   System.out.println(imageFile.isFile());
//	           String result = tessreact.doOCR(imageFile);  
//	           System.out.println(result);  
//	       } catch (TesseractException e) {  
//	           System.err.println(e.getMessage());  
//	       }  
//	} 
	
//	@Test
//	public void test5() throws Exception{
		
//         
//        String srcFilePath = "/home/ying/data/model.xlsx";
//        String destFilePath = "/home/ying/data/A.xlsx";
//        Map<String, List<Staff>> beanParams = new HashMap<String, List<Staff>>();
//        beanParams.put("staffs", staffs);
//         
//        XLSTransformer former = new XLSTransformer();
//		former.transformXLS(srcFilePath, beanParams, destFilePath);
//	}
	
	@Test
	public void test6(){
		String srcFilePath = "/home/ying/data/user.xls";
        String destFilePath = "/home/ying/data/A.xls";
		try (InputStream is = new FileInputStream(srcFilePath)) {
		    try (OutputStream os = new FileOutputStream(destFilePath)) {
		        Context context = new Context();
		        context.putVar("report_year", 2015);
		        context.putVar("report_month", 8);
		        //queryUser()为数据获取的方法
		        List<User> userList = new ArrayList<>();
		        User u = new User();
		        u.setId(1);
		        u.setName("aa");
		        u.setSex("男");
		        u.setAge(10);
		        userList.add(u);
		        context.putVar("users", userList);
		        System.out.println(is==null );
		        System.out.println(os==null);
		        JxlsHelper.getInstance().processTemplate(is, os, context);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	class User{
		private int id;
		private String name;
		private String sex;
		private int age;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		
	}
}
