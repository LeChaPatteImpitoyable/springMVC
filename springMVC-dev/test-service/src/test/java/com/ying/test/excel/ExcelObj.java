package com.ying.test.excel;

import java.lang.reflect.Method;

/**
 * 基础类,使用此功能的类需要继承该类
 * @author Liuzh
 *
 */
public abstract class ExcelObj {
    private String errMsg;
     
    public String getErrMsg() {
        return errMsg;
    }
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    /**
     * 通过属性名设置属性值
     * @param name
     * @param value
     */
    public  void putValue(String name,Object value){
        Class<? extends ExcelObj> c = this.getClass();
        Class<? extends Object> v = value.getClass();
        try{
            Method m = c.getMethod("set"+name, new Class[]{v});
            m.invoke(this, new Object[]{value});
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 返回属性名对应的值
     * @param name
     * @return 属性名对应的值
     */
    public Object outValue(String name){
        Class<? extends ExcelObj> c = this.getClass();
        Object o =null;
        try{
            Method m = c.getMethod("get"+name, new Class[]{});
            o = m.invoke(this, new Object[]{});
        }catch(Exception e){
            e.printStackTrace();
        }
        return o;
    }
}