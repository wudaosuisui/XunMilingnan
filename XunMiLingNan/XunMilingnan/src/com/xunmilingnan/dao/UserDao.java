package com.xunmilingnan.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xunmilingnan.entity.User;

@Repository
public class UserDao {

	@Resource
	private SessionFactory sessionFactory;
	
	/*save*/
	public void save(User obj ) {
		Session session = sessionFactory.getCurrentSession();//获取sessio
		Transaction tra = session.beginTransaction();//�?启事�?
		session.save(obj);
		session.flush();
		tra.commit();
	}
	
	/*get*/
	public List<User> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from User");
		return q.list();
	}
	public User getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		User obj = session.get(User.class, id);
		tra.commit();
		return obj;
	}
	
	/*upDate*/
	public void upDate(User obj) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		session.update(obj);
		session.flush();
		tra.commit();
	}
	
	/*delete*/
	public void delete(User obj) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(obj);
		session.flush();
		tra.commit();
		
	}

}
