package com.xunmilingnan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.xunmilingnan.dao.ArticleDao;
import com.xunmilingnan.dao.FollowDao;
import com.xunmilingnan.dao.ProgramDao;
import com.xunmilingnan.entity.Follow;
import com.xunmilingnan.entity.Program;
import com.xunmilingnan.statics.Page;
import com.xunmilingnan.statics.Result;

@Service
@Repository//�й�"��ע"�ķ���
public class FollowService {
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private FollowDao followDao;
	@Resource
	private ProgramDao programDao;
	@Resource
	private ArticleDao arDao;
	private Page page = new Page(10);
	
	//	4. �ղ�ĳ����Ŀ(���ݽ�Ŀid���û�id)���û��ղ�(�û�)	
	public HashMap<String, Object> addFollow(Follow fo){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		followDao.save(fo);
		System.out.println("save fo ");
		session.close();
		Map message =new HashMap<String, Object>(2)
		{{
			put("follow",fo);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
	//	5. ��ȡĳ���û�(�����û�id)�ղص���������(�û�)
	public HashMap<String, Object> getFollowArt(int uId,int pagNum){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		//��ȡfollw List
		List<Follow> foList = followDao.getListByUT(uId, 0);
		//��ȡ program  List ������page
		page.setList( arDao.getListByFollowList(foList));
		page.setCurrentPageNum(pagNum);
		//��ȡ�ֲ��б�
		List<Program> arList = page.getPartList();
		session.close();
		Map message =new HashMap<String, Object>(2)
		{{
			put("followArticle",arList);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
	//	5. ��ȡĳ���û�(�����û�id)�ղص����н�Ŀ(�û�)
	public HashMap<String, Object> getFollowPrograms(int uId ,int pagNum){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		//��ȡfollw List
		List<Follow> foList = followDao.getListByUT(uId, 1);
		//��ȡ program  List ������page
		page.setList( programDao.getListByFollowList(foList));
		page.setCurrentPageNum(pagNum);
		//��ȡ�ֲ��б�
		List<Program> proList = page.getPartList();
		session.close();
		Map message =new HashMap<String, Object>(2)
		{{
			put("followPrograms",proList);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
}








