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
@Repository//�й�ר��ķ���
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
	
	
	/*"����"�Ĳ���-------------------------------------------------------------------*/
	//	1. ��һ��ר���·���һƪ���£��û�����������˹�ע�����Թ�ע�����˷���һ����Ϣ��
	public HashMap<String, Object> addArticle(Article art){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		artDao.save(art);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("Article",art);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. ��һ��ר���¸���һƪ���£��û����ṩ�û��޸����µĹ���
	public HashMap<String, Object> updArticle(Article art){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		artDao.upDate(art);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("Article",art);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. ��һ��ר����ɾ��һƪ���£��û����ṩ�û�ɾ�����µĹ���
	public HashMap<String, Object> delArticle(int id){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		artDao.deleteById(id);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. �鿴��������(���з�ҳ��ɸѡ)
	public HashMap<String, Object> getArticleList(int pagNum){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		page.setList(artDao.getList());
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("ArticleList",page.getPartList());
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	5. �鿴ĳһר���µ���������
	public HashMap<String, Object> getArticleListInSt(int stId,int pagNum){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		page.setList(artDao.getListInSt(stId));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("ArticleList",page.getPartList());
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	6. ��ĳƪ���½������ۣ��û��������ͨ���󣬶��������߷���һ����Ϣ��
	public HashMap<String, Object> commentArtic(Comment com){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		coDao.save(com);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("Comment",com);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	7. ��ĳ�����۽��лظ����ɹ���Ա�ظ����ظ�������۵ķ����߷���һ����Ϣ��
	public HashMap<String, Object> replayComment(Comment com){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		coDao.save(com);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("Comment",com);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	8. �ղ�ĳƪ���£��ղ���+1�����û���
	public void addForward(int id ){
		 this.artAddOne(id, 0);
	}
	//	9. ��ȡĳ�û��ղص�����
	//	10. ����������+1�����û���
	public HashMap<String, Object> addBrowse(int id ){
		return this.artAddOne(id, 1);
	}
	//	11. ���ޣ�������+1�����û���
	public HashMap<String, Object> addPraise(int id ){
		return this.artAddOne(id, 2);
	}
	//	12. ��ȡĳ���û���д����������
	public HashMap<String, Object> getArticleListByUser(int uId,int pagNum){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		page.setList(artDao.getListInU(uId));
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("ArticleList",page.getPartList());
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	13. ��ȡĳ���û��ղص���������
	
	//	14.	���ͨ��ĳ�����ۣ���6��      
	public HashMap<String, Object> auditingComment(int adId,int coId){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		//��ȡ����Ա
		User admin = usDao.getById(adId);
		//��ȡ��Ӧ��comment
		Comment com = coDao.getById(coId);
		com.setAuditing(true);
		com.setAdmin(admin);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("Comment",com);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
	//ͨ�� id ��ȡһ������
	public Comment getCommentById(int id ) {
		Session session = sessionFactory.openSession();
		Comment co =coDao.getById(id);
		session.close();
		return co;
	}
	//��ȡ���¶���
	public Article getArticleById(int id ) {
		Session session = sessionFactory.openSession();
		Article art = artDao.getById(id);
		session.close();
		return art;
	}
	//��һ����
	public HashMap<String, Object> artAddOne(int id ,int type) {
		//type  0  - forward�ղ� ; ;1 - browse ��� ;2 - praise ����
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
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
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	/*"ר��"�Ĳ���-------------------------------------------------------------------*/
	//	1. ����һ��ר��
	public HashMap<String, Object> addSpeTop(SpecialTopic st){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		stDao.save(st);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpecialTopic",st);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. ɾ��һ��ר��
	public HashMap<String, Object> delSpeTop(int id ){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		stDao.deleteById(id);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. ����һ��ר��
	public HashMap<String, Object> updSpeTop(SpecialTopic st){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		stDao.upDate(st);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpecialTopic",st);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. ��ȡĳר������µ�����ר��(���з�ҳ��ɸѡ)
	public HashMap<String, Object> stListInStCat(int stcId,int pagNum){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		page.setList(stDao.getListInStCat(stcId));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpecialTopicList",page.getPartList());
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//ͨ��id��ȡר��
	public SpecialTopic getSpecialTopicById(int id ) {
		Session session = sessionFactory.openSession();
		SpecialTopic st = stDao.getById(id);
		session.close();
		return st;
	}
	/*ר�����Ĳ���-------------------------------------------------------------------*/
	//	1. ����һ��ר�����
	public HashMap<String, Object> addSpeTopCat(AdvCategory stCat){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		acDao.save(stCat);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpeTopCategory",stCat);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. ɾ��һ��ר�����
	public HashMap<String, Object>	delSpeTopCat(int id ){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		acDao.deleteById(id);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. ����һ��ר�����
	public HashMap<String, Object> updSpeTopCat(AdvCategory stCat){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		acDao.upDate(stCat);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpeTopCategory",stCat);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. ��ȡȫ��ר�����(���з�ҳ��ɸѡ)
	public HashMap<String, Object> speTopCatList(int pagNum){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		page.setList(acDao.getObgList(2));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("SpeTopCategoryList",page.getPartList());
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//ͨ��id  ��ȡһ�� ר�����
	public  AdvCategory getStCat(int id ) {
		Session session = sessionFactory.openSession();
		AdvCategory stc = acDao.getById(id);
		session.close();
		return stc;
	}

	
}
