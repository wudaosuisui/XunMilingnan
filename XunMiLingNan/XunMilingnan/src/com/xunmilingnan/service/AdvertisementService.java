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
@Repository//有关电台的服务
public class AdvertisementService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource//电台分类（也是广告分类）Dao
	private AdvCategoryDao advCatDao;
	
	//	1. 添加广告分类
	
	//	2. 更新广告分类
	//	3. 删除广告分类
	//	4. 获取所有广告分类(带有分页和筛选)
	//	5. 添加一条广告
	//	6. 更新一条广告
	//	7. 删除一条广告
	//	8. 获取所有广告(带有分页和筛选)
	
	
	
	//获取一个广告分类   by  id
	public AdvCategory getAdvCat(int id ) {
		Session session = sessionFactory.openSession();
		AdvCategory adv = advCatDao.getById(id);
		session.close();
		return adv;
	}
	//获取广告分类列表
	public List<AdvCategory> getList(){
		Session session = sessionFactory.openSession();
		List<AdvCategory> advCatList = advCatDao.getAdvList();
		session.close();
		return advCatList;
	}
	//添加一个广告分类
	public AdvCategory addRadCat(AdvCategory advCat) {
		Session session = sessionFactory.openSession();
		advCatDao.save(advCat);
		session.close();
		return advCat;
	}
	//删除一个广告分类
	public void deleteRadCat(int id ) {
		Session session = sessionFactory.openSession();
		advCatDao.deleteById(id);
		session.close();
	}
	//更新一个广告分类
	public AdvCategory updRadCat(AdvCategory adv) {
		Session session = sessionFactory.openSession();
		advCatDao.save(adv);
		session.close();
		return adv;
	}
}






