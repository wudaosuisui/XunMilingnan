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
@Repository//有关消息  检索 的服务
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
	
	/*"消息"的操作-------------------------------------------------------------------*/
	//	1. 获取某用户的所有消息（已读未读都包含）
	public HashMap<String, Object> getNewsByUid(int uId,int pagNum){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		page.setList(neDao.getListByUid(uId));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("News",page.getPartList());
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
	//	2. 读取（查看）（用户读取某消息后，更改消息的状态为已读）
	public HashMap<String, Object> redNews(int nId){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		News news = neDao.getById(nId);
		news.setState(true);//状态  true 为已读  false  为未读
		neDao.upDate(news);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("News",news);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. 某用户删除某条消息
	public HashMap<String, Object> delNews(int nId){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		neDao.deleteById(nId);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. 某用户删除某分组所有消息
	public HashMap<String, Object> delNewsList(int uId,int gro){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		neDao.deleteByUidGro(uId,gro);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
	
	/*"检索"的操作-------------------------------------------------------------------*/
	//	1.初级检索retrieval
	public HashMap<String, Object> simpleRetrieval(String ret){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
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
			put("Users",uslist);//用户
			put("SpecialTops",stlist);//专题
			put("Articles",arlist);//文章
			put("Albums",allist);//专辑
			put("Programs",prlist);//节目
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//返回每个列表的前三个
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
	//	检索详细页
	//	1. 用户
	public HashMap<String, Object> retrievalUser(String ret,int pagNum){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		page.setList(usDao.getList(ret));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("Users",page.getPartList());//用户
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. 专题
	public HashMap<String, Object> retrievalSpecuakTop(String ret,int pagNum){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		page.setList(stDao.getList(ret));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpecialTops",page.getPartList());//专题
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. 文章
	public HashMap<String, Object> retrievalArticle(String ret,int pagNum){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		page.setList(arDao.getList(ret));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("Articles",page.getPartList());//文章
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. 专辑
	public HashMap<String, Object> retrievalAlbum(String ret,int pagNum){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		page.setList(alDao.getList(ret));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("Albums",page.getPartList());//专辑
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	5. 节目
	public HashMap<String, Object> retrievalProgram(String ret,int pagNum){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		page.setList(prDao.getList(ret));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("Programs",page.getPartList());//节目
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}

	
}
