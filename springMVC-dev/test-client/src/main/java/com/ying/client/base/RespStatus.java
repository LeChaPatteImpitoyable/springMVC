package com.ying.client.base;

import java.io.Serializable;


public class RespStatus implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2236993919061762740L;
    
    private String code = RespCode.RESP_OK.getCode();
    private String msg = RespCode.RESP_OK.getMsg();

    
    /**
     * constructor
     */
    public RespStatus(){
    }
    
    /**
     * constructor specified parameter
     * @param respCode
     */
    public RespStatus(RespCode respCode){
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    
    
    
}
