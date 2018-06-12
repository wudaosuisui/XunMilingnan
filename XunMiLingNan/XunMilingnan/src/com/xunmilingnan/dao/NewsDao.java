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
		Session session = sessionFactory.getCurrentSession();//鑾峰彇sessio
		Transaction tra = session.beginTransaction();//寮?鍚簨鍔?
		session.save(obj);
		session.flush();
		tra.commit();
	}
	public void saveList(List<News> nelist ) {
		Session session = sessionFactory.getCurrentSession();//鑾峰彇sessio
		Transaction tra = session.beginTransaction();//寮?鍚簨鍔?
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
	//获取某用户的所有消息
	public List<News> getListByUid(int uid){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from News where user = "+uid);
		return q.list();
	}
	//获取某用户的未读的消息数量
	public int getCountInU(int uid){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from News where user = "+uid+" and state is "+false);
		return q.list().size();
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
	public void deleteById(int Id) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		News ne = new News();
		ne.setId(Id);
		session.delete(ne);
		session.flush();
		tra.commit();
		
	}
	public void deleteByUidGro(int uId,int gro) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		Query q=this.sessionFactory.getCurrentSession().createQuery("from News where user = "+uId+"  and gro = "+gro);
		List<News> newslist =  q.list();
		for(News ne : newslist)
			session.delete(ne);
		session.flush();
		tra.commit();
	}
}
