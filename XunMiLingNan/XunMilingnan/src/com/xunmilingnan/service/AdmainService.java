package com.xunmilingnan.service;


import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.xunmilingnan.dao.AdminDao;
import com.xunmilingnan.entity.Admin;

@Service
@Repository//有admin的服务
public class AdmainService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource
	private AdminDao adDao;
	
	public Admin getAdminById(int id ) {
		Session session = sessionFactory.openSession();
		Admin ad = adDao.getById(id);
		session.close();
		return ad;
	}
}
