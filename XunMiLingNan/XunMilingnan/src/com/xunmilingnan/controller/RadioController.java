package com.xunmilingnan.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xunmilingnan.entity.AdvCategory;
import com.xunmilingnan.service.RadioService;
import com.xunmilingnan.statics.Imgs;
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
	//�й�ͼƬ����
	private  Imgs imgs = new Imgs();
	
	//��ȡ���е�̨����
	@GetMapping("/getalladcat")
	public void getAllRadioCategory(HttpServletResponse response) {
		ResponseJsonUtils.json(response, radioService.getList());
	}
	//���һ����̨����
	@PostMapping("/addradcat")
	public void addRadioCategory( HttpServletResponse response,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber") int sortNumber,//������	
			@RequestParam(value="img") MultipartFile img//�ϴ�����ͼ��
			) {
		System.out.println("get controller success");
		
		String imgName = this.imgs.getImgUrl(img);
		System.out.println("get imgUrl success");
		//����һ������̨���ࡱ��AdvCategory
		AdvCategory advCat = new AdvCategory(name,sortNumber,imgName);
		//�����AdvCategory �������ݿ�  ����json����
		ResponseJsonUtils.json(response, radioService.addRadCat(advCat));
		System.out.println("out controller success");
	}
	
	//ɾ��һ����̨����
	@PostMapping("/delradcat")
	public void deleteRadioCategory(HttpServletResponse response,
			@RequestParam(value="radCatId") int id
			) {
		System.out.println("get controller ");
		radioService.deleteRadCat(id);
		System.out.println("out controller ");
	}
	
	//����һ����̨����
	@PostMapping("/updradcat")
	public void updateRadioCategory( HttpServletResponse response,
			@RequestParam(value="radCatId") int id,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber") int sortNumber,//������	
			MultipartFile img//�ϴ�����ͼ��
			) {
		//��ȡ���AdvCategory
		AdvCategory advCat = radioService.getRadCat(id);
		//����ԭ��ͼƬɾ�������ڿ��Ըĳ� �� �ж�һ�£�
		imgs.deleteImg(advCat.getImg());
		//���������Ը�ֵ���µ�ֵ
		advCat.setName(name);
		advCat.setSortNumber(sortNumber);
		advCat.setImg(imgs.getImgUrl(img));
		//����AdvCategory  ������ 
		ResponseJsonUtils.json(response, radioService.updRadCat(advCat));
	}
	
	
}
