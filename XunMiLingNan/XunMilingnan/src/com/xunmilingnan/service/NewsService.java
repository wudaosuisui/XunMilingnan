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
@Repository//�й���Ϣ�ķ���
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
	
	//����ע�����ߵ��û���һ����Ϣ������ע��**������������
	public void sendNewsForArticle(User user,int jumpId) {
		//ͨ��user ��ȡ   	��ע���û��� follow �б�
		List<Follow> folList = foDao.getListByFT(user.getId(), 2);
		//ͨ��follow�б�  	 ��ȡ 	�û��б�
		List<User> userList = usDao.getUserListByFL(folList);
		//����message����
		String message = "����ע������"+user.getUserName()+"�����������¡�";
		//����News List
		List<News> newList = new ArrayList();
		for(User us : userList) {
			News ne = new News(5,us,message,jumpId);
			newList.add(ne);
		}
		//�������ݿ�
		neDao.saveList(newList);
	}
	
	
}








