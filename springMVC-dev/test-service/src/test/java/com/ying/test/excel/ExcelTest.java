package com.ying.test.excel;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.ying.test.BaseTest;
 
/**
 * 测试类
 * @author Liuzh
 *
 */
public class ExcelTest extends BaseTest{
 
	@Test
    public void excel() throws Exception {
         
        String[] name = {"Name","Age","Sex","Birthday","Address","Email","Phone"};
        Integer[] type={ColT.cSTRING,ColT.cINT,ColT.cSTRING,ColT.cDATE,ColT.cSTRING,ColT.cSTRING,ColT.cSTRING};
        HashMap<String,Object> code = new HashMap<String, Object>();
        code.put("1", "男");
        code.put("2", "女");
         
         
        ExcelData excel = new ExcelData();
        //存入文件列名,必填项
        excel.setColnumName(name);
        //文件列名对应类型,可选,不写可能会出现异常
        excel.setColnumType(type);
        //使用code类型时,必须设置codetype
        excel.setCodeType(code);
        //使用getExcelObj方法必须设置
        excel.setDTO(new TestObj());
         
        File file=new File("/home/ying/data/info.xls");
         
         
         
        //使用第一种方法获取并输出
        List<List<HashMap<String, Object>>> exceldata = excel.getExcel(file,0,1);
        if(exceldata!=null){
             for(List<HashMap<String, Object>> lista:exceldata){
                 for(int iii=0;iii<lista.size();iii++){
                     HashMap<String, Object> hm = lista.get(iii);
                     System.out.print(hm.get(name[iii])+"  ");
                 }
                 System.out.println();
             }   
        }
     
        //使用第二种方法直接获取List<Object>
        List<Object> list = excel.getExcelObj(file,0,1);
        for(Object o:list){
            TestObj t = (TestObj)o;
            DecimalFormat df = new DecimalFormat("#");
            System.out.println("姓名为:"+t.getName()+",性别:"+t.getSex()+",地址为:"+t.getAddress()+",邮箱为:"+t.getEmail()+",手机号:"+df.format(Double.parseDouble(t.getPhone())));
        }
    }
 
}