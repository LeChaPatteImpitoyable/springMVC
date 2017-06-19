package com.ying.client.base;


/**
 * enum of xmen code 
 *
 */
public enum RespCode {

    // common
    RESP_OK("100","RESP_OK"),
    
    // common request 2....
    REQUEST_PARAM_IS_NULL("201","REQUEST_PARAM_IS_NULL"),//请求参数为空
    PRIVILEGE_INSUFFICIENT("202"," PRIVILEGE_INSUFFICIENT"),//没有权限
    INFO_IS_NOT_COMPLETE("203","INFO_IS_NOT_COMPLETE"),
    REQUEST_PARAM_IS_ERROR("204","REQUEST_PARAM_IS_ERROR"),//请求参数错误
    // common response 5....
    SYSTEM_ERROR("500","SYSTEM_ERROR"),
    RESP_CODE_NULL("501","RESP_CODE_NULL"),
    DATA_IS_NOT_EXIT("502","DATA_IS_NOT_EXIT"),
    SYSTEM_NET_ERROR("503","SYSTEM_NET_ERROR"),
    DB_INSERT_ERROR("504","DB_INSERT_ERROR"),
    JSON_ERROR("505","JSON_ERROR"),
    
    //class
    GRADE_IS_NULL("1000","GRADE_IS_NULL"),//年级不存在
    CLASS_IS_NULL("1001","CLASS_IS_NULL"),//班级不存在
    CLASS_NAME_IS_EXIST("1002","CLASS_NAME_IS_EXIST"),//班级已存在
    CLASS_NAME_DISAFFINITY_FOR_EXCEL("1003","CLASS_NAME_DISAFFINITY_FOR_EXCEL"),//excel表中不是同一个班级
    
    STUDENT_IS_NULL("2000","STUDENT_IS_NULL"),//学生不存在
    STUDENT_NAME_IS_NULL("2001","STUDENT_NAME_IS_NULL"),//学生姓名为空
    
    RELATION_IS_NULL("3000","RELATION_IS_NULL"),//学生关系未建立
    
    DEVICE_IS_NULL("4000","DEVICE_IS_NULL"),//设备不存在
    DEVICE_IS_BOUNDED("4001","DEVICE_IS_BOUNDED"),//设备已绑定
    DEVICE_IS_UNBOUNDED("4002","DEVICE_IS_UNBOUNDED"),//设备未绑定
    DEVICE_IS_EXIST("4003","DEVICE_IS_EXIST"),//设备已存在
    DEVICE_INCOMPLETE_INFORMATION("4004","DEVICE_INCOMPLETE_INFORMATION"),//设备信息不完整
    DEVICE_BRACELETNO_ILLEGAL("4005","DEVICE_BRACELETNO_ILLEGAL"),//设备编号不合法
    
    UUID_IS_NULL("5000","UUID_IS_NULL"),
    UUID_IS_EXIST("5001","UUID_IS_EXIST"),//已存在
    
	SYSTEM_UNKNOWN_ERROR("100001","SYSTEM_UNKNOWN_ERROR");
	
	
    private RespCode(String code, String msg){
    	this.code =code;
    	this.msg = msg;
    }
    
    private RespCode(){}
    
    private String code;
    private String msg;
   
    
    public String getCode(){
        return this.code;
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
}
