package com.ying.common.util;

import java.util.HashMap;
import java.util.Map;

import com.ying.common.util.JsonUtil;

public class JsonResult{

    private Object model;
    
    private Map<String, Object> resultMap = new HashMap<String, Object>();
    
    public JsonResult(){}


    public JsonResult(Object model){
        this.model = model;
    }
    
    
    public JsonResult(String key, Object model){
        if (model == null) {
            throw new IllegalArgumentException("argument is null");
        }
        resultMap.put("returnStatus", 1);
        resultMap.put(key, model);
    }

    
    public JsonResult(String key, Object model, Integer retCode){
        if (model == null) {
            throw new IllegalArgumentException("argument is null");
        }
        resultMap.put("returnStatus", retCode);
        resultMap.put(key, model);
    }
    

    public String buildContent() {
        return JsonUtil.toJsonString(this.resultMap);
    }

    
    public String buildDataContent(){
        return JsonUtil.toJsonString(this.model);
    }
    

}
