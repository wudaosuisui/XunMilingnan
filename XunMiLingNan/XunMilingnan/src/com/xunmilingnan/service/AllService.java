package com.xunmilingnan.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xunmilingnan.dao.*;
import com.xunmilingnan.entity.*;

@Service
@Repository
public class AllService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource
	private UserGroupDao usGroDao;//用户组
	@Resource
	private AdvertisementCategoryDao adverCatDao;//广告种类
	@Resource
	private ChannelDao chaDao;//频道类型
	
	@Resource
	private UserDao userDao;//活动
	@Resource
	private ActivityDao actDao;//活动
	@Resource
	private AdminDao admDao;//admin
	@Resource
	private AdvertisementDao aderDao;//广告
	@Resource
	private ArticleDao artDao;//活动
	@Resource
	private CommentDao comDao;//评论
	@Resource
	private ProgramDao proDao;//电台节目
	
	public Map getAll() {
//		Map objecs= new HashMap<String, Object>(11);
//		new HashMap<String, Object>(2) {{put("statusCode", "111001");
//            put("desc", "用户密码错误/用户不存在");
//            put("result", "");}}
		return new HashMap<String, Object>(10)
		{{put("UserGroup",usGroDao.getById(1));
		put("AdvertisementCategory",adverCatDao.getById(1));
		put("Channel",chaDao.getById(1));
		put("User",userDao.getById(1));
		put("Activity",actDao.getById(1));
		put("Admin",admDao.getById(1));
		put("Advertisement",aderDao.getById(1));
		put("Article",artDao.getById(1));
		put("Comment",comDao.getById(1));
		put("Program",proDao.getById(1));
		}} ;
	}
	public void addAll(Map objects) {
		System.out.println("get service success");
		Session session = sessionFactory.openSession();
		usGroDao.save((UserGroup)objects.get("UserGroup"));
		adverCatDao.save((AdvertisementCategory)objects.get("AdvertisementCategory"));
		chaDao.save((Channel)objects.get("Channel"));

		userDao.save((User)objects.get("User"));
		actDao.save((Activity)objects.get("Activity"));
		admDao.save((Admin)objects.get("Admin"));
		aderDao.save((Advertisement)objects.get("Advertisement"));
		artDao.save((Article)objects.get("Article"));
		comDao.save((Comment) objects.get("Comment"));
		proDao.save((Program) objects.get("Program"));
		System.out.println("OUT DAO success!!!!!!!!!");
		session.close();
		return;
	}
	
	
	
	
}
