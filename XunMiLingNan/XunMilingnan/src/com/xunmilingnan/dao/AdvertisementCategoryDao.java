package com.xunmilingnan.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xunmilingnan.entity.AdvCategory;

@Repository
public class AdvertisementCategoryDao {

	@Resource
	private SessionFactory sessionFactory;
	
	/*save*/
	public void save(AdvCategory obj ) {
		Session session = sessionFactory.getCurrentSession();//获取sessio
		Transaction tra = session.beginTransaction();//�?启事�?
		session.save(obj);
		session.flush();
		tra.commit();
	}
	
	/*get*/
	public List<AdvCategory> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from AdvertisementCategory");
		return q.list();
	}
	public AdvCategory getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		AdvCategory obj = session.get(AdvCategory.class, id);
		tra.commit();
		return obj;
	}
	
	/*upDate*/
	public void upDate(AdvCategory obj) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		session.update(obj);
		session.flush();
		tra.commit();
	}
	
	/*delete*/
	public void delete(AdvCategory obj) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(obj);
		session.flush();
		tra.commit();
		
	}
	

}
