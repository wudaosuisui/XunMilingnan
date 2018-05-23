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
 * Spring MVC返回JSON和JSONP数据 
 */  
@Controller  
@RequestMapping("/json")  
public class JsonController {  
      
    /** 
     * 返回JSON数据 
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
     * 返回JSONP数据 
     * @param callback JSONP的回调函数 
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
            //如果客户端没有发送回调函数，则使用默认的回调函数  
            ResponseJsonUtils.jsonp(response, data);  
        }else{  
            //使用客户端的回调函数  
            ResponseJsonUtils.jsonp(response, callback, data);  
        }  
    }  
}  