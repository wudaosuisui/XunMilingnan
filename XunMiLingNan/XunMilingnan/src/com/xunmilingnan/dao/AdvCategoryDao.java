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
public class AdvCategoryDao {

	@Resource
	private SessionFactory sessionFactory;
	
	/*save*/
	public void save(AdvCategory obj ) {
		Session session = sessionFactory.getCurrentSession();//Ëé∑Âèñsessio
		Transaction tra = session.beginTransaction();//Âº?ÂêØ‰∫ãÂä?
		session.save(obj);
		session.flush();
		tra.commit();
	}
	
	/*get*/
	public List<AdvCategory> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from AdvCategory");
		return q.list();
	}
	public List<AdvCategory> getRadioList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from AdvCategory where type=1");// where AdvCategory.type>1
		System.out.println("dao out success");
		return q.list();
	}
	public List<AdvCategory> getAdvList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from AdvCategory where type=0");
		return q.list();
	}
	public List<AdvCategory> getObgList(int type){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from AdvCategory where type="+type);
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
	//By AdvCategory
	public void delete(AdvCategory obj) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(obj);
		session.flush();
		tra.commit();
	}
	
	//By id ”–¥˝≤‚ ‘
	public void deleteById(int id ) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		AdvCategory adv = new AdvCategory();
		adv.setId(id);
		session.delete(adv); 
		session.flush();
		tra.commit();
		
	}

}
