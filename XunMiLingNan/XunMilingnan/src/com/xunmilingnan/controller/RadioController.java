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
	//有关图片的类
	private  Imgs imgs = new Imgs();
	
	//获取所有电台分类
	@GetMapping("/getalladcat")
	public void getAllRadioCategory(HttpServletResponse response) {
		ResponseJsonUtils.json(response, radioService.getList());
	}
	//添加一个电台分类
	@PostMapping("/addradcat")
	public void addRadioCategory( HttpServletResponse response,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber") int sortNumber,//排序编号	
			@RequestParam(value="img") MultipartFile img//上传来的图像
			) {
		System.out.println("get controller success");
		
		String imgName = this.imgs.getImgUrl(img);
		System.out.println("get imgUrl success");
		//创建一个“电台分类”的AdvCategory
		AdvCategory advCat = new AdvCategory(name,sortNumber,imgName);
		//将这个AdvCategory 存入数据库  并用json返回
		ResponseJsonUtils.json(response, radioService.addRadCat(advCat));
		System.out.println("out controller success");
	}
	
	//删除一个电台分类
	@PostMapping("/delradcat")
	public void deleteRadioCategory(HttpServletResponse response,
			@RequestParam(value="radCatId") int id
			) {
		System.out.println("get controller ");
		radioService.deleteRadCat(id);
		System.out.println("out controller ");
	}
	
	//更新一个电台分类
	@PostMapping("/updradcat")
	public void updateRadioCategory( HttpServletResponse response,
			@RequestParam(value="radCatId") int id,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber") int sortNumber,//排序编号	
			MultipartFile img//上传来的图像
			) {
		//获取这个AdvCategory
		AdvCategory advCat = radioService.getRadCat(id);
		//将其原有图片删除（后期可以改成 先 判断一下）
		imgs.deleteImg(advCat.getImg());
		//将各个属性赋值成新的值
		advCat.setName(name);
		advCat.setSortNumber(sortNumber);
		advCat.setImg(imgs.getImgUrl(img));
		//更新AdvCategory  并返回 
		ResponseJsonUtils.json(response, radioService.updRadCat(advCat));
	}
	
	
}
