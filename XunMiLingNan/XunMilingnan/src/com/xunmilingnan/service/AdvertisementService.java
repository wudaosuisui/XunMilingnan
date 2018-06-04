package com.xunmilingnan.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.xunmilingnan.dao.AdvCategoryDao;
import com.xunmilingnan.entity.AdvCategory;

@Service
@Repository//�йص�̨�ķ���
public class AdvertisementService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource//��̨���ࣨҲ�ǹ����ࣩDao
	private AdvCategoryDao advCatDao;
	
	//	1. ��ӹ�����
	
	//	2. ���¹�����
	//	3. ɾ��������
	//	4. ��ȡ���й�����(���з�ҳ��ɸѡ)
	//	5. ���һ�����
	//	6. ����һ�����
	//	7. ɾ��һ�����
	//	8. ��ȡ���й��(���з�ҳ��ɸѡ)
	
	
	
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






