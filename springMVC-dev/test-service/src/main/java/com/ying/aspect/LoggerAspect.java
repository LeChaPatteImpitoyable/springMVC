package com.ying.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.ying.client.base.BaseResponse;
import com.ying.client.base.RespCode;
import com.ying.client.base.RespStatus;


/**
 * make logger
 *
 */
public class LoggerAspect {
    
    private static final Logger LOG = LoggerFactory.getLogger(LoggerAspect.class);

    
    public Object logAround(ProceedingJoinPoint joinPoint){

        LOG.info(">>----------------- request worker start -----------------<<");

        Object result = new BaseResponse();
        try {
            // check request is null
            long start = System.currentTimeMillis();
            boolean isNull = false;
            Object[] args  = joinPoint.getArgs();
            for(Object o : args){
                if(o == null){
                    isNull = true;
                    break;
                }
            }
            LOG.info(joinPoint.getSignature().getName()+"() method request data is --> " + JSON.toJSONString(args));
            //if request is null, return
            if(isNull){
                Signature signature = joinPoint.getSignature();
                BaseResponse returnType = makeErrorResponse(signature, RespCode.REQUEST_PARAM_IS_NULL);
                LOG.warn("request is null.. method is --> " + joinPoint.getSignature().getName());
                return returnType;
            }else{
                result = joinPoint.proceed();
                long end = System.currentTimeMillis();
                Signature signature = joinPoint.getSignature();
                LOG.info(signature.getName()+"() method response data is --> " + JSON.toJSONString(result));
                LOG.info(signature.getName() + "()--> start: " + start + ",  end: " + end  + ", cost time:--->" +  (end - start));
            }
        } catch (Throwable e) {
            LOG.error("do bracelet loggerAspect call error. ", e);
            Signature signature = joinPoint.getSignature();
            BaseResponse returnType = null;
            try {
                returnType = makeErrorResponse(signature, RespCode.SYSTEM_ERROR);
            } catch (Exception e1) {
                LOG.error(e.getMessage(), e);
            }
            return returnType;
        }
        
        return result;
    }
    
    
    /**
     * 
     * @param signature
     * @param code
     * @return
     * @throws Exception
     */
    private BaseResponse makeErrorResponse(Signature signature, RespCode code) throws Exception{
        @SuppressWarnings("unchecked")
        Class<? extends BaseResponse> clazz = ((MethodSignature)signature).getReturnType();
        BaseResponse returnType = clazz.newInstance();
        returnType.setRespStatus(new RespStatus(code));
        return returnType;
    }
    
}
