package com.doshr.xmen.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import org.junit.Test;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class MyTest2 extends BaseTest{

	@Test  
	public void test() {  
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
	
	@Test
	public void test1(){
		try {
			executeCommands();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void executeCommands() throws IOException {

	    File tempScript = createTempScript();
	    StringBuffer output = new StringBuffer();
	    try {
	        ProcessBuilder pb = new ProcessBuilder("bash", tempScript.toString());
	        pb.inheritIO();
	        Process process = pb.start();
	        process.waitFor();
	        BufferedReader reader = 
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

		    String line = "";           
		    while ((line = reader.readLine())!= null) {
		        output.append(line + "\n");
		    }
		    System.out.println(output.toString());
	    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        tempScript.delete();
	    }
	}

	public File createTempScript() throws IOException {
	    File tempScript = File.createTempFile("/home/ying/data/script", null);

	    Writer streamWriter = new OutputStreamWriter(new FileOutputStream(
	            tempScript));
	    PrintWriter printWriter = new PrintWriter(streamWriter);

	    printWriter.println("#!/bin/bash");
	    printWriter.println("mysql -uroot -pying -e \"select * from class\" bracelet > /home/ying/data/bracelet.xlsx");

	    printWriter.close();

	    return tempScript;
	}
}
