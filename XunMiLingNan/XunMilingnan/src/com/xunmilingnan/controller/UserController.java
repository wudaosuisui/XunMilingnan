package com.xunmilingnan.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xunmilingnan.entity.Group;
import com.xunmilingnan.entity.User;
import com.xunmilingnan.service.UserService;
import com.xunmilingnan.statics.ResponseJsonUtils;

@Controller//关于消息的所有controller
@Repository
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService usService;
	//count计数
	//登录
	@RequestMapping("/login")
	private void logIn(HttpServletResponse response,
			@RequestParam(value="code")  String code) {
		ResponseJsonUtils.json(response, usService.login(code));
	}
	//注册
	@RequestMapping("/register")
	private void register(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="avatarUrl")  String avatarUrl,
			@RequestParam(value="city")  String city,
			@RequestParam(value="country")  String country,
			@RequestParam(value="language")  String language,
			@RequestParam(value="nickName")  String nickName,
			@RequestParam(value="province")  String province
			) {
		User user = new User(uId,avatarUrl,city,country,language,nickName,province);
		ResponseJsonUtils.json(response, usService.updateUser(user));
	}
	//	1. 查看个人主页
	@RequestMapping("/homepage")
	private void homePage(HttpServletResponse response,
			@RequestParam(value="uId")  int uId) {
		ResponseJsonUtils.json(response, usService.homePage(uId));
	}
	//	2. 修改个人资料
	@RequestMapping("/chepro")
	private void changeUserProfile(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="text")  String text
			) {
		ResponseJsonUtils.json(response, usService.changeUserProfile(uId,text));
	}
	//	3. 关注其他用户（向被关注的用户发送一条消息）
	@RequestMapping("/foluse")
	private void followUser(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="fuId")  int fuId
			) {
		ResponseJsonUtils.json(response, usService.followUser(uId,fuId));
	}
	//	4. 获取此用户关注的所有用户
	@RequestMapping("/foluselist")
	private void followUserList(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="pagNum")  int pagNum,
			@RequestParam(value="limit")  int limit
			) {
		ResponseJsonUtils.json(response, usService.followList(uId,pagNum,limit,2));
	}
	//	5. 查看关注的某个用户（同1）
	@RequestMapping("/getfoluse")
	private void getUser(HttpServletResponse response,
			@RequestParam(value="uId")  int uId
			) {
		ResponseJsonUtils.json(response, usService.homePage(uId));
	}
	//	6. 查看关注自己的粉丝
	@RequestMapping("/befoluselist")
	private void beFollowUserList(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="pagNum")  int pagNum,
			@RequestParam(value="limit")  int limit
			) {
		ResponseJsonUtils.json(response, usService.beFollowList(uId,pagNum,limit,2));
	}
	//	7. 查看收藏的文章（此接口位于文章模块）
	//	8. 查看收藏的专题（专题模块）
	//	9. 查看收藏的专辑（电台-专辑模块）
	//	10. 查看自己写的文章列表（文章模块）
	//	11. 查看自己的浏览记录（每种浏览分类记录仅10条，超出10条顶掉最旧的，带有筛选分类的功能）
	@RequestMapping("/getbrolist")
	private void getBrowseList(HttpServletResponse response,
			@RequestParam(value="uId")  int uId
			) {
		ResponseJsonUtils.json(response, usService.getBrowseList(uId));
	}
	//	12. 删除某一条浏览记录
	@RequestMapping("/delbro")
	private void delBrowse(HttpServletResponse response,
			@RequestParam(value="fId")  int fId
			) {
		ResponseJsonUtils.json(response, usService.delBrowse(fId));
	}
	//	13. 清空自己的浏览记录(带有分类，清空某个分类的浏览记录)
	@RequestMapping("/delbrolist")
	private void delBrowseList(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="type")  int type//4 - 文章浏览记录；5- 节目浏览记录
			) {
		ResponseJsonUtils.json(response, usService.delBrowseList(uId,type));
	}
	//添加Group 
	@RequestMapping("/addgro")
	private void addGroup(HttpServletResponse response,
			@RequestParam(value="sign")  String sign,
			@RequestParam(value="name")  String name,
			@RequestParam(value="describe")  String describe
			) {
		Group gro = new Group(sign,name,describe);
		ResponseJsonUtils.json(response, usService.addGroup(gro));
	}

	
}
