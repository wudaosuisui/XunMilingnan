package com.xunmilingnan.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xunmilingnan.entity.Comment;

@Repository
public class CommentDao {

	@Resource
	private SessionFactory sessionFactory;
	
	/*save*/
	public void save(Comment obj ) {
//		System.out.println("get Comment Dao  success");
		Session session = sessionFactory.getCurrentSession();//获取sessio
		Transaction tra = session.beginTransaction();//�?启事�?
		session.save(obj);
//		System.out.println("save Comment Dao  success");
		session.flush();
		tra.commit();
//		System.out.println("out Comment Dao  success");
	}
	
	/*get*/
	public List<Comment> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Comment");
		return q.list();
	}
	public Comment getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		Comment obj = session.get(Comment.class, id);
		tra.commit();
		return obj;
	}
	
	/*upDate*/
	public void upDate(Comment obj) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		session.update(obj);
		session.flush();
		tra.commit();
	}
	
	/*delete*/
	public void delete(Comment obj) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(obj);
		session.flush();
		tra.commit();
		
	}
	

}
