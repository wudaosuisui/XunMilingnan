package com.xunmilingnan.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xunmilingnan.entity.Group;

@Repository
public class Dao {
	
	@Resource
	private SessionFactory sessionFactory;
	
	/*save*/
	public void save(Object obj ) {
		Session session = sessionFactory.getCurrentSession();//获取sessio
		Transaction tra = session.beginTransaction();//�?启事�?
		session.save(obj);
		session.flush();
		tra.commit();
	}
	
	/*get*/
	public List<Object> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from tableName");
		return q.list();
	}
	public Object getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		Object obj = session.get(Object.class, id);
		tra.commit();
		return obj;
	}
	
	/*upDate*/
	public void upDate(Object obj) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		session.update(obj);
		session.flush();
		tra.commit();
	}
	
	/*delete*/
	public void delete(Object obj) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(obj);
		session.flush();
		tra.commit();
		
	}
	
}

