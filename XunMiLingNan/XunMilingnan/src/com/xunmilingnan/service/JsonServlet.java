package com.xunmilingnan.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xunmilingnan.statics.ResponseJsonUtils;

//������   ����ûʲô����
/** 
 * ��Servlet����JSON���� 
 */  
@WebServlet("/json.do")  
public class JsonServlet/* extends HttpServlet*/ {  
    private static final long serialVersionUID = 7500835936131982864L;  
  
    /** 
     * ����json��ʽ���� 
     */  
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        Map<String, Object> data = new HashMap<String, Object>();  
          
        data.put("date", new Date());  
        data.put("email", "accountwcx@qq.com");  
        data.put("age", 30);  
        data.put("name", "csdn");  
        data.put("array", new int[]{1,2,3,4});  
        System.out.println("get service");  
        ResponseJsonUtils.json(response, data);  
    }  
}  