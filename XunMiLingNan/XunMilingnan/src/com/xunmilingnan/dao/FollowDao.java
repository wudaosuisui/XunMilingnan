package com.xunmilingnan.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xunmilingnan.entity.Follow;

@Repository
public class FollowDao {
	@Resource
	private SessionFactory sessionFactory;
	
	/*save*/
	public void save(Follow obj ) {
		Session session = sessionFactory.getCurrentSession();//鑾峰彇sessio
		Transaction tra = session.beginTransaction();//寮?鍚簨鍔?
		session.save(obj);
		session.flush();
		tra.commit();
	}
	
	/*get*/
	//获取全部的follow  （基本没什么用）
	public List<Follow> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Follow");
		return q.list();
	}
	public List<Follow> getList(int uId){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Follow where user = "+ uId);
		return q.list();
	}
	//user + type  -> fsid list
	public List<Follow> getListByUT(int userId,int type){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Follow where user = " +userId+" and type="+type);
		return q.list();
	}
	//fsid + type -> user list
	public List<Follow> getListByFT(int fsid,int type){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Follow where fsid = " +fsid+" and type="+type);
		return q.list();
	}
	public Follow getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		Follow obj = session.get(Follow.class, id);
		tra.commit();
		return obj;
	}
	
	/*upDate*/
	public void upDate(Follow obj) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		session.update(obj);
		session.flush();
		tra.commit();
	}
	
	/*delete*/
	public void delete(Follow obj) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(obj);
		session.flush();
		tra.commit();
		
	}
	
}
