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
@Repository//有关电台的服务
public class RadioService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource//电台分类（也是广告分类）Dao
	private AdvCategoryDao advCatDao;
	
	//获取一个电台分类 by id 
	public AdvCategory getRadCat(int id ) {
		Session session = sessionFactory.openSession();
		AdvCategory adv = advCatDao.getById(id);
		session.close();
		return adv;
	}
	//获取所有电台分类
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
	//添加一个电台分类
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
	//删除一个电台分类
	public void deleteRadCat(int id ) {
		System.out.println("get service ");
		Session session = sessionFactory.openSession();
		advCatDao.deleteById(id);
		session.close();
		System.out.println("out service ");
	}
	//更新一个电台分类
	public AdvCategory updRadCat(AdvCategory adv) {
		Session session = sessionFactory.openSession();
		advCatDao.save(adv);
		session.close();
		return adv;
	}
	
}
