package com.ying.test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ying.client.model.StudentAchievementDTO;
import com.ying.common.util.XmenUtils;

public class CreateExcelTest extends BaseTest{
	
	@Test
	public void test1(){
		String outPath = "/home/ying/data/out.xls";
		ChartFormatUtil.getExcel(outPath);
	}
	
	@Test
	public void test2(){
		String date = XmenUtils.getDate();
		URL url = this.getClass().getClassLoader().getResource("excel_model/student_achievement.xlsx");
		String srcFilePath = url.getPath();
		String destFilePath = "/home/ying/data/out_"+date+".xls";
		List<StudentAchievementDTO> list = new ArrayList<StudentAchievementDTO>();
		StudentAchievementDTO s = new StudentAchievementDTO();
		s.setStudentName("大刀");
		s.setNum(100);
		list.add(s);
		XmenUtils.createExcel(srcFilePath, list, destFilePath);
	}

}
