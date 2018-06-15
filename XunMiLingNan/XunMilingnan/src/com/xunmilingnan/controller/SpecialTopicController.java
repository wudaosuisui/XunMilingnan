package com.xunmilingnan.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMethod;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xunmilingnan.entity.Admin;
import com.xunmilingnan.entity.AdvCategory;
import com.xunmilingnan.entity.Article;
import com.xunmilingnan.entity.Comment;
import com.xunmilingnan.entity.Follow;
import com.xunmilingnan.entity.Program;
import com.xunmilingnan.entity.SpecialTopic;
import com.xunmilingnan.entity.User;
import com.xunmilingnan.service.AdmainService;
import com.xunmilingnan.service.FollowService;
import com.xunmilingnan.service.NewsAndRetrievalService;
import com.xunmilingnan.service.SpecialTopicService;
import com.xunmilingnan.service.UserService;
import com.xunmilingnan.statics.ResponseJsonUtils;
import com.xunmilingnan.statics.Result;

@Controller//关于专题（专题里的文章也包括）的所有controller
@Repository
@RequestMapping("/spectop")
public class SpecialTopicController {
//	@Resource
//	private SessionFactory sessionFactory;
	@Resource
	private SpecialTopicService stService;
	@Resource
	private UserService usService;
	@Resource
	private NewsAndRetrievalService neService;
	@Resource
	private AdmainService adService;
	@Resource
	private FollowService foService;
	
	/*"文章"的操作-------------------------------------------------------------------*/
	//	1. 在一个专题下发布一篇文章（用户）（如果有人关注他，对关注他的人发布一条消息）
	@PostMapping("/addar")
	public void addArticle(HttpServletResponse response,
			@RequestParam(value="stId") int stId,
			@RequestParam(value="userId") int userId,
			@RequestParam(value="publishTime") String publishTime,
			@RequestParam(value="title") String title,
			@RequestParam(value="text") String text,
			@RequestParam(value="sortNumber",required=false, defaultValue="0") int sortNumber
			) {
		//获取专题
		SpecialTopic st = stService.getSpecialTopicById(stId);
		//获取user
		User user = usService.getUserById(userId);
		//创建文章对象
		Article art = new Article(st,user,publishTime,title,text,sortNumber);
		//存入
		HashMap<String, Object> map = stService.addArticle(art);
//		Map message = (Map)map.get("message");
		Article ar = (Article)((Map)map.get("message")).get("message");
		//给关注此作者的用户发一条消息
		neService.sendNewsForArticle(user,ar.getId());
		//存入并返回
		ResponseJsonUtils.json(response,map);
	}
	//	2. 在一个专题下更新一篇文章（用户）提供用户修改文章的功能
	@PostMapping("/updar")
	public void updArticle(HttpServletResponse response,
			@RequestParam(value="arId") int arId,
			@RequestParam(value="stId") int stId,
			@RequestParam(value="userId") int userId,
			@RequestParam(value="publishTime") String publishTime,
			@RequestParam(value="title") String title,
			@RequestParam(value="text") String text,
			@RequestParam(value="sortNumber",required=false, defaultValue="0") int sortNumber
			) {
		//获取专题
		SpecialTopic st = stService.getSpecialTopicById(stId);
		//获取user
		User user = usService.getUserById(userId);
		//创建文章对象
		Article art = new Article(st,user,publishTime,title,text,sortNumber);
		//输入id
		art.setId(arId);
		//存入并返回
		ResponseJsonUtils.json(response, stService.updArticle(art));
	}
	//	3. 在一个专题下删除一篇文章（用户）提供用户删除文章的功能
	@PostMapping("/delar")
	public void delArticle(HttpServletResponse response,
			@RequestParam(value="arId") int arId
			) {
		//删除
		ResponseJsonUtils.json(response, stService.delArticle(arId));
	}
	//	4. 查看所有文章(带有分页和筛选)
	@PostMapping("/arlist")
	public void getArticleList(HttpServletResponse response,
			@RequestParam(value="pagNum") int pagNum,
			@RequestParam(value="limit") int limit
			) {
		//删除
		ResponseJsonUtils.json(response, stService.getArticleList(pagNum,limit));
	}
	//	5. 查看某一专题下的所有文章
	@PostMapping("/arlistinst")
	public void getArticleListInSt(HttpServletResponse response,
			@RequestParam(value="stId") int stId,
			@RequestParam(value="pagNum") int pagNum,
			@RequestParam(value="limit") int limit
			) {
		//删除
		ResponseJsonUtils.json(response, stService.getArticleListInSt(stId,pagNum,limit));
	}
	//	6. 对某篇文章进行评论（用户）（审核通过后，对文章作者发送一条消息）
	@PostMapping("/comar")
	public void commentArtic(HttpServletResponse response,
			@RequestParam(value="arId") int arId,//文章的id
			@RequestParam(value="cusId") int cusId,//评论者的id
			@RequestParam(value="time") String time,//评论时间
			@RequestParam(value="text") String text//评论内容
			) {
		//获取评论的user
		User user = usService.getUserById(cusId);
		//创建一个“评论”
		Comment com = new Comment(user,time,text,0,arId);
		//存入并返回
		ResponseJsonUtils.json(response, stService.commentArtic(com));
	}
	//	7. 对某个评论进行回复（由管理员回复，回复后对评论的发布者发送一条消息）
	@PostMapping("/repcom")
	public void replayComment(HttpServletResponse response,
			@RequestParam(value="adId") int adId,//管理员的id
			@RequestParam(value="comId") int comId,//被回复的“评论”
			@RequestParam(value="time") String time,//评论时间
			@RequestParam(value="text") String text//回复内容
			) {
		//admin
		User ad = usService.getUserById(adId);
		//comment
		Comment com = stService.getCommentById(comId);
		//创建一个“评论”
		Comment rep = new Comment(com,ad,time,text,com.getType(),com.getWorks());
		//存入并返回
		ResponseJsonUtils.json(response, stService.replayComment(com));
	}
	//	8. 收藏某篇文章（收藏量+1）（用户）
	@PostMapping("/folart")
	public void folArticle(HttpServletResponse response,
			@RequestParam(value="arId") int arId,//文章的id
			@RequestParam(value="usId") int usId//用户的id
			) {
		//获取User;
		User user = usService.getUserById(usId);
		//收藏文章加一
		stService.addForward(arId);
		//创建一个Follow
		Follow fo = new Follow(user,0,arId);
		//将Follow存入
		ResponseJsonUtils.json(response, foService.addFollow(fo));
	}
	//	9. 获取某用户收藏的文章
	@PostMapping("/getfolarts")
	public void getFollowArt(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="pagNum",required=false, defaultValue="0")  int pagNum,
			@RequestParam(value="limit")  int limit
			) {
		ResponseJsonUtils.json(response, foService.getFollowArt(uId,pagNum,limit));
	}
	//	10. 浏览（浏览量+1）（用户）
	@PostMapping("/browseart")
	public void browseArticle(HttpServletResponse response,
			@RequestParam(value="aId")  int aId
			) {
		ResponseJsonUtils.json(response,stService.addBrowse(aId));
	}
	//	11. 点赞（点赞量+1）（用户）
	@PostMapping("/praiseart")
	public void praiseArticle(HttpServletResponse response,
			@RequestParam(value="aId")  int aId
			) {
		ResponseJsonUtils.json(response,stService.addPraise(aId));
	}
	//	12. 获取某个用户所写的所有文章
	@PostMapping("/userslistbyus")
	public void getArticleListByUser(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="pagNum")  int pagNum,
			@RequestParam(value="limit") int limit//页码
			) {
		ResponseJsonUtils.json(response,stService.getArticleListByUser(uId,pagNum,limit));
	}
	//	14.	审核通过某个评论（接6）   
	@PostMapping("/audcom")//examine
	public void auditingComment(HttpServletResponse response,
			@RequestParam(value="adId")  int adId,
			@RequestParam(value="coId")  int coId
			) {
		ResponseJsonUtils.json(response,stService.auditingComment(adId,coId));
	}
	/*"专题"的操作-------------------------------------------------------------------*/
	//	1. 创建一个专题
	@PostMapping("/addst")
	public void addSpeTop(HttpServletResponse response,
			@RequestParam(value="stCatId") int stCatId,
			@RequestParam(value="title") String title,
			@RequestParam(value="startTime") String startTime,
			@RequestParam(value="endTime") String  endTime,
			@RequestParam(value="article") String article, 
			@RequestParam(value="img") String img,
			@RequestParam(value="showTime") String  showTime,
			@RequestParam(value="isHot",required=false, defaultValue="0") int isHot,
			@RequestParam(value="sortNumber",required=false, defaultValue="0") int sortNumber
	) {
		//获取专题分类
		AdvCategory stCat = stService.getStCat(stCatId);
		//创建一个“专题”
		SpecialTopic st = new SpecialTopic(stCat,title,startTime,endTime,article,img,showTime,isHot,sortNumber);
		//存入并返回
		ResponseJsonUtils.json(response, stService.addSpeTop(st));
	}
	//	2. 删除一个专题
	@PostMapping("/delst")
	public void delSpeTop(HttpServletResponse response,
			@RequestParam(value="stId") int stId) {
		//删除并返回
		ResponseJsonUtils.json(response, stService.delSpeTop(stId));
	}
	//	3. 更新一个专题
	@PostMapping("/updst")
	public void updSpeTop(HttpServletResponse response,
			@RequestParam(value="stId") int stId,
			@RequestParam(value="stCatId") int stCatId,
			@RequestParam(value="title") String title,
			@RequestParam(value="startTime") String startTime,
			@RequestParam(value="endTime") String  endTime,
			@RequestParam(value="article") String article, 
			@RequestParam(value="img") String img,
			@RequestParam(value="showTime") String  showTime,
			@RequestParam(value="isHot",required=false, defaultValue="0") int isHot,
			@RequestParam(value="sortNumber",required=false, defaultValue="0") int sortNumber
	) {
		//获取专题分类
		AdvCategory stCat = stService.getStCat(stCatId);
		//创建一个“专题”
		SpecialTopic st = new SpecialTopic(stCat,title,startTime,endTime,article,img,showTime,isHot,sortNumber);
		//输入id
		st.setId(stId);
		//更新并返回
		ResponseJsonUtils.json(response, stService.updSpeTop(st));
	}
	//	4. 获取某专题分类下的所有专题(带有分页和筛选)
	@PostMapping("/stlistinstcat")
	public void stListInStCat(HttpServletResponse response,
			@RequestParam(value="stcId") int stcId,
			@RequestParam(value="pagNum") int pagNum,
			@RequestParam(value="limit") int limit) {
		//获取并返回
		ResponseJsonUtils.json(response, stService.stListInStCat(stcId,pagNum,limit));
	}
	/*"专题分类"的操作-------------------------------------------------------------------*/
	//	1. 创建一个专题分类
	@PostMapping("/addstcat")
	public void addSpeTopCat(HttpServletResponse response,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber",required=false, defaultValue="0") int sortNumber,
			@RequestParam(value="img") String img 
	) {
		//创建一个专题分类
		AdvCategory stCat = new AdvCategory(name,2,sortNumber,img);
		//存入并返回
		ResponseJsonUtils.json(response, stService.addSpeTopCat(stCat));
	}
	//	2. 删除一个专题分类
	@PostMapping("/delstcat")
	public void delSpeTopCat(HttpServletResponse response,
			@RequestParam(value="scId") int scId
	) {
		//存入并返回
		ResponseJsonUtils.json(response, stService.delSpeTopCat(scId));
	}
	//	3. 更新一个专题分类
	@PostMapping("/updstcat")
	public void updSpeTopCat(HttpServletResponse response,
			@RequestParam(value="id") int id,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber",required=false, defaultValue="0") int sortNumber,
			@RequestParam(value="img") String img 
	) {
		//创建一个专题分类
		AdvCategory stCat = new AdvCategory(name,2,sortNumber,img);
		//输入id
		stCat.setId(id);
		//存入并返回
		ResponseJsonUtils.json(response, stService.updSpeTopCat(stCat));
	}
	//	4. 获取全部专题分类(带有分页和筛选)
	@PostMapping("/stcatlist")
	public void speTopCatList(HttpServletResponse response,
			@RequestParam(value="pagNum") int pagNum,
			@RequestParam(value="limit") int limit
	) {
		System.out.println("get /stcatlist ");
		//执行并返回
		ResponseJsonUtils.json(response, stService.speTopCatList(pagNum,limit));
	}
}






