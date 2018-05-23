package com.xunmilingnan.controller;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.xunmilingnan.entity.*;
import com.xunmilingnan.service.*;
import com.xunmilingnan.statics.ResponseJsonUtils;


//@RestController
@Controller
@Repository
@RequestMapping("/test")
public class TestOne {
	@Resource
	private UserGroupServiceImpl ugService;
	@Resource
	private AllService allService;
	/*
	@RequestParam(value="sign") String sign,
	@RequestParam(value="name") String name,
	@RequestParam(value="describe") String describe */
	/*,
	@RequestBody UserGroup ug*/
	
	@PostMapping("/addOneUg")
	public void addOneUg(HttpServletResponse response,
			@RequestParam(value="sign") String sign,
			@RequestParam(value="name") String name,
			@RequestParam(value="describe") String describe ) throws SQLException{
		UserGroup ug = new UserGroup(sign,name,describe);
		ugService.addOneUserGroup(ug);
		ResponseJsonUtils.json(response, ug);
	}
	
	@GetMapping("/getTest")
	public void getTest( HttpServletResponse response) {
		ResponseJsonUtils.json(response, allService.getAll());
	}
	
	@PostMapping("/addTest")
	public void addTest( HttpServletResponse response) {
		System.out.println("get controller success ");
		Map objecs= new HashMap<String, Object>(11);
		//�û���
		UserGroup ug = new UserGroup("0","author","��̨��Ŀ����");
		//�������
		AdvertisementCategory advCat = new AdvertisementCategory("��ҳ�ֲ�ͼ");
		//Ƶ������
		Channel cha = new Channel("Ƶ������1","��Ƶ������1������","file:///E:/Program%20Files/JavaEE/eclipseWork/XunMilingnan/WebContent/img/page%20(1).jpg");
		
		//�û�
		User use = new User("username","password");
		//�
		Activity acr = new Activity("�һ",new Date(),new Date(),"��ľ������ݣ�����Ӧ���кܶ�����","file:///E:/Program%20Files/JavaEE/eclipseWork/XunMilingnan/WebContent/img/page%20(2).jpg",0);
		//admin
		Admin adm = new Admin("username2","123",ug,new Date());
		//���
		Advertisement adv = new Advertisement(advCat,"����һ��������Ŀ","file:///E:/Program%20Files/JavaEE/eclipseWork/XunMilingnan/WebContent/img/page%20(3).jpg","����ԭ��Ӧ����һ��url");
		//����
		Article art = new Article(acr,use,new Date(),"������������",0,0,0);
		//����
		Comment com = new Comment(false,adm,use,new Date(),"���۵�����");
		//��̨jiemu
		Program pro = new Program(use,new Date(),0,0,0,0);
//		login.put("userName", true);
//		 log.get(userName)
		System.out.println("��������ɹ�  success to new Objects!");
		objecs.put("UserGroup", ug);objecs.put("AdvertisementCategory", advCat);
		objecs.put("Channel", cha);objecs.put("Activity", acr);
		objecs.put("Admin", adm);objecs.put("Advertisement", adv);
		objecs.put("Article", art);objecs.put("Comment", com);
		objecs.put("Program", pro);objecs.put("User", use);
		System.out.println("add to Map success");
		allService.addAll(objecs);
		ResponseJsonUtils.json(response, objecs);
	}
	
	
	
}
