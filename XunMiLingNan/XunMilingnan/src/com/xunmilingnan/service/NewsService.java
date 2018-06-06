package com.xunmilingnan.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.xunmilingnan.dao.FollowDao;
import com.xunmilingnan.dao.GroupDao;
import com.xunmilingnan.dao.NewsDao;
import com.xunmilingnan.dao.UserDao;
import com.xunmilingnan.entity.Follow;
import com.xunmilingnan.entity.News;
import com.xunmilingnan.entity.User;

@Service
@Repository//有关消息的服务
public class NewsService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource
	private NewsDao neDao;
	@Resource
	private FollowDao foDao;
	@Resource
	private UserDao usDao;
	@Resource
	private GroupDao grDao;
	
	//给关注此作者的用户发一条消息：您关注的**发布了新文章
	public void sendNewsForArticle(User user,int jumpId) {
		//通过user 获取   	关注此用户的 follow 列表
		List<Follow> folList = foDao.getListByFT(user.getId(), 2);
		//通过follow列表  	 获取 	用户列表
		List<User> userList = usDao.getUserListByFL(folList);
		//创建message内容
		String message = "您关注的作者"+user.getUserName()+"发布了新文章。";
		//创建News List
		List<News> newList = new ArrayList();
		for(User us : userList) {
			News ne = new News(5,us,message,jumpId);
			newList.add(ne);
		}
		//存入数据库
		neDao.saveList(newList);
	}
	
	
}








