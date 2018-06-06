package com.xunmilingnan.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.xunmilingnan.dao.AlbumDao;
import com.xunmilingnan.dao.ArticleDao;
import com.xunmilingnan.dao.NewsDao;
import com.xunmilingnan.dao.ProgramDao;
import com.xunmilingnan.dao.SpecialTopicDao;
import com.xunmilingnan.dao.UserDao;
import com.xunmilingnan.entity.Album;
import com.xunmilingnan.entity.Article;
import com.xunmilingnan.entity.News;
import com.xunmilingnan.entity.Program;
import com.xunmilingnan.entity.SpecialTopic;
import com.xunmilingnan.entity.User;
import com.xunmilingnan.statics.Page;
import com.xunmilingnan.statics.Result;

@Service
@Repository//�й���Ϣ  ���� �ķ���
public class NewsAndRetrievalService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource
	private UserDao usDao;
	@Resource
	private SpecialTopicDao stDao;
	@Resource
	private ArticleDao arDao;
	@Resource
	private AlbumDao alDao;
	@Resource
	private ProgramDao prDao;
	@Resource
	private NewsDao neDao;
	
	
	private Page page = new Page (10);
	
	/*"��Ϣ"�Ĳ���-------------------------------------------------------------------*/
	//	1. ��ȡĳ�û���������Ϣ���Ѷ�δ����������
	public HashMap<String, Object> getNewsByUid(int uId,int pagNum){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		page.setList(neDao.getListByUid(uId));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("News",page.getPartList());
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
	//	2. ��ȡ���鿴�����û���ȡĳ��Ϣ�󣬸�����Ϣ��״̬Ϊ�Ѷ���
	public HashMap<String, Object> redNews(int nId){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		News news = neDao.getById(nId);
		news.setState(true);//״̬  true Ϊ�Ѷ�  false  Ϊδ��
		neDao.upDate(news);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("News",news);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. ĳ�û�ɾ��ĳ����Ϣ
	public HashMap<String, Object> delNews(int nId){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		neDao.deleteById(nId);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. ĳ�û�ɾ��ĳ����������Ϣ
	public HashMap<String, Object> delNewsList(int uId,int gro){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		neDao.deleteByUidGro(uId,gro);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
	
	/*"����"�Ĳ���-------------------------------------------------------------------*/
	//	1.��������retrieval
	public HashMap<String, Object> simpleRetrieval(String ret){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		//user
		List<Object> oblist = Arrays.asList(usDao.getList(ret));
		oblist = this.getTopThree(oblist);
		List<User> uslist = new ArrayList<User>(3);
		for(Object obj :oblist) {
			uslist.add((User)obj);
		}
		//SpecialTopic
		oblist = Arrays.asList(stDao.getList(ret));
		oblist = this.getTopThree(oblist);
		List<SpecialTopic> stlist = new ArrayList<SpecialTopic>(3);
		for(Object obj :oblist) {
			stlist.add((SpecialTopic)obj);
		}
		//Article
		oblist = Arrays.asList(arDao.getList(ret));
		oblist = this.getTopThree(oblist);
		List<Article> arlist = new ArrayList<Article>(3);
		for(Object obj :oblist) {
			arlist.add((Article)obj);
		}
		//Album
		oblist = Arrays.asList(alDao.getList(ret));
		oblist = this.getTopThree(oblist);
		List<Album> allist = new ArrayList<Album>(3);
		for(Object obj :oblist) {
			allist.add((Album)obj);
		}
		//Program
		oblist = Arrays.asList(prDao.getList(ret));
		oblist = this.getTopThree(oblist);
		List<Program> prlist = new ArrayList<Program>(3);
		for(Object obj :oblist) {
			prlist.add((Program)obj);
		}
		session.close();
		Map message =new HashMap<String, Object>(5)
		{{
			put("Users",uslist);//�û�
			put("SpecialTops",stlist);//ר��
			put("Articles",arlist);//����
			put("Albums",allist);//ר��
			put("Programs",prlist);//��Ŀ
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//����ÿ���б��ǰ����
	public List<Object> getTopThree(List<Object> oblist){
		List<Object> rlist = new ArrayList();
		int num=0;
		if(oblist.size()<3) 
			num=oblist.size();
		else
			num = 3;
		for(int i =0;i<num;i++) 
			rlist.add(oblist.get(i));
		
		return rlist;
	}
	//	������ϸҳ
	//	1. �û�
	public HashMap<String, Object> retrievalUser(String ret,int pagNum){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		page.setList(usDao.getList(ret));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("Users",page.getPartList());//�û�
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. ר��
	public HashMap<String, Object> retrievalSpecuakTop(String ret,int pagNum){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		page.setList(stDao.getList(ret));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpecialTops",page.getPartList());//ר��
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. ����
	public HashMap<String, Object> retrievalArticle(String ret,int pagNum){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		page.setList(arDao.getList(ret));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("Articles",page.getPartList());//����
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. ר��
	public HashMap<String, Object> retrievalAlbum(String ret,int pagNum){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		page.setList(alDao.getList(ret));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("Albums",page.getPartList());//ר��
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	5. ��Ŀ
	public HashMap<String, Object> retrievalProgram(String ret,int pagNum){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		page.setList(prDao.getList(ret));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("Programs",page.getPartList());//��Ŀ
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}

	
}
