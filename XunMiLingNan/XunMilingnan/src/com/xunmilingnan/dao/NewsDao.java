package com.xunmilingnan.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xunmilingnan.entity.News;

@Repository
public class NewsDao {
	@Resource
	private SessionFactory sessionFactory;
	
	/*save*/
	public void save(News obj ) {
		Session session = sessionFactory.getCurrentSession();//获取sessio
		Transaction tra = session.beginTransaction();//�?启事�?
		session.save(obj);
		session.flush();
		tra.commit();
	}
	public void saveList(List<News> nelist ) {
		Session session = sessionFactory.getCurrentSession();//获取sessio
		Transaction tra = session.beginTransaction();//�?启事�?
		for(News ne : nelist)
			session.save(ne);
		session.flush();
		tra.commit();
	}
	/*get*/
	public List<News> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from News");
		return q.list();
	}
	public News getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		News obj = session.get(News.class, id);
		tra.commit();
		return obj;
	}
	
	/*upDate*/
	public void upDate(News obj) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		session.update(obj);
		session.flush();
		tra.commit();
	}
	
	/*delete*/
	public void delete(News obj) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(obj);
		session.flush();
		tra.commit();
		
	}
}
