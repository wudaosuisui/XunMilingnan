package com.xunmilingnan.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//public class JsonController {
//
//}
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;

import com.xunmilingnan.statics.ResponseJsonUtils;  
  
/** 
 * Spring MVC����JSON��JSONP���� 
 */  
@Controller  
@RequestMapping("/json")  
public class JsonController {  
      
    /** 
     * ����JSON���� 
     * @param request 
     * @param response 
     */  
    @RequestMapping("/json.do")  
    public void json(HttpServletRequest request, HttpServletResponse response){  
        Map<String, Object> data = new HashMap<String, Object>();  
          
        data.put("date", new Date());  
        data.put("email", "accountwcx@qq.com");  
        data.put("age", 30);  
        data.put("name", "csdn");  
        data.put("array", new int[]{1,2,3,4});  
        System.out.println("get @Controller 1");    
        ResponseJsonUtils.json(response, data);  
    }  
      
    /** 
     * ����JSONP���� 
     * @param callback JSONP�Ļص����� 
     * @param request 
     * @param response 
     */  
    @RequestMapping("/jsonp.do")  
    public void json(String callback, HttpServletRequest request, HttpServletResponse response){  
        Map<String, Object> data = new HashMap<String, Object>();  
          
        data.put("date", new Date());  
        data.put("email", "accountwcx@qq.com");  
        data.put("age", 30);  
        data.put("name", "csdn");  
        data.put("array", new int[]{1,2,3,4});  
        System.out.println("get @Controller 2");    
        if(callback == null || callback.length() == 0){  
            //����ͻ���û�з��ͻص���������ʹ��Ĭ�ϵĻص�����  
            ResponseJsonUtils.jsonp(response, data);  
        }else{  
            //ʹ�ÿͻ��˵Ļص�����  
            ResponseJsonUtils.jsonp(response, callback, data);  
        }  
    }  
}  