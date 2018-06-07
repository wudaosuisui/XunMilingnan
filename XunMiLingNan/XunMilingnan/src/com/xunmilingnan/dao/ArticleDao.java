package com.xunmilingnan.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xunmilingnan.entity.Article;
import com.xunmilingnan.entity.Follow;

@Repository
public class ArticleDao {

	@Resource
	private SessionFactory sessionFactory;
	
	/*save*/
	public void save(Article obj ) {
		Session session = sessionFactory.getCurrentSession();//获取sessio
		Transaction tra = session.beginTransaction();//�?启事�?
		session.save(obj);
		session.flush();
		tra.commit();
	}
	
	/*get*/
	//��������
	public List<Article> getList(){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Article");
		return q.list();
	}
	public List<Article> getList(String ret){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Article where title = ?"+ret+"?");
		return q.list();
	}
	//ĳר���µ���������
	public List<Article> getListInSt(int stId){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Article where activity = " + stId);
		return q.list();
	}
	//��ȡĳ�û���д����������
	public List<Article> getListInU(int uId){
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Article where user = " + uId);
		return q.list();
	}
	//��ȡĳ�û�д���������µ�����
	public int getCountInU(int uId) {
		Query q=this.sessionFactory.getCurrentSession().createQuery("from Article where user = " + uId);
		return q.list().size();
	}
	//follow list -> article list
	public List<Article> getListByFollowList(List<Follow> foList){
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		List<Article> artlist = new ArrayList(foList.size());
		for(Follow fol : foList) {
			artlist.add(session.get(Article.class, fol.getFsid()));
		}
		tra.commit();
		return artlist;
	}
	
	public Article getById(int id ) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		Article obj = session.get(Article.class, id);
		tra.commit();
		return obj;
	}
	
	/*upDate*/
	public void upDate(Article obj) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();
		session.update(obj);
		session.flush();
		tra.commit();
	}
	
	/*delete*/
	public void delete(Article obj) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		session.delete(obj);
		session.flush();
		tra.commit();
	}
	public void deleteById(int id) {
		Session session = sessionFactory.getCurrentSession(); 
		Transaction tra = session.beginTransaction();
		Article ar = new Article();
		ar.setId(id);
		session.delete(ar);
		session.flush();
		tra.commit();
	}

}
