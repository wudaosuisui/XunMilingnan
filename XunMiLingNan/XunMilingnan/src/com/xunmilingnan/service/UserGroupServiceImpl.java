package com.xunmilingnan.service;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.xunmilingnan.dao.GroupDao;
import com.xunmilingnan.entity.Group;



@Service
@Repository
public class UserGroupServiceImpl {
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private GroupDao ugDaoImpl;
	
	//add one 
	public void addOneUserGroup(Group ug) {
		Session session = sessionFactory.openSession();
		ugDaoImpl.save(ug);
		session.close();
	}
}
