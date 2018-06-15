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

@Controller//����ר�⣨ר���������Ҳ������������controller
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
	
	/*"����"�Ĳ���-------------------------------------------------------------------*/
	//	1. ��һ��ר���·���һƪ���£��û�����������˹�ע�����Թ�ע�����˷���һ����Ϣ��
	@PostMapping("/addar")
	public void addArticle(HttpServletResponse response,
			@RequestParam(value="stId") int stId,
			@RequestParam(value="userId") int userId,
			@RequestParam(value="publishTime") String publishTime,
			@RequestParam(value="title") String title,
			@RequestParam(value="text") String text,
			@RequestParam(value="sortNumber",required=false, defaultValue="0") int sortNumber
			) {
		//��ȡר��
		SpecialTopic st = stService.getSpecialTopicById(stId);
		//��ȡuser
		User user = usService.getUserById(userId);
		//�������¶���
		Article art = new Article(st,user,publishTime,title,text,sortNumber);
		//����
		HashMap<String, Object> map = stService.addArticle(art);
//		Map message = (Map)map.get("message");
		Article ar = (Article)((Map)map.get("message")).get("message");
		//����ע�����ߵ��û���һ����Ϣ
		neService.sendNewsForArticle(user,ar.getId());
		//���벢����
		ResponseJsonUtils.json(response,map);
	}
	//	2. ��һ��ר���¸���һƪ���£��û����ṩ�û��޸����µĹ���
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
		//��ȡר��
		SpecialTopic st = stService.getSpecialTopicById(stId);
		//��ȡuser
		User user = usService.getUserById(userId);
		//�������¶���
		Article art = new Article(st,user,publishTime,title,text,sortNumber);
		//����id
		art.setId(arId);
		//���벢����
		ResponseJsonUtils.json(response, stService.updArticle(art));
	}
	//	3. ��һ��ר����ɾ��һƪ���£��û����ṩ�û�ɾ�����µĹ���
	@PostMapping("/delar")
	public void delArticle(HttpServletResponse response,
			@RequestParam(value="arId") int arId
			) {
		//ɾ��
		ResponseJsonUtils.json(response, stService.delArticle(arId));
	}
	//	4. �鿴��������(���з�ҳ��ɸѡ)
	@PostMapping("/arlist")
	public void getArticleList(HttpServletResponse response,
			@RequestParam(value="pagNum") int pagNum,
			@RequestParam(value="limit") int limit
			) {
		//ɾ��
		ResponseJsonUtils.json(response, stService.getArticleList(pagNum,limit));
	}
	//	5. �鿴ĳһר���µ���������
	@PostMapping("/arlistinst")
	public void getArticleListInSt(HttpServletResponse response,
			@RequestParam(value="stId") int stId,
			@RequestParam(value="pagNum") int pagNum,
			@RequestParam(value="limit") int limit
			) {
		//ɾ��
		ResponseJsonUtils.json(response, stService.getArticleListInSt(stId,pagNum,limit));
	}
	//	6. ��ĳƪ���½������ۣ��û��������ͨ���󣬶��������߷���һ����Ϣ��
	@PostMapping("/comar")
	public void commentArtic(HttpServletResponse response,
			@RequestParam(value="arId") int arId,//���µ�id
			@RequestParam(value="cusId") int cusId,//�����ߵ�id
			@RequestParam(value="time") String time,//����ʱ��
			@RequestParam(value="text") String text//��������
			) {
		//��ȡ���۵�user
		User user = usService.getUserById(cusId);
		//����һ�������ۡ�
		Comment com = new Comment(user,time,text,0,arId);
		//���벢����
		ResponseJsonUtils.json(response, stService.commentArtic(com));
	}
	//	7. ��ĳ�����۽��лظ����ɹ���Ա�ظ����ظ�������۵ķ����߷���һ����Ϣ��
	@PostMapping("/repcom")
	public void replayComment(HttpServletResponse response,
			@RequestParam(value="adId") int adId,//����Ա��id
			@RequestParam(value="comId") int comId,//���ظ��ġ����ۡ�
			@RequestParam(value="time") String time,//����ʱ��
			@RequestParam(value="text") String text//�ظ�����
			) {
		//admin
		User ad = usService.getUserById(adId);
		//comment
		Comment com = stService.getCommentById(comId);
		//����һ�������ۡ�
		Comment rep = new Comment(com,ad,time,text,com.getType(),com.getWorks());
		//���벢����
		ResponseJsonUtils.json(response, stService.replayComment(com));
	}
	//	8. �ղ�ĳƪ���£��ղ���+1�����û���
	@PostMapping("/folart")
	public void folArticle(HttpServletResponse response,
			@RequestParam(value="arId") int arId,//���µ�id
			@RequestParam(value="usId") int usId//�û���id
			) {
		//��ȡUser;
		User user = usService.getUserById(usId);
		//�ղ����¼�һ
		stService.addForward(arId);
		//����һ��Follow
		Follow fo = new Follow(user,0,arId);
		//��Follow����
		ResponseJsonUtils.json(response, foService.addFollow(fo));
	}
	//	9. ��ȡĳ�û��ղص�����
	@PostMapping("/getfolarts")
	public void getFollowArt(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="pagNum",required=false, defaultValue="0")  int pagNum,
			@RequestParam(value="limit")  int limit
			) {
		ResponseJsonUtils.json(response, foService.getFollowArt(uId,pagNum,limit));
	}
	//	10. ����������+1�����û���
	@PostMapping("/browseart")
	public void browseArticle(HttpServletResponse response,
			@RequestParam(value="aId")  int aId
			) {
		ResponseJsonUtils.json(response,stService.addBrowse(aId));
	}
	//	11. ���ޣ�������+1�����û���
	@PostMapping("/praiseart")
	public void praiseArticle(HttpServletResponse response,
			@RequestParam(value="aId")  int aId
			) {
		ResponseJsonUtils.json(response,stService.addPraise(aId));
	}
	//	12. ��ȡĳ���û���д����������
	@PostMapping("/userslistbyus")
	public void getArticleListByUser(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="pagNum")  int pagNum,
			@RequestParam(value="limit") int limit//ҳ��
			) {
		ResponseJsonUtils.json(response,stService.getArticleListByUser(uId,pagNum,limit));
	}
	//	14.	���ͨ��ĳ�����ۣ���6��   
	@PostMapping("/audcom")//examine
	public void auditingComment(HttpServletResponse response,
			@RequestParam(value="adId")  int adId,
			@RequestParam(value="coId")  int coId
			) {
		ResponseJsonUtils.json(response,stService.auditingComment(adId,coId));
	}
	/*"ר��"�Ĳ���-------------------------------------------------------------------*/
	//	1. ����һ��ר��
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
		//��ȡר�����
		AdvCategory stCat = stService.getStCat(stCatId);
		//����һ����ר�⡱
		SpecialTopic st = new SpecialTopic(stCat,title,startTime,endTime,article,img,showTime,isHot,sortNumber);
		//���벢����
		ResponseJsonUtils.json(response, stService.addSpeTop(st));
	}
	//	2. ɾ��һ��ר��
	@PostMapping("/delst")
	public void delSpeTop(HttpServletResponse response,
			@RequestParam(value="stId") int stId) {
		//ɾ��������
		ResponseJsonUtils.json(response, stService.delSpeTop(stId));
	}
	//	3. ����һ��ר��
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
		//��ȡר�����
		AdvCategory stCat = stService.getStCat(stCatId);
		//����һ����ר�⡱
		SpecialTopic st = new SpecialTopic(stCat,title,startTime,endTime,article,img,showTime,isHot,sortNumber);
		//����id
		st.setId(stId);
		//���²�����
		ResponseJsonUtils.json(response, stService.updSpeTop(st));
	}
	//	4. ��ȡĳר������µ�����ר��(���з�ҳ��ɸѡ)
	@PostMapping("/stlistinstcat")
	public void stListInStCat(HttpServletResponse response,
			@RequestParam(value="stcId") int stcId,
			@RequestParam(value="pagNum") int pagNum,
			@RequestParam(value="limit") int limit) {
		//��ȡ������
		ResponseJsonUtils.json(response, stService.stListInStCat(stcId,pagNum,limit));
	}
	/*"ר�����"�Ĳ���-------------------------------------------------------------------*/
	//	1. ����һ��ר�����
	@PostMapping("/addstcat")
	public void addSpeTopCat(HttpServletResponse response,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber",required=false, defaultValue="0") int sortNumber,
			@RequestParam(value="img") String img 
	) {
		//����һ��ר�����
		AdvCategory stCat = new AdvCategory(name,2,sortNumber,img);
		//���벢����
		ResponseJsonUtils.json(response, stService.addSpeTopCat(stCat));
	}
	//	2. ɾ��һ��ר�����
	@PostMapping("/delstcat")
	public void delSpeTopCat(HttpServletResponse response,
			@RequestParam(value="scId") int scId
	) {
		//���벢����
		ResponseJsonUtils.json(response, stService.delSpeTopCat(scId));
	}
	//	3. ����һ��ר�����
	@PostMapping("/updstcat")
	public void updSpeTopCat(HttpServletResponse response,
			@RequestParam(value="id") int id,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber",required=false, defaultValue="0") int sortNumber,
			@RequestParam(value="img") String img 
	) {
		//����һ��ר�����
		AdvCategory stCat = new AdvCategory(name,2,sortNumber,img);
		//����id
		stCat.setId(id);
		//���벢����
		ResponseJsonUtils.json(response, stService.updSpeTopCat(stCat));
	}
	//	4. ��ȡȫ��ר�����(���з�ҳ��ɸѡ)
	@PostMapping("/stcatlist")
	public void speTopCatList(HttpServletResponse response,
			@RequestParam(value="pagNum") int pagNum,
			@RequestParam(value="limit") int limit
	) {
		System.out.println("get /stcatlist ");
		//ִ�в�����
		ResponseJsonUtils.json(response, stService.speTopCatList(pagNum,limit));
	}
}






