package com.xunmilingnan.service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.xunmilingnan.dao.AdvCategoryDao;
import com.xunmilingnan.dao.AlbumDao;
import com.xunmilingnan.dao.ProgramDao;
import com.xunmilingnan.entity.AdvCategory;
import com.xunmilingnan.entity.Album;
import com.xunmilingnan.entity.Program;
import com.xunmilingnan.statics.Page;
import com.xunmilingnan.statics.Result;
import com.xunmilingnan.statics.Uploads;

@Service
@Repository//有关电台的服务
public class RadioService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource//电台分类（也是广告分类）Dao
	private AdvCategoryDao advCatDao;
	@Resource//专辑！
	private AlbumDao albumDao;
	@Resource//节目
	private ProgramDao proDao;
	
	
	/*节目的操作-------------------------------------------------------------------*/
	//	1. 在一个专辑下添加（创建）一个节目（用户）
	public HashMap<String, Object> addProgram(Program pro) {
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		proDao.save(pro);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("program",pro);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. 在一个专辑下删除一个节目（用户）
	public HashMap<String, Object> deletProgram(int proId) {
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		proDao.deleteById(proId);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. 在一个专辑下更新一个节目（用户）：更改名称
	public HashMap<String, Object> updProgram(Program pro) {
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		proDao.upDate(pro);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("Program",pro);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. (位于 follow Service)收藏某个节目(依据节目id和用户id)到用户收藏(用户)
	public void followProgram(int id ) {
		this.proAddOne(id, 2);
	}
	//	5. (位于 follow Service)获取某个用户(依据用户id)收藏的所有节目(用户)
	//	6. 获取某专辑下所有节目（按ID/发布时间倒序）
	public HashMap<String, Object> getProgramsInAlbum(int aId,int pagNum,int limit){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		//获取专辑里的节目列表，并存入page
		Page page = new Page(limit);
		page.setList(proDao.getListByAlbumId(aId));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("Programs",page.getPartList());
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	7. 获取某个电台分类下所以节目（按ID/发布时间倒序）
	public HashMap<String, Object> getProgramsInRadCat(int rId,int pagNum,int limit){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		//获取专辑里的节目列表，并存入page
		Page page = new Page(limit);
		page.setList(proDao.getListByAdvCatId(rId));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("Programs",page.getPartList());
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	8. 听取某个节目
	public HashMap<String, Object> browseProgram(int id ) {
		return this.proAddOne(id, 1);
	}
	//	9. 点赞某个节目
	public HashMap<String, Object> praiseProgram(int id ) {
		return this.proAddOne(id, 0);
	}
	//加一操作
	public HashMap<String, Object> proAddOne(int id ,int type) {
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Program pro = proDao.getById(id);
		if(type ==0) {
			pro.setPraise(pro.getPraise()+1);
		}else if(type==1) {
			pro.setBrowse(pro.getBrowse()+1);
		}else if(type==2) {
			pro.setFollow(pro.getFollow()+1);
		}
		this.updProgram(pro);
		Map message =new HashMap<String, Object>(1){{
			put("Program",pro);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//通过id 获取节目对象
	public Program getProgram(int id ) {
		Session session = sessionFactory.openSession();
		Program  pro =proDao.getById(id);
		session.close();
		return pro;
	}
	//更新节目对象
	public Program getProgram(Program pro ) {
		Session session = sessionFactory.openSession();
		proDao.upDate(pro);
		session.close();
		return pro;
	}
	
	/*专辑的操作-------------------------------------------------------------------*/
	//	1. 在一个电台分类下添加（创建）一个专辑（用户）/addalbum
	public HashMap<String, Object> addAlbum(Album album){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		albumDao.save(album);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("album",album);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. 在一个电台分类下删除一个专辑（用户）/delealbum
	public HashMap<String, Object> delAlbum(int id ) {
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		//删除此专辑下的节目
		List<Program> proList = proDao.getListByAlbumId(id);
		proDao.deleteList(proList);
		//删除此专辑
		albumDao.deleteById(id);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. 在一个电台分类下更新一个专辑（用户）：更换封面、更改名字等操作/updalbum
	public HashMap<String, Object> updAlbum(Album album){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		albumDao.upDate(album);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("album",album);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. 获取一个电台分类下的所有专辑（按ID/创建时间倒序）/getallalbum
	public HashMap<String, Object>  getAlbumList(int advCatid,int pageNum,int limit) {
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		List<Album> albumList = albumDao.getList(advCatid);
		session.close();
		Page page = new Page(limit);
		if(albumList==null) {
			statusCode = "100005";
			desc = "数据库列表获取失败";
		}else {
			page.setList(albumList);
			page.setCurrentPageNum(pageNum);
		}
		Map message =new HashMap<String, Object>(1)
			{{put("albumList",page.getPartList());
			}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//获取一个专辑
	public Album getAlbum(int id ) {
		Session session = sessionFactory.openSession();
		Album alb = albumDao.getById(id);
		session.close();
		return alb;
	}
	/*电台分类的操作-------------------------------------------------------------------*/
	//获取一个电台分类 by id 
	public AdvCategory getRadCat(int id ) {
		Session session = sessionFactory.openSession();
		AdvCategory adv = advCatDao.getById(id);
		session.close();
		return adv;
	}
	//获取所有电台分类
	public HashMap<String, Object> getAdvCatList(int pageNum,int limit){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		List<AdvCategory> advCatList = advCatDao.getRadioList();
		session.close();
		Page page = new Page(limit);
		if(advCatList==null) {
			statusCode = "100005";
			desc = "数据库列表获取失败";
		}else {
			page.setList(advCatList);
			page.setCurrentPageNum(pageNum);
		}
		Map message =new HashMap<String, Object>(2)
			{{put("advCatList",page.getPartList());
			  put("imgUrl","F:\\Documents\\GitHub\\XunMilingnan\\XunMiLingNan\\XunMilingnan\\WebContent\\img\\");
			}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//添加一个电台分类
	public HashMap<String, Object> addRadCat(AdvCategory advCat) {
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行代码
		Session session = sessionFactory.openSession();
		advCatDao.save(advCat);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{put("advCat",advCat);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//删除一个电台分类
	public HashMap deleteRadCat(int id ) {
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行内容
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
	//更新一个电台分类
	public HashMap updRadCat(AdvCategory adv) {
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行内容
		Session session = sessionFactory.openSession();
		advCatDao.upDate(adv);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("AdvCategory",adv);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
}
