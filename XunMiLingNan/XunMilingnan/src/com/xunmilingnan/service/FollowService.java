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
@Repository//有关"关注"的服务
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
	
	//	4. 收藏某个节目(依据节目id和用户id)到用户收藏(用户)	
	public HashMap<String, Object> addFollow(Follow fo){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		followDao.save(fo);
		System.out.println("save fo ");
		session.close();
		Map message =new HashMap<String, Object>(2)
		{{
			put("follow",fo);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
	//	5. 获取某个用户(依据用户id)收藏的所有文章(用户)
	public HashMap<String, Object> getFollowArt(int uId,int pagNum){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		//获取follw List
		List<Follow> foList = followDao.getListByUT(uId, 0);
		//获取 program  List 并存入page
		page.setList( arDao.getListByFollowList(foList));
		page.setCurrentPageNum(pagNum);
		//获取局部列表
		List<Program> arList = page.getPartList();
		session.close();
		Map message =new HashMap<String, Object>(2)
		{{
			put("followArticle",arList);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
	//	5. 获取某个用户(依据用户id)收藏的所有节目(用户)
	public HashMap<String, Object> getFollowPrograms(int uId ,int pagNum){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		//获取follw List
		List<Follow> foList = followDao.getListByUT(uId, 1);
		//获取 program  List 并存入page
		page.setList( programDao.getListByFollowList(foList));
		page.setCurrentPageNum(pagNum);
		//获取局部列表
		List<Program> proList = page.getPartList();
		session.close();
		Map message =new HashMap<String, Object>(2)
		{{
			put("followPrograms",proList);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
}








