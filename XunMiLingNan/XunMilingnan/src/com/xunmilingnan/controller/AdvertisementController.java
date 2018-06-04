package com.xunmilingnan.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xunmilingnan.entity.AdvCategory;
import com.xunmilingnan.entity.Advertisement;
import com.xunmilingnan.service.AdvertisementService;
import com.xunmilingnan.statics.ResponseJsonUtils;

@Controller//关于广告的所有controller
@Repository
@RequestMapping("/adver")
public class AdvertisementController {
	@Resource
	private AdvertisementService advService;
	
	
	//	1. 添加广告分类 // type;0 - 广告分类；
	@PostMapping("/addadvcat")
	public void addAdvCat(HttpServletResponse response,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber") int sortNumber
			) {
		//创建一个广告分类
		AdvCategory advCat = new AdvCategory(name,0,sortNumber);
		//存入并返回
		ResponseJsonUtils.json(response, advService.addAdvCat(advCat));
	}
	//	2. 更新广告分类
	@PostMapping("/updadvcat")
	public void updAdvCat(HttpServletResponse response,
			@RequestParam(value="aId") int aId,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber") int sortNumber
			) {
		//创建一个广告分类
		AdvCategory advCat = new AdvCategory(name,0,sortNumber);
		advCat.setId(aId);
		//存入并返回
		ResponseJsonUtils.json(response, advService.updAdvCat(advCat));
	}
	//	3. 删除广告分类
	@PostMapping("/updadvcat")
	public void delAdvCat(HttpServletResponse response,
			@RequestParam(value="aId") int aId
			) {
		ResponseJsonUtils.json(response, advService.delAdvCat(aId));
	}
	//	4. 获取所有广告分类(带有分页和筛选)
	@PostMapping("/getalladvcat")
	public void getAllAdvCat(HttpServletResponse response,
			@RequestParam(value="pagNum") int pagNum
			) {
		ResponseJsonUtils.json(response, advService.getAllAdvCat(pagNum));
	}
	//	5. 添加一条广告
	@PostMapping("/addadv")
	public void addAdvertisement(HttpServletResponse response,
			@RequestParam(value="advCatId") int advCatId,
			@RequestParam(value="title") String title,
			@RequestParam(value="img") String img,
			@RequestParam(value="url") String url,
			@RequestParam(value="sortNumber") int sortNumber
			) {
		//获取广告类型
		AdvCategory advCat = advService.getAdvCat(advCatId);
		//创建一个广告
		Advertisement adv = new Advertisement(advCat,title,img,url,sortNumber);
		//存入并返回
		ResponseJsonUtils.json(response, advService.addAdvertisement(adv));
	}
	//	6. 更新一条广告
	@PostMapping("/updadv")
	public void updAdvertisement(HttpServletResponse response,
			@RequestParam(value="advId") int advId,
			@RequestParam(value="advCatId") int advCatId,
			@RequestParam(value="title") String title,
			@RequestParam(value="img") String img,
			@RequestParam(value="url") String url,
			@RequestParam(value="sortNumber") int sortNumber
			) {
		//获取广告类型
		AdvCategory advCat = advService.getAdvCat(advCatId);
		//创建一个广告
		Advertisement adv = new Advertisement(advCat,title,img,url,sortNumber);
		//输入id
		adv.setId(advId);
		//存入并返回
		ResponseJsonUtils.json(response, advService.updAdvertisement(adv));
	}
	//	7. 删除一条广告
	@PostMapping("/deladv")
	public void deleteAdvertisement(HttpServletResponse response,
			@RequestParam(value="advId") int advId
			) {
		ResponseJsonUtils.json(response, advService.deleteAdvertisement(advId));
	}
	//	8. 获取所有广告(带有分页和筛选)
	@PostMapping("/getadvlist")
	public void getAdvList(HttpServletResponse response,
			@RequestParam(value="pagNum") int pagNum
			) {
		ResponseJsonUtils.json(response, advService.getAdvList(pagNum));
	}
	//  9. 获取某个广告分类下的所有广告（带有分页和筛选）
	@PostMapping("/getadvlist")
	public void getAdvListInAdvCat(HttpServletResponse response,
			@RequestParam(value="acId") int acId,
			@RequestParam(value="pagNum") int pagNum
			) {
		ResponseJsonUtils.json(response, advService.getAdvListInAdvCat(acId,pagNum));
	}
}
