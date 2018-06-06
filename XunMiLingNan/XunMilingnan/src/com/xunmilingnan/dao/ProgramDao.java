package com.xunmilingnan.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xunmilingnan.entity.Follow;
import com.xunmilingnan.entity.Program;
import com.xunmilingnan.statics.Uploads;

@Repository
public class ProgramDao {

	@Resource
	private SessionFactory sessionFactory;
	
	
	private Uploads uploads = new Uploads();
	
	
	/*save*/
	public void save(Program obj ) {
		Session session = sessionFactory.getCurrentSession();//鑾峰彇sessio
		Transaction tra = session.beginTransaction();//寮?鍚簨鍔?
		session.save(obj);
		session.flush();
		tra.commit();
	}
	
	/*get*/
	public List<Program> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Program");
		return q.list();
	}
	public List<Program> getListByAlbumId(int id ){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Program where album="+id);
		return q.list();
	}
	public List<Program> getListByAdvCatId(int id ){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Program where advCat="+id);
		return q.list();
	}
	public Program getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		Program obj = session.get(Program.class, id);
		tra.commit();
		return obj;
	}
	public List<Program> getListByFollowList(List<Follow> foList){
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		//创建一个 program list
		List<Program> proList =  new ArrayList(foList.size());
		for(Follow fol : foList) {
			proList.add(session.get(Program.class, fol.getFsid()));
		}
		tra.commit();
		return proList;
	}
	/*upDate*/
	public void upDate(Program obj) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		this.uploads.deleteRad(obj.getFMName());//删除对应的音频文件
		session.update(obj);
		session.flush();
		tra.commit();
	}
	
	/*delete*/
	public void delete(Program obj) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(obj);
		session.flush();
		tra.commit();
		
	}
	public void deleteById(int id ) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		Program pro = getById(id);
		this.uploads.deleteRad(pro.getFMName());//删除对应的音频文件
		session.delete(pro);
		session.flush();
		tra.commit();
	}
	public void deleteList(List<Program> list) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		for(Program pro : list) {
			uploads.deleteRad(pro.getFMName());
			session.delete(pro);
		}
		session.flush();
		tra.commit();
	}

}
