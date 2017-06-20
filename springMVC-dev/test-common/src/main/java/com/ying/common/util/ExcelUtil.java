package com.ying.common.util;

//import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jxls.transformer.XLSTransformer;


public class ExcelUtil {
 
	public void createExcel(String srcFilePath, List<?> list, String destFilePath){
		try { 
			XLSTransformer transformer = new XLSTransformer();
			
//			URL url = this.getClass().getClassLoader().getResource("");
			
//			String srcFilePath = url.getPath() + templateFileName;
			Map<String,Object> map = new HashMap<String,Object>(); 
			map.put("list", list);
//			String destFilePath = url.getPath() + resultFileName;
			
			transformer.transformXLS(srcFilePath, map, destFilePath);
		} catch (Exception e) {
			throw new RuntimeException("error happens...", e);
		}
	}
}
