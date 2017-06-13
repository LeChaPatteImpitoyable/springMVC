package com.doshr.xmen.backend.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.doshr.xmen.backend.client.base.RespCode;
import com.doshr.xmen.backend.client.device.api.DeviceApi;
import com.doshr.xmen.backend.client.device.dto.DeviceDTO;
import com.doshr.xmen.backend.client.device.request.AddDevicesReq;
import com.doshr.xmen.backend.client.device.response.AddDevicesResp;
import com.doshr.xmen.backend.client.grade.api.ClassApi;
import com.doshr.xmen.backend.client.grade.dto.StudentDTO;
import com.doshr.xmen.backend.client.grade.request.AddStudentsReq;
import com.doshr.xmen.backend.client.grade.response.AddStudentsResp;
import com.doshr.xmen.backend.common.exception.BackendException;
import com.doshr.xmen.backend.common.util.XmenUtils;

@Controller
@RequestMapping("/manage")
public class ManageController extends JsonScreen{
	private static final Logger LOG = LoggerFactory.getLogger(ManageController.class);
	
	@Autowired
	private ClassApi classApi;
	
	@Autowired
	private DeviceApi deviceApi;
	
	@Autowired
	private String braceletDir;
	
	/**
	 * 导入学生信息
	 * @param file
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/batchimport.do", method = RequestMethod.POST)
	public ModelAndView addCustomerInfo(@RequestParam(value="filename") MultipartFile file,
										HttpServletRequest request,
										HttpServletResponse response,
										HttpSession session){
		
			ModelAndView mv = new ModelAndView();
		   //判断文件是否为空
	        if(file==null){
	        	mv.setViewName("redirect:/batchimport.jsp");
	        	session.setAttribute("emptyExcel", "导入的学生信息为空,请重新选择文件");
	        	session.setAttribute("studentDTOs", null);
	        	return mv;
	        } 
	       // 把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
	        CommonsMultipartFile cf= (CommonsMultipartFile)file;
	        File file1 = new  File(braceletDir);
	        LOG.info("决定路径:"+file1.getAbsolutePath());
	        if (!file1.exists()){
	        	file1.mkdirs();
	        } 
	       //新建一个文件
	        File file2 = new File(braceletDir + new Date().getTime() + ".xlsx");
	        try {
	        	 //将上传的文件写入新建的文件中
				cf.getFileItem().write(file2);
			} catch(Exception e) {
				e.printStackTrace();
			}
            try {
				List<Map<Integer,String>> maps = XmenUtils.readExcelContent(file2);
				List<StudentDTO> studentDTOs = null;
				if(!XmenUtils.isEmpty(maps)){
					try {
						studentDTOs = getStudentInfoByExcel(maps);
					} catch(BackendException e) { //201 参数为空
					   if(e.getRespCode().getCode().equals("201")){
						    mv.setViewName("redirect:/batchimport.jsp");
							session.setAttribute("studentDTOs", null);
							session.setAttribute("errorContent", "年龄不能为空");
							return mv;
					   }
					}
				}
				
				if(!XmenUtils.isEmpty(studentDTOs)){
					AddStudentsReq req = new AddStudentsReq();
					req.setStudentDTOs(studentDTOs);
					AddStudentsResp resp = classApi.addStudents(req);
					XmenUtils.checkRespStatus(resp);
				}
				
			  System.out.println("读取结果:"+JSON.toJSONString(studentDTOs));
			  mv.setViewName("redirect:/batchimport.jsp");
			  session.setAttribute("success", "成功导入学生信息");
			  session.setAttribute("studentDTOs", studentDTOs);
			  return mv;
			} catch(RuntimeException be) {
				mv.setViewName("redirect:/batchimport.jsp");
				if(be.getMessage().equals("1001")){
					session.setAttribute("errorContent", "班级名称不能为空");
				}else if(be.getMessage().equals("1002")){
					session.setAttribute("errorContent", "此班级学生信息已导入");
				}else if(be.getMessage().equals("1003")){
					session.setAttribute("errorContent", "请保持班级名称一致");
				}else{
					session.setAttribute("errorContent", "导入学生信息失败");
				}
				session.setAttribute("studentDTOs", null);
				return mv;
			}catch(Exception e){
				mv.setViewName("redirect:/batchimport.jsp");
				session.setAttribute("studentDTOs", null);
				session.setAttribute("errorContent", "导入学生信息失败");
				return mv;
			}
	}
	
	@RequestMapping(value = "/deviceimport.do", method = RequestMethod.POST)
	public ModelAndView addDeviceInfo(@RequestParam(value="filename") MultipartFile file,
										HttpServletRequest request,
										HttpServletResponse response,
										HttpSession session){
		
			ModelAndView mv = new ModelAndView();
		   //判断文件是否为空
	        if(file==null){
	        	mv.setViewName("redirect:/deviceimport.jsp");
	        	session.setAttribute("emptyExcel", "导入的学生信息为空,请重新选择文件");
	        	session.setAttribute("studentDTOs", null);
	        	return mv;
	        } 
	       // 把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
	        CommonsMultipartFile cf= (CommonsMultipartFile)file;
	        File file1 = new  File(braceletDir);
	        LOG.info("决定路径:"+file1.getAbsolutePath());
	        if (!file1.exists()){
	        	file1.mkdirs();
	        } 
	       //新建一个文件
	        File file2 = new File(braceletDir + new Date().getTime() + ".xlsx");
	        try {
	        	 //将上传的文件写入新建的文件中
				cf.getFileItem().write(file2);
			} catch(Exception e) {
				e.printStackTrace();
			}
	        
	        try {
				List<Map<Integer,String>> maps = XmenUtils.readExcelContent(file2);
				List<DeviceDTO> deviceDTOs = null;
				if(!XmenUtils.isEmpty(maps)){
					deviceDTOs = getDeviceInfoByExcel(maps);
				}
				if(!XmenUtils.isEmpty(deviceDTOs)){
					AddDevicesReq req = new AddDevicesReq();
					req.setDeviceDTOs(deviceDTOs);
					AddDevicesResp resp = deviceApi.addDevices(req);
					XmenUtils.checkRespStatus(resp);
				}
				mv.setViewName("redirect:/deviceimport.jsp");
	        	session.setAttribute("success", "成功导入设备信息");
	        	session.setAttribute("deviceDTOs", deviceDTOs);
				return mv;
			} catch(RuntimeException be) {
				mv.setViewName("redirect:/deviceimport.jsp");
				if(be.getMessage().equals("201")){
					session.setAttribute("errorContent", "Excle表格设备信息为空");
				}else if(be.getMessage().equals("4004")){
					session.setAttribute("errorContent", "设备信息不完整");
				}else if(be.getMessage().equals("4003")){
					session.setAttribute("errorContent", "设备信息已存在");
				}else{
					session.setAttribute("errorContent", "导入设备信息失败");
				}
				session.setAttribute("deviceDTOs", null);
				return mv;
			}catch(Exception e){
				mv.setViewName("redirect:/deviceimport.jsp");
				session.setAttribute("errorContent", "导入设备信息失败");
				session.setAttribute("deviceDTOs", null);
				return mv;
			}
           
	}
	
	
	/**
	 * 封装excel里面的设备信息
	 * @param maps
	 * @return
	 */
	private List<DeviceDTO> getDeviceInfoByExcel(List<Map<Integer,String>> maps){
		List<DeviceDTO> deviceDTOs = new ArrayList<>();
		for (Map<Integer,String> map :maps) {
			DeviceDTO d = new DeviceDTO();
		    if(!StringUtils.isEmpty(map.get(0))){
		    	int serialId = (int)Double.parseDouble(map.get(0));
		    	d.setSerialId(serialId);
		    }
			d.setBraceletNO(map.get(1));
			d.setRfid(map.get(2));
			d.setMac(map.get(3));
			deviceDTOs.add(d);
		}
		return deviceDTOs;
	}
	
	/**
	 * 把Excel读出来的内容封装成studentDTO
	 * @param maps
	 * @return
	 */
	private List<StudentDTO> getStudentInfoByExcel(List<Map<Integer,String>> maps) throws BackendException{
		    List<StudentDTO> studentDTOs = new ArrayList<>();
			for(Map<Integer,String> map :maps){
				   StudentDTO studentDTO = new StudentDTO();
				   if(!StringUtils.isEmpty(map.get(0))){
					   studentDTO.setSerialId((int)(Double.parseDouble(map.get(0))));
				   }
				   studentDTO.setStudentNO(map.get(1));
				   studentDTO.setStudentName(map.get(2));
				   String sex = map.get(3);
				   studentDTO.setSex(sex);
				   if(StringUtils.isEmpty(map.get(4))){
					   LOG.error("读取excel文件时年龄为空");
					   throw new BackendException(RespCode.REQUEST_PARAM_IS_NULL);
				   }
				   int age = (int)(Double.parseDouble(map.get(4)));
				   int grade = 0;
				   int clas = 0;
				   if(!StringUtils.isEmpty(map.get(8))){
					   grade = (int)(Double.parseDouble(map.get(8)));
				   }
				
				   if(!StringUtils.isEmpty(map.get(9))){
					   clas = (int)(Double.parseDouble(map.get(9)));
				   }
				   if(age <= 0){
					   LOG.error("读取excel文件时  age grade clas 不能小于等于0");
					   throw new BackendException(RespCode.REQUEST_PARAM_IS_NULL);
				   }
				   studentDTO.setAge(age);
				   String weight = map.get(5);
				   if(StringUtils.isEmpty(weight)){
					   weight = XmenUtils.getStandardStatureAndWeightByAge(age, sex).get("weight");
				   }
				   studentDTO.setWeight(weight);
				   String stature = map.get(6);
				   if(StringUtils.isEmpty(stature)){
					   stature = XmenUtils.getStandardStatureAndWeightByAge(age, sex).get("stature");
				   }
				   studentDTO.setStature(stature);
				   studentDTO.setClassName(map.get(7));
				   studentDTO.setGrade(grade);
				   studentDTO.setClas(clas);
				   studentDTOs.add(studentDTO);
			}
			
			return studentDTOs;
	}
}
