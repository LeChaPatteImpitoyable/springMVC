package com.ying.controller;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ying.common.util.JsonResult;


public abstract class JsonScreen {

    private static final Logger LOG = LoggerFactory.getLogger(JsonScreen.class);
    
    @Autowired
    private HttpServletResponse response;
    
    protected void returnResult(Object result) {
        JsonResult json = new JsonResult(result);
        flushData(json.buildDataContent());
    }

    
    protected void returnResult(String key, Object result) {
        JsonResult json = new JsonResult(key, result);
        flushData(json.buildContent());
    }

    protected void returnFile(byte[] bytes){
    	response.reset();
    	response.setContentType("application/vnd.ms-excel;charset=utf-8");  
    	response.setCharacterEncoding("UTF-8");
    	Date date = new Date();
    	SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String orderDate = dateFormater.format(date);
    	String orderName = "order"+orderDate;
    	  try {
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(orderName.getBytes("utf-8"), "iso-8859-1") + ".xls");
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    
    };
    
    private void flushData(String content){
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        int length = 0;
        if(content != null){
        	length = content.getBytes().length;
        }
        response.setContentLength(length);
        try {
            Writer writer = response.getWriter();
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
