package com.xunmilingnan.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.xunmilingnan.dao.AdvCategoryDao;
import com.xunmilingnan.dao.ArticleDao;
import com.xunmilingnan.dao.CommentDao;
import com.xunmilingnan.dao.SpecialTopicDao;
import com.xunmilingnan.dao.UserDao;
import com.xunmilingnan.entity.AdvCategory;
import com.xunmilingnan.entity.Article;
import com.xunmilingnan.entity.Comment;
import com.xunmilingnan.entity.SpecialTopic;
import com.xunmilingnan.entity.User;
import com.xunmilingnan.statics.Page;
import com.xunmilingnan.statics.Result;

@Service
@Repository//有关专题的服务
public class SpecialTopicService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource
	private AdvCategoryDao acDao;
	@Resource
	private SpecialTopicDao stDao;
	@Resource
	private ArticleDao artDao;
	@Resource
	private CommentDao coDao;
	@Resource
	private UserDao usDao;
	
	
	private Page page = new Page(10);
	
	
	/*"文章"的操作-------------------------------------------------------------------*/
	//	1. 在一个专题下发布一篇文章（用户）（如果有人关注他，对关注他的人发布一条消息）
	public HashMap<String, Object> addArticle(Article art){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		artDao.save(art);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("Article",art);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. 在一个专题下更新一篇文章（用户）提供用户修改文章的功能
	public HashMap<String, Object> updArticle(Article art){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		artDao.upDate(art);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("Article",art);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. 在一个专题下删除一篇文章（用户）提供用户删除文章的功能
	public HashMap<String, Object> delArticle(int id){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		artDao.deleteById(id);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. 查看所有文章(带有分页和筛选)
	public HashMap<String, Object> getArticleList(int pagNum){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		page.setList(artDao.getList());
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("ArticleList",page.getPartList());
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	5. 查看某一专题下的所有文章
	public HashMap<String, Object> getArticleListInSt(int stId,int pagNum){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		page.setList(artDao.getListInSt(stId));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("ArticleList",page.getPartList());
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	6. 对某篇文章进行评论（用户）（审核通过后，对文章作者发送一条消息）
	public HashMap<String, Object> commentArtic(Comment com){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		coDao.save(com);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("Comment",com);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	7. 对某个评论进行回复（由管理员回复，回复后对评论的发布者发送一条消息）
	public HashMap<String, Object> replayComment(Comment com){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		coDao.save(com);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("Comment",com);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	8. 收藏某篇文章（收藏量+1）（用户）
	public void addForward(int id ){
		 this.artAddOne(id, 0);
	}
	//	9. 获取某用户收藏的文章
	//	10. 浏览（浏览量+1）（用户）
	public HashMap<String, Object> addBrowse(int id ){
		return this.artAddOne(id, 1);
	}
	//	11. 点赞（点赞量+1）（用户）
	public HashMap<String, Object> addPraise(int id ){
		return this.artAddOne(id, 2);
	}
	//	12. 获取某个用户所写的所有文章
	public HashMap<String, Object> getArticleListByUser(int uId,int pagNum){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		page.setList(artDao.getListInU(uId));
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("ArticleList",page.getPartList());
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	13. 获取某个用户收藏的所有文章
	
	//	14.	审核通过某个评论（接6）      
	public HashMap<String, Object> auditingComment(int adId,int coId){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		//获取管理员
		User admin = usDao.getById(adId);
		//获取对应的comment
		Comment com = coDao.getById(coId);
		com.setAuditing(true);
		com.setAdmin(admin);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("Comment",com);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
	//通过 id 获取一条评论
	public Comment getCommentById(int id ) {
		Session session = sessionFactory.openSession();
		Comment co =coDao.getById(id);
		session.close();
		return co;
	}
	//获取文章对象
	public Article getArticleById(int id ) {
		Session session = sessionFactory.openSession();
		Article art = artDao.getById(id);
		session.close();
		return art;
	}
	//加一操作
	public HashMap<String, Object> artAddOne(int id ,int type) {
		//type  0  - forward收藏 ; ;1 - browse 浏览 ;2 - praise 点赞
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Article art = this.getArticleById(id);
		if(type ==0) {
			art.setForward(art.getForward()+1);
		}else if(type==1) {
			art.setBrowse(art.getBrowse()+1);
		}else if(type==2) {
			art.setPraise(art.getPraise()+1);
		}
		this.updArticle(art);
		Map message =new HashMap<String, Object>(1){{
			put("Article",art);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	/*"专题"的操作-------------------------------------------------------------------*/
	//	1. 创建一个专题
	public HashMap<String, Object> addSpeTop(SpecialTopic st){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		stDao.save(st);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpecialTopic",st);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. 删除一个专题
	public HashMap<String, Object> delSpeTop(int id ){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		stDao.deleteById(id);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. 更新一个专题
	public HashMap<String, Object> updSpeTop(SpecialTopic st){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		stDao.upDate(st);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpecialTopic",st);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. 获取某专题分类下的所有专题(带有分页和筛选)
	public HashMap<String, Object> stListInStCat(int stcId,int pagNum){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		page.setList(stDao.getListInStCat(stcId));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpecialTopicList",page.getPartList());
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//通过id获取专题
	public SpecialTopic getSpecialTopicById(int id ) {
		Session session = sessionFactory.openSession();
		SpecialTopic st = stDao.getById(id);
		session.close();
		return st;
	}
	/*专题分类的操作-------------------------------------------------------------------*/
	//	1. 创建一个专题分类
	public HashMap<String, Object> addSpeTopCat(AdvCategory stCat){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		acDao.save(stCat);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpeTopCategory",stCat);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. 删除一个专题分类
	public HashMap<String, Object>	delSpeTopCat(int id ){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		acDao.deleteById(id);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. 更新一个专题分类
	public HashMap<String, Object> updSpeTopCat(AdvCategory stCat){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		acDao.upDate(stCat);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpeTopCategory",stCat);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. 获取全部专题分类(带有分页和筛选)
	public HashMap<String, Object> speTopCatList(int pagNum){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		page.setList(acDao.getObgList(2));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpeTopCategoryList",page.getPartList());
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//通过id  获取一个 专题分类
	public  AdvCategory getStCat(int id ) {
		Session session = sessionFactory.openSession();
		AdvCategory stc = acDao.getById(id);
		session.close();
		return stc;
	}

	
}
