package com.xunmilingnan.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xunmilingnan.entity.AdvCategory;
import com.xunmilingnan.entity.Album;
import com.xunmilingnan.entity.Follow;
import com.xunmilingnan.entity.Program;
import com.xunmilingnan.entity.User;
import com.xunmilingnan.service.FollowService;
import com.xunmilingnan.service.RadioService;
import com.xunmilingnan.service.UserService;
import com.xunmilingnan.statics.Uploads;
import com.xunmilingnan.statics.ResponseJsonUtils;

@Controller//有关电台的所以controller
@Repository
@RequestMapping("/radio")
public class RadioController {
//	@PostMapping("/getTest")
//	@GetMapping("/getAllTest")
//	@RequestMapping(value="/add")
//	http://localhost:8080/XunMilingnan/radio
//	@GetMapping("/getAllTest")
//	public void getAllTest( HttpServletResponse response) {
//		ResponseJsonUtils.json(response, allService.getAll());
//	}
//	,method={RequestMethod.POST,RequestMethod.GET} 可以使用post或get方式  
	@Resource
	private  RadioService radioService;
	@Resource
	private UserService userService;
	@Resource
	private FollowService followService;
	
	/*节目的操作-------------------------------------------------------------------*/
	//	1. 在一个专辑下添加（创建）一个节目（用户）
	@PostMapping("/addprogram")//添加一个节目Program
	public void addProgram(HttpServletResponse response,
			@RequestParam(value="time") String time,//发布时间
			@RequestParam(value="longOfTime") String longOfTime,//节目时常
			@RequestParam(value="name") String  name,//节目名称
			@RequestParam(value="advCat") int advCatid,//对应电台分类的id
			@RequestParam(value="album") int albumId,//对应专辑
			@RequestParam(value="FMName") String FMName,//节目资源的文件名称，如236.mp4
			@RequestParam(value="sortNumber") int sortNumber//排序编号
			) {
		//获取对应专辑
		Album album = radioService.getAlbum(albumId);
		//创建一个节目对象
		Program pro = new Program(time,longOfTime,name,advCatid,album,FMName,sortNumber);
		ResponseJsonUtils.json(response, radioService.addProgram(pro));
	}
	//	2. 在一个专辑下删除一个节目（用户）
	@PostMapping("/delprogram")
	public void deletProgram(HttpServletResponse response,
			@RequestParam(value="proId")  int  proId) {
		ResponseJsonUtils.json(response, radioService.deletProgram(proId));
	}
	//	3. 在一个专辑下更新一个节目（用户）：更改名称等
	@PostMapping("/updprogram")
	public void updProgram(HttpServletResponse response,
			@RequestParam(value="id")  int id,
			@RequestParam(value="time") String time,//发布时间
			@RequestParam(value="longOfTime") String longOfTime,//节目时常
			@RequestParam(value="name") String  name,//节目名称
			@RequestParam(value="advCat") int advCatid,//对应电台分类的id
			@RequestParam(value="album") int albumId,//对应专辑
			@RequestParam(value="FMName") String FMName,//节目资源的文件名称，如236.mp4
			@RequestParam(value="sortNumber") int sortNumber//排序编号
			) {
		//获取对应专辑
		Album album = radioService.getAlbum(albumId);
		//创建一个节目对象
		Program pro = new Program(time,longOfTime,name,advCatid,album,FMName,sortNumber);
		//输入此节目对象应有的id
		pro.setId(id);
		ResponseJsonUtils.json(response, radioService.updProgram(pro));
	}
	//	4. 收藏某个节目(依据节目id和用户id)到用户收藏(用户)
	@PostMapping("/folprogram")
	public void followProgram(HttpServletResponse response,
			@RequestParam(value="pId")  int pId,
			@RequestParam(value="uId")  int uId
			) {
		//获取User;
		User user = userService.getUserById(uId);
		//收藏节目加一
		radioService.followProgram(pId);
		//创建一个Follow
		Follow fo = new Follow(user,1,pId);
		//将Follow存入
		ResponseJsonUtils.json(response, followService.addFollow(fo));
	}
	//	5. 获取某个用户(依据用户id)收藏的所有节目(用户)
	@PostMapping("/getfolprograms")
	public void getFollowPrograms(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="pagNum")  int pagNum,
			@RequestParam(value="limit")  int limit
			) {
		ResponseJsonUtils.json(response, followService.getFollowPrograms(uId,pagNum,limit));
	}
	//	6. 获取某专辑下所有节目（按ID/发布时间倒序）
	@PostMapping("/getproinalbum")
	public void getProgramsInAlbum(HttpServletResponse response,
			@RequestParam(value="aId")  int aId,
			@RequestParam(value="pagNum")  int pagNum,
			@RequestParam(value="limit")  int limit
			) {
		ResponseJsonUtils.json(response, radioService.getProgramsInAlbum(aId,pagNum,limit));
	}
	//	7. 获取某个电台分类下所以节目（按ID/发布时间倒序）
	@PostMapping("/getproinradcat")
	public void getProgramsInRadCat(HttpServletResponse response,
			@RequestParam(value="rId")  int rId,
			@RequestParam(value="pagNum")  int pagNum,//页码,
			@RequestParam(value="limit")  int limit
			) {
		ResponseJsonUtils.json(response, radioService.getProgramsInRadCat(rId,pagNum,limit));
	}
	//	8. 听取某个节目
	@PostMapping("/browsepro")
	public void browseProgram(HttpServletResponse response,
			@RequestParam(value="pId")  int pId
			) {
		ResponseJsonUtils.json(response, radioService.browseProgram(pId));
	}
	//	9. 点赞某个节目
	@PostMapping("/praisepro")
	public void praiseProgram(HttpServletResponse response,
			@RequestParam(value="pId")  int pId
			) {
		ResponseJsonUtils.json(response, radioService.praiseProgram(pId));
	}
	/*专辑的操作-------------------------------------------------------------------*/
	//	1. 在一个电台分类下添加（创建）一个专辑（用户）/addalbum	
	@PostMapping("/addalbum")//添加一个专辑
	public void addAlbum(HttpServletResponse response,
			@RequestParam(value="rcId") int rcId,
			@RequestParam(value="relTime") String relTime,
			@RequestParam(value="uId") int uId,
			@RequestParam(value="name") String name,
			@RequestParam(value="img")  String img
			) {
		//获取AdvCategory、User
		AdvCategory rad = radioService.getRadCat(rcId);
		User use = userService.getUserById(uId);
		//创建一个电台专辑
		Album album = new Album(rad,use,name,relTime,img);
		//存入并返回
		ResponseJsonUtils.json(response, radioService.addAlbum(album));
	}
	//	2. 在一个电台分类下删除一个专辑（用户）/delealbum
	@PostMapping("/delalbum")//删除一个专辑
	public void delAlbum(HttpServletResponse response,
			@RequestParam(value="aid") int aid
			) {
		//删除并返回
		System.out.println("get controller");
		ResponseJsonUtils.json(response, radioService.delAlbum(aid));
	}
	//	3. 在一个电台分类下更新一个专辑（用户）：更换封面、更改名字等操作/updalbum
	@PostMapping("/updalbum")//更新一个专辑
	public void updAlbum(HttpServletResponse response,
			@RequestParam(value="alid") int alid,
			@RequestParam(value="relTime") String relTime,
			@RequestParam(value="rcId") int rcId,
			@RequestParam(value="uId") int uId,
			@RequestParam(value="name") String name,
			@RequestParam(value="img")  String img
			) {
		//获取AdvCategory、User
		AdvCategory rad = radioService.getRadCat(rcId);
		User use = userService.getUserById(uId);
		//创建一个电台专辑
		Album album = new Album(rad,use,name,relTime,img);
		album.setId(alid);
		//存入并返回
		ResponseJsonUtils.json(response, radioService.updAlbum(album));
	}
	//	4. 获取一个电台分类下的所有专辑（按ID/创建时间倒序）/getallalbum
	@PostMapping("/getallalbum")//获取专辑列表
	public void getAllAlbum(HttpServletResponse response,
			@RequestParam(value="acId") int acid,
			@RequestParam(value="pagNum") int pagNum,
			@RequestParam(value="limit")  int limit) {//pagNum 欲获取的分页
		ResponseJsonUtils.json(response,radioService.getAlbumList(acid,pagNum,limit));
	}
	
	
	
	/*电台分类的操作-------------------------------------------------------------------*/
	//	1. 添加一个电台分类/addradcat
	@PostMapping("/addradcat")
	public void addRadioCategory( HttpServletResponse response,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber") int sortNumber,//排序编号	
			@RequestParam(value="img") String img//上传来的图像
			) {
		//创建一个“电台分类”的AdvCategory
		AdvCategory advCat = new AdvCategory(name,sortNumber,img);
		//将这个AdvCategory 存入数据库  并用json返回
		ResponseJsonUtils.json(response, radioService.addRadCat(advCat));
	}
	//	2. 删除一个电台分类/delradcat
	@PostMapping("/delradcat")
	public void deleteRadioCategory(HttpServletResponse response,
			@RequestParam(value="rcId") int rcId
			) {
		ResponseJsonUtils.json(response,radioService.deleteRadCat(rcId));
	}
	//	3. 更新一个电台分类/updradcat
	@PostMapping("/updradcat")
	public void updateRadioCategory( HttpServletResponse response,
			@RequestParam(value="rcId") int rcId,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber") int sortNumber,//排序编号	
			@RequestParam(value="img") String img//上传来的图像
			) {
		//创建一个“电台分类”的AdvCategory
		AdvCategory advCat = new AdvCategory(name,sortNumber,img);
		//将id 输入
		advCat.setId(rcId);
		//更新AdvCategory  并返回 
		ResponseJsonUtils.json(response, radioService.updRadCat(advCat));
	}
	//	4. 获取所有电台分类(带有分页和筛选) /getalladcat
	@PostMapping("/getalladcat")//带有分页功能
	public void getAllRadioCategory(HttpServletResponse response,
			@RequestParam(value="pagNum")int pagNum,
			@RequestParam(value="limit")  int limit) {//pagNum 欲获取的分页
		ResponseJsonUtils.json(response, radioService.getAdvCatList(pagNum,limit));
	}
	
	
}
