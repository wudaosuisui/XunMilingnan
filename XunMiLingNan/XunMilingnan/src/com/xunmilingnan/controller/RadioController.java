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

@Controller//�йص�̨������controller
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
//	,method={RequestMethod.POST,RequestMethod.GET} ����ʹ��post��get��ʽ  
	@Resource
	private  RadioService radioService;
	@Resource
	private UserService userService;
	@Resource
	private FollowService followService;
	
	/*��Ŀ�Ĳ���-------------------------------------------------------------------*/
	//	1. ��һ��ר������ӣ�������һ����Ŀ���û���
	@PostMapping("/addprogram")//���һ����ĿProgram
	public void addProgram(HttpServletResponse response,
			@RequestParam(value="time") String time,//����ʱ��
			@RequestParam(value="longOfTime") String longOfTime,//��Ŀʱ��
			@RequestParam(value="name") String  name,//��Ŀ����
			@RequestParam(value="advCat") int advCatid,//��Ӧ��̨�����id
			@RequestParam(value="album") int albumId,//��Ӧר��
			@RequestParam(value="FMName") String FMName,//��Ŀ��Դ���ļ����ƣ���236.mp4
			@RequestParam(value="sortNumber") int sortNumber//������
			) {
		//��ȡ��Ӧר��
		Album album = radioService.getAlbum(albumId);
		//����һ����Ŀ����
		Program pro = new Program(time,longOfTime,name,advCatid,album,FMName,sortNumber);
		ResponseJsonUtils.json(response, radioService.addProgram(pro));
	}
	//	2. ��һ��ר����ɾ��һ����Ŀ���û���
	@PostMapping("/delprogram")
	public void deletProgram(HttpServletResponse response,
			@RequestParam(value="proId")  int  proId) {
		ResponseJsonUtils.json(response, radioService.deletProgram(proId));
	}
	//	3. ��һ��ר���¸���һ����Ŀ���û������������Ƶ�
	@PostMapping("/updprogram")
	public void updProgram(HttpServletResponse response,
			@RequestParam(value="id")  int id,
			@RequestParam(value="time") String time,//����ʱ��
			@RequestParam(value="longOfTime") String longOfTime,//��Ŀʱ��
			@RequestParam(value="name") String  name,//��Ŀ����
			@RequestParam(value="advCat") int advCatid,//��Ӧ��̨�����id
			@RequestParam(value="album") int albumId,//��Ӧר��
			@RequestParam(value="FMName") String FMName,//��Ŀ��Դ���ļ����ƣ���236.mp4
			@RequestParam(value="sortNumber") int sortNumber//������
			) {
		//��ȡ��Ӧר��
		Album album = radioService.getAlbum(albumId);
		//����һ����Ŀ����
		Program pro = new Program(time,longOfTime,name,advCatid,album,FMName,sortNumber);
		//����˽�Ŀ����Ӧ�е�id
		pro.setId(id);
		ResponseJsonUtils.json(response, radioService.updProgram(pro));
	}
	//	4. �ղ�ĳ����Ŀ(���ݽ�Ŀid���û�id)���û��ղ�(�û�)
	@PostMapping("/folprogram")
	public void followProgram(HttpServletResponse response,
			@RequestParam(value="pId")  int pId,
			@RequestParam(value="uId")  int uId
			) {
		//��ȡUser;
		User user = userService.getUserById(uId);
		//�ղؽ�Ŀ��һ
		radioService.followProgram(pId);
		//����һ��Follow
		Follow fo = new Follow(user,1,pId);
		//��Follow����
		ResponseJsonUtils.json(response, followService.addFollow(fo));
	}
	//	5. ��ȡĳ���û�(�����û�id)�ղص����н�Ŀ(�û�)
	@PostMapping("/getfolprograms")
	public void getFollowPrograms(HttpServletResponse response,
			@RequestParam(value="uId")  int uId,
			@RequestParam(value="pagNum")  int pagNum,
			@RequestParam(value="limit")  int limit
			) {
		ResponseJsonUtils.json(response, followService.getFollowPrograms(uId,pagNum,limit));
	}
	//	6. ��ȡĳר�������н�Ŀ����ID/����ʱ�䵹��
	@PostMapping("/getproinalbum")
	public void getProgramsInAlbum(HttpServletResponse response,
			@RequestParam(value="aId")  int aId,
			@RequestParam(value="pagNum")  int pagNum,
			@RequestParam(value="limit")  int limit
			) {
		ResponseJsonUtils.json(response, radioService.getProgramsInAlbum(aId,pagNum,limit));
	}
	//	7. ��ȡĳ����̨���������Խ�Ŀ����ID/����ʱ�䵹��
	@PostMapping("/getproinradcat")
	public void getProgramsInRadCat(HttpServletResponse response,
			@RequestParam(value="rId")  int rId,
			@RequestParam(value="pagNum")  int pagNum,//ҳ��,
			@RequestParam(value="limit")  int limit
			) {
		ResponseJsonUtils.json(response, radioService.getProgramsInRadCat(rId,pagNum,limit));
	}
	//	8. ��ȡĳ����Ŀ
	@PostMapping("/browsepro")
	public void browseProgram(HttpServletResponse response,
			@RequestParam(value="pId")  int pId
			) {
		ResponseJsonUtils.json(response, radioService.browseProgram(pId));
	}
	//	9. ����ĳ����Ŀ
	@PostMapping("/praisepro")
	public void praiseProgram(HttpServletResponse response,
			@RequestParam(value="pId")  int pId
			) {
		ResponseJsonUtils.json(response, radioService.praiseProgram(pId));
	}
	/*ר���Ĳ���-------------------------------------------------------------------*/
	//	1. ��һ����̨��������ӣ�������һ��ר�����û���/addalbum	
	@PostMapping("/addalbum")//���һ��ר��
	public void addAlbum(HttpServletResponse response,
			@RequestParam(value="rcId") int rcId,
			@RequestParam(value="relTime") String relTime,
			@RequestParam(value="uId") int uId,
			@RequestParam(value="name") String name,
			@RequestParam(value="img")  String img
			) {
		//��ȡAdvCategory��User
		AdvCategory rad = radioService.getRadCat(rcId);
		User use = userService.getUserById(uId);
		//����һ����̨ר��
		Album album = new Album(rad,use,name,relTime,img);
		//���벢����
		ResponseJsonUtils.json(response, radioService.addAlbum(album));
	}
	//	2. ��һ����̨������ɾ��һ��ר�����û���/delealbum
	@PostMapping("/delalbum")//ɾ��һ��ר��
	public void delAlbum(HttpServletResponse response,
			@RequestParam(value="aid") int aid
			) {
		//ɾ��������
		System.out.println("get controller");
		ResponseJsonUtils.json(response, radioService.delAlbum(aid));
	}
	//	3. ��һ����̨�����¸���һ��ר�����û������������桢�������ֵȲ���/updalbum
	@PostMapping("/updalbum")//����һ��ר��
	public void updAlbum(HttpServletResponse response,
			@RequestParam(value="alid") int alid,
			@RequestParam(value="relTime") String relTime,
			@RequestParam(value="rcId") int rcId,
			@RequestParam(value="uId") int uId,
			@RequestParam(value="name") String name,
			@RequestParam(value="img")  String img
			) {
		//��ȡAdvCategory��User
		AdvCategory rad = radioService.getRadCat(rcId);
		User use = userService.getUserById(uId);
		//����һ����̨ר��
		Album album = new Album(rad,use,name,relTime,img);
		album.setId(alid);
		//���벢����
		ResponseJsonUtils.json(response, radioService.updAlbum(album));
	}
	//	4. ��ȡһ����̨�����µ�����ר������ID/����ʱ�䵹��/getallalbum
	@PostMapping("/getallalbum")//��ȡר���б�
	public void getAllAlbum(HttpServletResponse response,
			@RequestParam(value="acId") int acid,
			@RequestParam(value="pagNum") int pagNum,
			@RequestParam(value="limit")  int limit) {//pagNum ����ȡ�ķ�ҳ
		ResponseJsonUtils.json(response,radioService.getAlbumList(acid,pagNum,limit));
	}
	
	
	
	/*��̨����Ĳ���-------------------------------------------------------------------*/
	//	1. ���һ����̨����/addradcat
	@PostMapping("/addradcat")
	public void addRadioCategory( HttpServletResponse response,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber") int sortNumber,//������	
			@RequestParam(value="img") String img//�ϴ�����ͼ��
			) {
		//����һ������̨���ࡱ��AdvCategory
		AdvCategory advCat = new AdvCategory(name,sortNumber,img);
		//�����AdvCategory �������ݿ�  ����json����
		ResponseJsonUtils.json(response, radioService.addRadCat(advCat));
	}
	//	2. ɾ��һ����̨����/delradcat
	@PostMapping("/delradcat")
	public void deleteRadioCategory(HttpServletResponse response,
			@RequestParam(value="rcId") int rcId
			) {
		ResponseJsonUtils.json(response,radioService.deleteRadCat(rcId));
	}
	//	3. ����һ����̨����/updradcat
	@PostMapping("/updradcat")
	public void updateRadioCategory( HttpServletResponse response,
			@RequestParam(value="rcId") int rcId,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber") int sortNumber,//������	
			@RequestParam(value="img") String img//�ϴ�����ͼ��
			) {
		//����һ������̨���ࡱ��AdvCategory
		AdvCategory advCat = new AdvCategory(name,sortNumber,img);
		//��id ����
		advCat.setId(rcId);
		//����AdvCategory  ������ 
		ResponseJsonUtils.json(response, radioService.updRadCat(advCat));
	}
	//	4. ��ȡ���е�̨����(���з�ҳ��ɸѡ) /getalladcat
	@PostMapping("/getalladcat")//���з�ҳ����
	public void getAllRadioCategory(HttpServletResponse response,
			@RequestParam(value="pagNum")int pagNum,
			@RequestParam(value="limit")  int limit) {//pagNum ����ȡ�ķ�ҳ
		ResponseJsonUtils.json(response, radioService.getAdvCatList(pagNum,limit));
	}
	
	
}
