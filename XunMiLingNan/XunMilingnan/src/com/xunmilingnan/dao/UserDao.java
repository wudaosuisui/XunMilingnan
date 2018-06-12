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
import com.xunmilingnan.entity.User;

@Repository
public class UserDao {

	@Resource
	private SessionFactory sessionFactory;
	
	/*save*/
	public void save(User obj ) {
		Session session = sessionFactory.getCurrentSession();//Ëé∑Âèñsessio
		Transaction tra = session.beginTransaction();//Âº?ÂêØ‰∫ãÂä?
		session.save(obj);
		session.flush();
		tra.commit();
	}
	
	/*get*/
	public List<User> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from User");
		return q.list();
	}
	public List<User> getList(String ret){//ºÏÀ˜”√ªß√˚
		Query q=this.sessionFactory.getCurrentSession().createQuery("from User where userName ='%"+ret+"%'");
		return q.list();
	}
	//follow list -> user list
	public List<User> getUserListByFL(List<Follow> folList){
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		List<User> usList = new ArrayList();
		for(Follow fo : folList) {
			User user = session.get(User.class, fo.getFsid());
			usList.add(user);
		}
		tra.commit();
		return usList;
	}

	public User getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		User obj = session.get(User.class, id);
		tra.commit();
		return obj;
	}
	public User getByOpenid(String key ) {
		Query q=this.sessionFactory.getCurrentSession().createQuery("from User where openid = '"+key+"'");
//		return (User)q.list().get(0);
		if(q.list().size()<1) {
			System.out.println("ul.size()<1");
			return null;
		}else {
			System.out.println("ul.size()>=1");
			return (User)q.list().get(0);
		}

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




//System.out.println("get  dao success  key is "+ key);
//Query q=this.sessionFactory.getCurrentSession().createQuery("from User");
//System.out.println("select seccess ");
//System.out.println("q is  "+q);
//System.out.println("q.list    "+q.list());
//List<User> ul = q.list();
//System.out.println("ul is "+ul+"  key is "+key);
//if(ul.size()<1) {
//	System.out.println("ul.size()<1");
//	return null;
//}else {
//	System.out.println("ul.size()>=1");
//	for(User u : ul) {
//		if(u.getOpenid()==key) {
//			System.out.println("get user ");
//			return u;
//		}
//	}
//	return null;
//}


//System.out.println("get  dao success  key is "+ key);
//Query q=this.sessionFactory.getCurrentSession().createQuery("from User where openid ="+key);
//System.out.println("q is "+q+"  key is  " + key+"   q.list() is "+q.list()+"   q.list.size  is  "+q.list().size());
//if(q.list().size()<1) {
//	System.out.println("q.list is null      "+q.list());
//	return null;
//}else {
//	System.out.println("q.list is not  null   "+q.list());
//	return (User) q.list().get(0);
//}
