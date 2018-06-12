package com.xunmilingnan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.xunmilingnan.dao.AdvCategoryDao;
import com.xunmilingnan.dao.AdvertisementDao;
import com.xunmilingnan.entity.AdvCategory;
import com.xunmilingnan.entity.Advertisement;
import com.xunmilingnan.statics.Page;
import com.xunmilingnan.statics.Result;

@Service
@Repository//�йص�̨�ķ���
public class AdvertisementService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource//��̨���ࣨҲ�ǹ����ࣩDao
	private AdvCategoryDao advCatDao;
	@Resource//��̨���ࣨҲ�ǹ����ࣩDao
	private AdvertisementDao advDao;
	
	//	1. ��ӹ�����
	public HashMap<String, Object> addAdvCat(AdvCategory advCat){
		System.out.println("get service ");
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		advCatDao.save(advCat);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("advCategory",advCat);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. ���¹�����
	public HashMap<String, Object> updAdvCat(AdvCategory advCat){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		advCatDao.upDate(advCat);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("advCategory",advCat);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. ɾ��������
	public HashMap<String, Object> delAdvCat(int id){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		advCatDao.deleteById(id);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. ��ȡ���й�����(���з�ҳ��ɸѡ)
	public HashMap<String, Object> getAllAdvCat(int pagNum,int limit){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		Page page = new Page(limit);
		page.setList(advCatDao.getAdvList());
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("advCatList",page.getPartList());
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	5. ���һ�����
	public HashMap<String, Object> addAdvertisement(Advertisement adv){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		advDao.save(adv);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("advertisement",adv);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	6. ����һ�����
	public HashMap<String, Object> updAdvertisement(Advertisement adv){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		advDao.upDate(adv);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("advertisement",adv);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	7. ɾ��һ�����
	public HashMap<String, Object> deleteAdvertisement(int id){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		advDao.deleteById(id);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	8. ��ȡ���й��(���з�ҳ��ɸѡ)
	public HashMap<String, Object> getAdvList(int pagNum,int limit){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		Page page = new Page(limit);
		page.setList(advDao.getList());
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("advList",page.getPartList());
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//  9. ��ȡĳ���������µ����й�棨���з�ҳ��ɸѡ��
	public HashMap<String, Object> getAdvListInAdvCat(int acId,int pagNum,int limit){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		Page page = new Page(limit);
		page.setList(advDao.getListByAdvCatId(acId));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("advList",page.getPartList());
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
	
	
	//��ȡһ��������   by  id
	public AdvCategory getAdvCat(int id ) {
		Session session = sessionFactory.openSession();
		AdvCategory adv = advCatDao.getById(id);
		session.close();
		return adv;
	}
	//��ȡ�������б�
	public List<AdvCategory> getList(){
		Session session = sessionFactory.openSession();
		List<AdvCategory> advCatList = advCatDao.getAdvList();
		session.close();
		return advCatList;
	}
	//���һ��������
	public AdvCategory addRadCat(AdvCategory advCat) {
		Session session = sessionFactory.openSession();
		advCatDao.save(advCat);
		session.close();
		return advCat;
	}
	//ɾ��һ��������
	public void deleteRadCat(int id ) {
		Session session = sessionFactory.openSession();
		advCatDao.deleteById(id);
		session.close();
	}
	//����һ��������
	public AdvCategory updRadCat(AdvCategory adv) {
		Session session = sessionFactory.openSession();
		advCatDao.save(adv);
		session.close();
		return adv;
	}
}






