package com.xunmilingnan.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xunmilingnan.entity.AdvertisementCategory;

@Repository
public class AdvertisementCategoryDao {

	@Resource
	private SessionFactory sessionFactory;
	
	/*save*/
	public void save(AdvertisementCategory obj ) {
		Session session = sessionFactory.getCurrentSession();//获取sessio
		Transaction tra = session.beginTransaction();//�?启事�?
		session.save(obj);
		session.flush();
		tra.commit();
	}
	
	/*get*/
	public List<AdvertisementCategory> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from AdvertisementCategory");
		return q.list();
	}
	public AdvertisementCategory getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		AdvertisementCategory obj = session.get(AdvertisementCategory.class, id);
		tra.commit();
		return obj;
	}
	
	/*upDate*/
	public void upDate(AdvertisementCategory obj) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		session.update(obj);
		session.flush();
		tra.commit();
	}
	
	/*delete*/
	public void delete(AdvertisementCategory obj) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(obj);
		session.flush();
		tra.commit();
		
	}
	

}
