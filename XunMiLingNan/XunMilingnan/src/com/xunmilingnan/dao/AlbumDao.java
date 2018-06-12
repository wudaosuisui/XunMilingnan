package com.xunmilingnan.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xunmilingnan.entity.Album;

@Repository
public class AlbumDao {
	@Resource
	private SessionFactory sessionFactory;
	
	/*save*/
	public void save(Album obj ) {
		Session session = sessionFactory.getCurrentSession();//获取sessio
		Transaction tra = session.beginTransaction();//�?启事�?
		session.save(obj);
		session.flush();
		tra.commit();
	}
	
	/*get*/
	
	public List<Album> getList(int advCatid ){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Album where advCat ="+advCatid);
		return q.list();
	}
	public List<Album> getList(String ret ){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Album where name ='%"+ret+"%'");
		return q.list();
	}
	public Album getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		Album obj = session.get(Album.class, id);
		tra.commit();
		return obj;
	}
	
	/*upDate*/
	public void upDate(Album obj) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		session.update(obj);
		session.flush();
		tra.commit();
	}
	
	/*delete*/
	public void delete(Album obj) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(obj);
		session.flush();
		tra.commit();
		
	}
	public void deleteById(int id) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		Album album = session.get(Album.class, id);
//		album.setId(id);
		session.delete(album);
		session.flush();
		tra.commit();
		
	}
}
