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

@Controller//���ڹ�������controller
@Repository
@RequestMapping("/adver")
public class AdvertisementController {
	@Resource
	private AdvertisementService advService;
	
	
	//	1. ��ӹ����� // type;0 - �����ࣻ
	@PostMapping("/addadvcat")
	public void addAdvCat(HttpServletResponse response,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber") int sortNumber
			) {
		//����һ��������
		AdvCategory advCat = new AdvCategory(name,0,sortNumber);
		//���벢����
		ResponseJsonUtils.json(response, advService.addAdvCat(advCat));
	}
	//	2. ���¹�����
	@PostMapping("/updadvcat")
	public void updAdvCat(HttpServletResponse response,
			@RequestParam(value="aId") int aId,
			@RequestParam(value="name") String name,
			@RequestParam(value="sortNumber") int sortNumber
			) {
		//����һ��������
		AdvCategory advCat = new AdvCategory(name,0,sortNumber);
		advCat.setId(aId);
		//���벢����
		ResponseJsonUtils.json(response, advService.updAdvCat(advCat));
	}
	//	3. ɾ��������
	@PostMapping("/updadvcat")
	public void delAdvCat(HttpServletResponse response,
			@RequestParam(value="aId") int aId
			) {
		ResponseJsonUtils.json(response, advService.delAdvCat(aId));
	}
	//	4. ��ȡ���й�����(���з�ҳ��ɸѡ)
	@PostMapping("/getalladvcat")
	public void getAllAdvCat(HttpServletResponse response,
			@RequestParam(value="pagNum") int pagNum
			) {
		ResponseJsonUtils.json(response, advService.getAllAdvCat(pagNum));
	}
	//	5. ���һ�����
	@PostMapping("/addadv")
	public void addAdvertisement(HttpServletResponse response,
			@RequestParam(value="advCatId") int advCatId,
			@RequestParam(value="title") String title,
			@RequestParam(value="img") String img,
			@RequestParam(value="url") String url,
			@RequestParam(value="sortNumber") int sortNumber
			) {
		//��ȡ�������
		AdvCategory advCat = advService.getAdvCat(advCatId);
		//����һ�����
		Advertisement adv = new Advertisement(advCat,title,img,url,sortNumber);
		//���벢����
		ResponseJsonUtils.json(response, advService.addAdvertisement(adv));
	}
	//	6. ����һ�����
	@PostMapping("/updadv")
	public void updAdvertisement(HttpServletResponse response,
			@RequestParam(value="advId") int advId,
			@RequestParam(value="advCatId") int advCatId,
			@RequestParam(value="title") String title,
			@RequestParam(value="img") String img,
			@RequestParam(value="url") String url,
			@RequestParam(value="sortNumber") int sortNumber
			) {
		//��ȡ�������
		AdvCategory advCat = advService.getAdvCat(advCatId);
		//����һ�����
		Advertisement adv = new Advertisement(advCat,title,img,url,sortNumber);
		//����id
		adv.setId(advId);
		//���벢����
		ResponseJsonUtils.json(response, advService.updAdvertisement(adv));
	}
	//	7. ɾ��һ�����
	@PostMapping("/deladv")
	public void deleteAdvertisement(HttpServletResponse response,
			@RequestParam(value="advId") int advId
			) {
		ResponseJsonUtils.json(response, advService.deleteAdvertisement(advId));
	}
	//	8. ��ȡ���й��(���з�ҳ��ɸѡ)
	@PostMapping("/getadvlist")
	public void getAdvList(HttpServletResponse response,
			@RequestParam(value="pagNum") int pagNum
			) {
		ResponseJsonUtils.json(response, advService.getAdvList(pagNum));
	}
	//  9. ��ȡĳ���������µ����й�棨���з�ҳ��ɸѡ��
	@PostMapping("/getadvlist")
	public void getAdvListInAdvCat(HttpServletResponse response,
			@RequestParam(value="acId") int acId,
			@RequestParam(value="pagNum") int pagNum
			) {
		ResponseJsonUtils.json(response, advService.getAdvListInAdvCat(acId,pagNum));
	}
}
