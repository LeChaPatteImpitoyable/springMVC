package com.doshr.xmen.backend.client.grade.dto;


import java.util.List;

import com.doshr.xmen.backend.client.device.dto.DeviceDTO;
import com.doshr.xmen.backend.client.examination.dto.ExaminationDTO;
import com.doshr.xmen.backend.client.monitordata.dto.MonitorDataDTO;
import com.doshr.xmen.backend.client.student.dto.StudentInfoDTO;

public class StudentDTO extends StudentInfoDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8666871129305121531L;
	private String studentName;
	private String className;
	private String studentNO;
	private String braceletNO;
	private DeviceDTO deviceDTO;
	private int status;
	private String sex;
	private int age;
	private String stature;//身高
	private String weight;
	private int isSignIn;//当天是否签到
	private List<ExaminationDTO> examinationDTOs;//考核数据
	private List<MonitorDataDTO> monitorDataDTOs;//运动数据
	
	public List<MonitorDataDTO> getMonitorDataDTOs() {
		return monitorDataDTOs;
	}
	public void setMonitorDataDTOs(List<MonitorDataDTO> monitorDataDTOs) {
		this.monitorDataDTOs = monitorDataDTOs;
	}
	public List<ExaminationDTO> getExaminationDTOs() {
		return examinationDTOs;
	}
	public void setExaminationDTOs(List<ExaminationDTO> examinationDTOs) {
		this.examinationDTOs = examinationDTOs;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getIsSignIn() {
		return isSignIn;
	}
	public void setIsSignIn(int isSignIn) {
		this.isSignIn = isSignIn;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getStature() {
		return stature;
	}
	public void setStature(String stature) {
		this.stature = stature;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public DeviceDTO getDeviceDTO() {
		return deviceDTO;
	}
	public void setDeviceDTO(DeviceDTO deviceDTO) {
		this.deviceDTO = deviceDTO;
	}
	public String getBraceletNO() {
		return braceletNO;
	}
	public void setBraceletNO(String braceletNO) {
		this.braceletNO = braceletNO;
	}
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getStudentNO() {
		return studentNO;
	}
	public void setStudentNO(String studentNO) {
		this.studentNO = studentNO;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
