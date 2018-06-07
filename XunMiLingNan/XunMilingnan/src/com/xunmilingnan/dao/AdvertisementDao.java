package com.xunmilingnan.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xunmilingnan.entity.Advertisement;


@Repository
public class AdvertisementDao {

	@Resource
	private SessionFactory sessionFactory;
	
	/*save*/
	public void save(Advertisement obj ) {
		Session session = sessionFactory.getCurrentSession();//获取sessio
		Transaction tra = session.beginTransaction();//�?启事�?
		session.save(obj);
		session.flush();
		tra.commit();
	}
	
	/*get*/
	public List<Advertisement> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Advertisement");
		System.out.println("get list success");
		return q.list();
	}
	public List<Advertisement> getListByAdvCatId(int id ){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Advertisement where adCategory = "+id);
		return q.list();
	}
	public Advertisement getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		Advertisement obj = session.get(Advertisement.class, id);
		tra.commit();
		return obj;
	}
	
	/*upDate*/
	public void upDate(Advertisement obj) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		session.update(obj);
		session.flush();
		tra.commit();
	}
	
	/*delete*/
	public void delete(Advertisement obj) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(obj);
		session.flush();
		tra.commit();
		
	}
	public void deleteById(int id) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		Advertisement adv = new Advertisement();
		adv.setId(id);
		session.delete(adv);
		session.flush();
		tra.commit();
		
	}

}
