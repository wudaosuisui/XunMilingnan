package com.xunmilingnan.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xunmilingnan.entity.SpecialTopic;

@Repository
public class SpecialTopicDao {
	
	@Resource
	private SessionFactory sessionFactory;
	
	/*save*/
	public void save( SpecialTopic act ) {
		Session session = sessionFactory.getCurrentSession();//获取sessio
		Transaction tra = session.beginTransaction();//�?启事�?
		session.save(act);
		session.flush();
		tra.commit();
	}
	
	/*get*/
	public List<SpecialTopic> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Activity");
		return q.list();
	}
	public SpecialTopic getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		SpecialTopic act = session.get(SpecialTopic.class, id);
		tra.commit();
		return act;
	}
	
	/*upDate*/
	public void upDate(SpecialTopic act) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		session.update(act);
		session.flush();
		tra.commit();
	}
	
	/*delete*/
	public void delete(SpecialTopic act) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(act);
		session.flush();
		tra.commit();
		
	}
	

}
