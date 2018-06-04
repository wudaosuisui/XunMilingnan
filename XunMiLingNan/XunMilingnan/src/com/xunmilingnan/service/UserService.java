package com.xunmilingnan.service;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.xunmilingnan.dao.UserDao;
import com.xunmilingnan.entity.AdvCategory;
import com.xunmilingnan.entity.User;

@Service
@Repository//有关用户
public class UserService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource
	private UserDao userDao;
	
	public User getUserById(int id ) {
		Session session = sessionFactory.openSession();
		User use = userDao.getById(id);
		session.close();
		return use;
	}
}
