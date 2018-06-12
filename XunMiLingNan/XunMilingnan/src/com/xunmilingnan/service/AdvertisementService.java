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
@Repository//有关电台的服务
public class AdvertisementService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource//电台分类（也是广告分类）Dao
	private AdvCategoryDao advCatDao;
	@Resource//电台分类（也是广告分类）Dao
	private AdvertisementDao advDao;
	
	//	1. 添加广告分类
	public HashMap<String, Object> addAdvCat(AdvCategory advCat){
		System.out.println("get service ");
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		advCatDao.save(advCat);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("advCategory",advCat);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. 更新广告分类
	public HashMap<String, Object> updAdvCat(AdvCategory advCat){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		advCatDao.upDate(advCat);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("advCategory",advCat);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. 删除广告分类
	public HashMap<String, Object> delAdvCat(int id){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		advCatDao.deleteById(id);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. 获取所有广告分类(带有分页和筛选)
	public HashMap<String, Object> getAllAdvCat(int pagNum,int limit){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		Page page = new Page(limit);
		page.setList(advCatDao.getAdvList());
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("advCatList",page.getPartList());
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	5. 添加一条广告
	public HashMap<String, Object> addAdvertisement(Advertisement adv){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		advDao.save(adv);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("advertisement",adv);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	6. 更新一条广告
	public HashMap<String, Object> updAdvertisement(Advertisement adv){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		advDao.upDate(adv);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("advertisement",adv);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	7. 删除一条广告
	public HashMap<String, Object> deleteAdvertisement(int id){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		advDao.deleteById(id);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	8. 获取所有广告(带有分页和筛选)
	public HashMap<String, Object> getAdvList(int pagNum,int limit){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		Page page = new Page(limit);
		page.setList(advDao.getList());
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("advList",page.getPartList());
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//  9. 获取某个广告分类下的所有广告（带有分页和筛选）
	public HashMap<String, Object> getAdvListInAdvCat(int acId,int pagNum,int limit){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		Page page = new Page(limit);
		page.setList(advDao.getListByAdvCatId(acId));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("advList",page.getPartList());
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
	
	
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






