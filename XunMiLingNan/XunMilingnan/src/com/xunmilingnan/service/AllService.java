//package com.xunmilingnan.service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.xunmilingnan.dao.*;
//import com.xunmilingnan.entity.*;
//
//@Service
//@Repository
//public class AllService {
//	@Resource
//	private SessionFactory sessionFactory;
//	
//	@Resource
//	private GroupDao usGroDao;//�û���
//	@Resource
//	private AdvCategoryDao adverCatDao;//�������
//	@Resource
//	private ChannelDao chaDao;//Ƶ������
//	
//	@Resource
//	private UserDao userDao;//�
//	@Resource
//	private SpecialTopicDao actDao;//�
//	@Resource
//	private AdminDao admDao;//admin
//	@Resource
//	private AdvertisementDao aderDao;//���
//	@Resource
//	private ArticleDao artDao;//�
//	@Resource
//	private CommentDao comDao;//����
//	@Resource
//	private ProgramDao proDao;//��̨��Ŀ
//	
//	public Map getAll() {
////		Map objecs= new HashMap<String, Object>(11);
////		new HashMap<String, Object>(2) {{put("statusCode", "111001");
////            put("desc", "�û��������/�û�������");
////            put("result", "");}}
//		return new HashMap<String, Object>(10)
//		{{put("UserGroup",usGroDao.getById(1));
//		put("AdvertisementCategory",adverCatDao.getById(1));
//		put("Channel",chaDao.getById(1));
//		put("User",userDao.getById(1));
//		put("Activity",actDao.getById(1));
//		put("Admin",admDao.getById(1));
//		put("Advertisement",aderDao.getById(1));
//		put("Article",artDao.getById(1));
//		put("Comment",comDao.getById(1));
//		put("Program",proDao.getById(1));
//		}} ;
//	}
//	public void addAll(Map objects) {
//		System.out.println("get service success");
//		Session session = sessionFactory.openSession();
//		usGroDao.save((Group)objects.get("UserGroup"));
//		adverCatDao.save((AdvCategory)objects.get("AdvertisementCategory"));
//		chaDao.save((Channel)objects.get("Channel"));
//
//		userDao.save((User)objects.get("User"));
//		actDao.save((SpecialTopic)objects.get("Activity"));
//		admDao.save((Admin)objects.get("Admin"));
//		aderDao.save((Advertisement)objects.get("Advertisement"));
//		artDao.save((Article)objects.get("Article"));
//		comDao.save((Comment) objects.get("Comment"));
//		proDao.save((Program) objects.get("Program"));
//		System.out.println("OUT DAO success!!!!!!!!!");
//		session.close();
//		return;
//	}
//	
//	
//	
//	
//}
