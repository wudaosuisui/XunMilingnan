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

@Controller//������Ϣ������controller
@Repository
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService usService;
	//count����
	//��¼
	@RequestMapping("/login")
	private void logIn(HttpServletResponse response,
			@RequestParam(value="code")  String code) {
		ResponseJsonUtils.json(response, usService.login(code));
	}
	//ע��
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
	//	1. �鿴������ҳ
	@RequestMapping("/homepage")
	private void homePage(HttpServletResponse response,
			@RequestParam(value="uId")  int uId) {
		ResponseJsonUtils.json(response, usService.homePage(uId));
	}
	//	2. �޸ĸ�������
	@RequestMapping("/chepro")
	private void changeUserProfile(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="text")  String text
			) {
		ResponseJsonUtils.json(response, usService.changeUserProfile(uId,text));
	}
	//	3. ��ע�����û����򱻹�ע���û�����һ����Ϣ��
	@RequestMapping("/foluse")
	private void followUser(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="fuId")  int fuId
			) {
		ResponseJsonUtils.json(response, usService.followUser(uId,fuId));
	}
	//	4. ��ȡ���û���ע�������û�
	@RequestMapping("/foluselist")
	private void followUserList(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="pagNum")  int pagNum,
			@RequestParam(value="limit")  int limit
			) {
		ResponseJsonUtils.json(response, usService.followList(uId,pagNum,limit,2));
	}
	//	5. �鿴��ע��ĳ���û���ͬ1��
	@RequestMapping("/getfoluse")
	private void getUser(HttpServletResponse response,
			@RequestParam(value="uId")  int uId
			) {
		ResponseJsonUtils.json(response, usService.homePage(uId));
	}
	//	6. �鿴��ע�Լ��ķ�˿
	@RequestMapping("/befoluselist")
	private void beFollowUserList(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="pagNum")  int pagNum,
			@RequestParam(value="limit")  int limit
			) {
		ResponseJsonUtils.json(response, usService.beFollowList(uId,pagNum,limit,2));
	}
	//	7. �鿴�ղص����£��˽ӿ�λ������ģ�飩
	//	8. �鿴�ղص�ר�⣨ר��ģ�飩
	//	9. �鿴�ղص�ר������̨-ר��ģ�飩
	//	10. �鿴�Լ�д�������б�����ģ�飩
	//	11. �鿴�Լ��������¼��ÿ����������¼��10��������10��������ɵģ�����ɸѡ����Ĺ��ܣ�
	@RequestMapping("/getbrolist")
	private void getBrowseList(HttpServletResponse response,
			@RequestParam(value="uId")  int uId
			) {
		ResponseJsonUtils.json(response, usService.getBrowseList(uId));
	}
	//	12. ɾ��ĳһ�������¼
	@RequestMapping("/delbro")
	private void delBrowse(HttpServletResponse response,
			@RequestParam(value="fId")  int fId
			) {
		ResponseJsonUtils.json(response, usService.delBrowse(fId));
	}
	//	13. ����Լ��������¼(���з��࣬���ĳ������������¼)
	@RequestMapping("/delbrolist")
	private void delBrowseList(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="type")  int type//4 - ���������¼��5- ��Ŀ�����¼
			) {
		ResponseJsonUtils.json(response, usService.delBrowseList(uId,type));
	}
	//���Group 
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
