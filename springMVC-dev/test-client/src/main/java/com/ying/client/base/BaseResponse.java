package com.ying.client.base;

import java.io.Serializable;

public class BaseResponse implements Serializable{


    /**
     * 
     */
    private static final long serialVersionUID = -386099185754889033L;

    public BaseResponse(){}
    
    private RespStatus respStatus = new RespStatus();

    public RespStatus getRespStatus() {
        return respStatus;
    }

    public void setRespStatus(RespStatus respStatus) {
        this.respStatus = respStatus;
    }

            
    
}
