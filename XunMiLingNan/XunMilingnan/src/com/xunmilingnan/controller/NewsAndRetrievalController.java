package com.xunmilingnan.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xunmilingnan.service.NewsAndRetrievalService;
import com.xunmilingnan.statics.ResponseJsonUtils;

@Controller//关于消息的所有controller
@Repository
//@RequestMapping("/news")
public class NewsAndRetrievalController {
	@Resource
	public NewsAndRetrievalService nrService;
	/*"消息"的操作-------------------------------------------------------------------*/
	//	1. 获取某用户的所有消息（已读未读都包含）
	@RequestMapping("news/getnews")
	public void getNewsByUid(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="pagNum")  int pagNum
			) {
		ResponseJsonUtils.json(response,nrService.getNewsByUid(uId,pagNum));
	}
	//	2. 读取（查看）（用户读取某消息后，更改消息的状态为已读）
	@RequestMapping("news/rednews")
	public void redNews(HttpServletResponse response,
			@RequestParam(value="nId")  int nId
			) {
		ResponseJsonUtils.json(response,nrService.redNews(nId));
	}
	//	3. 某用户删除某条消息
	@RequestMapping("news/delnews")
	public void delNews(HttpServletResponse response,
			@RequestParam(value="nId")  int nId
			) {
		ResponseJsonUtils.json(response,nrService.delNews(nId));
	}
	//	4. 某用户删除某分组所有消息
	@RequestMapping("news/delnewslist")
	public void delNewsList(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="gro")  int gro
			) {
		ResponseJsonUtils.json(response,nrService.delNewsList(uId,gro));
	}
	
	
	/*"检索"的操作-------------------------------------------------------------------*/
	//简单检索
	@RequestMapping("retr/simret")
	public void simpleRetrieval(HttpServletResponse response,
			@RequestParam(value="ret")  String ret//检索内容
			) {
		ResponseJsonUtils.json(response,nrService.simpleRetrieval(ret));
	}
	//user
	@RequestMapping("retr/user")
	public void retrievalUser(HttpServletResponse response,
			@RequestParam(value="ret")  String ret,//检索内容
			@RequestParam(value="pagNum")  int pagNum
			) {
		ResponseJsonUtils.json(response,nrService.retrievalUser(ret,pagNum));
	}
	//SpecuakTop
	@RequestMapping("retr/specuaktop")
	public void retrievalSpecuakTop(HttpServletResponse response,
			@RequestParam(value="ret")  String ret,//检索内容
			@RequestParam(value="pagNum")  int pagNum
			) {
		ResponseJsonUtils.json(response,nrService.retrievalSpecuakTop(ret,pagNum));
	}
	//Article
	@RequestMapping("retr/article")
	public void retrievalArticle(HttpServletResponse response,
			@RequestParam(value="ret")  String ret,//检索内容
			@RequestParam(value="pagNum")  int pagNum
			) {
		ResponseJsonUtils.json(response,nrService.retrievalArticle(ret,pagNum));
	}
	//Album
	@RequestMapping("retr/album")
	public void retrievalAlbum(HttpServletResponse response,
			@RequestParam(value="ret")  String ret,//检索内容
			@RequestParam(value="pagNum")  int pagNum
			) {
	ResponseJsonUtils.json(response,nrService.retrievalAlbum(ret,pagNum));
	}
	//Program
	@RequestMapping("retr/program")
	public void retrievalProgram(HttpServletResponse response,
			@RequestParam(value="ret")  String ret,//检索内容
			@RequestParam(value="pagNum")  int pagNum
			) {
		ResponseJsonUtils.json(response,nrService.retrievalProgram(ret,pagNum));
	}

}





