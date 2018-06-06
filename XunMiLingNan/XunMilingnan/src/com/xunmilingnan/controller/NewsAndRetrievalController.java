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

@Controller//������Ϣ������controller
@Repository
//@RequestMapping("/news")
public class NewsAndRetrievalController {
	@Resource
	public NewsAndRetrievalService nrService;
	/*"��Ϣ"�Ĳ���-------------------------------------------------------------------*/
	//	1. ��ȡĳ�û���������Ϣ���Ѷ�δ����������
	@RequestMapping("news/getnews")
	public void getNewsByUid(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="pagNum")  int pagNum
			) {
		ResponseJsonUtils.json(response,nrService.getNewsByUid(uId,pagNum));
	}
	//	2. ��ȡ���鿴�����û���ȡĳ��Ϣ�󣬸�����Ϣ��״̬Ϊ�Ѷ���
	@RequestMapping("news/rednews")
	public void redNews(HttpServletResponse response,
			@RequestParam(value="nId")  int nId
			) {
		ResponseJsonUtils.json(response,nrService.redNews(nId));
	}
	//	3. ĳ�û�ɾ��ĳ����Ϣ
	@RequestMapping("news/delnews")
	public void delNews(HttpServletResponse response,
			@RequestParam(value="nId")  int nId
			) {
		ResponseJsonUtils.json(response,nrService.delNews(nId));
	}
	//	4. ĳ�û�ɾ��ĳ����������Ϣ
	@RequestMapping("news/delnewslist")
	public void delNewsList(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="gro")  int gro
			) {
		ResponseJsonUtils.json(response,nrService.delNewsList(uId,gro));
	}
	
	
	/*"����"�Ĳ���-------------------------------------------------------------------*/
	//�򵥼���
	@RequestMapping("retr/simret")
	public void simpleRetrieval(HttpServletResponse response,
			@RequestParam(value="ret")  String ret//��������
			) {
		ResponseJsonUtils.json(response,nrService.simpleRetrieval(ret));
	}
	//user
	@RequestMapping("retr/user")
	public void retrievalUser(HttpServletResponse response,
			@RequestParam(value="ret")  String ret,//��������
			@RequestParam(value="pagNum")  int pagNum
			) {
		ResponseJsonUtils.json(response,nrService.retrievalUser(ret,pagNum));
	}
	//SpecuakTop
	@RequestMapping("retr/specuaktop")
	public void retrievalSpecuakTop(HttpServletResponse response,
			@RequestParam(value="ret")  String ret,//��������
			@RequestParam(value="pagNum")  int pagNum
			) {
		ResponseJsonUtils.json(response,nrService.retrievalSpecuakTop(ret,pagNum));
	}
	//Article
	@RequestMapping("retr/article")
	public void retrievalArticle(HttpServletResponse response,
			@RequestParam(value="ret")  String ret,//��������
			@RequestParam(value="pagNum")  int pagNum
			) {
		ResponseJsonUtils.json(response,nrService.retrievalArticle(ret,pagNum));
	}
	//Album
	@RequestMapping("retr/album")
	public void retrievalAlbum(HttpServletResponse response,
			@RequestParam(value="ret")  String ret,//��������
			@RequestParam(value="pagNum")  int pagNum
			) {
	ResponseJsonUtils.json(response,nrService.retrievalAlbum(ret,pagNum));
	}
	//Program
	@RequestMapping("retr/program")
	public void retrievalProgram(HttpServletResponse response,
			@RequestParam(value="ret")  String ret,//��������
			@RequestParam(value="pagNum")  int pagNum
			) {
		ResponseJsonUtils.json(response,nrService.retrievalProgram(ret,pagNum));
	}

}





