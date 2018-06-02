package com.xunmilingnan.service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.xunmilingnan.dao.AdvCategoryDao;
import com.xunmilingnan.entity.AdvCategory;
import com.xunmilingnan.statics.Imgs;

@Service
@Repository//�йص�̨�ķ���
public class RadioService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource//��̨���ࣨҲ�ǹ����ࣩDao
	private AdvCategoryDao advCatDao;
	
	//��ȡһ����̨���� by id 
	public AdvCategory getRadCat(int id ) {
		Session session = sessionFactory.openSession();
		AdvCategory adv = advCatDao.getById(id);
		session.close();
		return adv;
	}
	//��ȡ���е�̨����
	public HashMap<String, Object> getList(){
		Session session = sessionFactory.openSession();
		List<AdvCategory> advCatList = advCatDao.getRadioList();
		session.close();
		System.out.println("getList out");
		return new HashMap<String, Object>(2)
		{{put("advCatList",advCatList);
		  put("imgUrl","F:\\Documents\\GitHub\\XunMilingnan\\XunMiLingNan\\XunMilingnan\\WebContent\\img\\");
		}};
	}
	//���һ����̨����
	public HashMap<String, Object> addRadCat(AdvCategory advCat) {
		System.out.println("get service success");
		Session session = sessionFactory.openSession();
		advCatDao.save(advCat);
		session.close();
		System.out.println("out service success");
		return  new HashMap<String, Object>(2)
		{{put("advCat",advCat);
		  put("imgUrl","F:\\Documents\\GitHub\\XunMilingnan\\XunMiLingNan\\XunMilingnan\\WebContent\\img\\");
		}};
	}
	//ɾ��һ����̨����
	public void deleteRadCat(int id ) {
		System.out.println("get service ");
		Session session = sessionFactory.openSession();
		advCatDao.deleteById(id);
		session.close();
		System.out.println("out service ");
	}
	//����һ����̨����
	public AdvCategory updRadCat(AdvCategory adv) {
		Session session = sessionFactory.openSession();
		advCatDao.save(adv);
		session.close();
		return adv;
	}
	
}
