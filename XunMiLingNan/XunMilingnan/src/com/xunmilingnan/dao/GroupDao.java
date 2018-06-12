package com.xunmilingnan.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xunmilingnan.entity.Group;
import com.xunmilingnan.entity.User;


@Repository
public class GroupDao {
	
	@Resource
	private SessionFactory sessionFactory;
	
	/*保存*/
	//save 
	public void save(Group ug) {
		Session session = sessionFactory.getCurrentSession();//获取sessio
		Transaction tra = session.beginTransaction();//�?启事�?
		session.save(ug);
		session.flush();
		tra.commit();
	}
	/*获取*/
	public List<Group> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from User_group");
		return q.list();
	}
	
	//get by id
	public Group getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		Group ug = session.get(Group.class, id);
		tra.commit();
		return ug;
	}
	
	public Group getBySign(String sign ) {
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Group where sign = '"+sign+"'");//User_group
//		List<Group> gl = q.list();
//		return gl.get(0);
		if(q.list().size()<1) {
			System.out.println("ul.size()<1");
			return null;
		}else {
			System.out.println("ul.size()>=1");
			return (Group)q.list().get(0);
		}
	}
	/*修改*/
	public void upDate(Group ug) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		session.update(ug);
		session.flush();
		tra.commit();
	}
	/*删除*/
	public void delete(Group ug) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(ug);
		session.flush();
		tra.commit();
		
	}
	
	
}
