package com.xunmilingnan.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xunmilingnan.service.AdvertisementService;

@Controller//���ڹ�������controller
@Repository
@RequestMapping("/adver")
public class AdvertisementController {
	@Resource
	private AdvertisementService advService;
	
	
	//	1. ��ӹ�����
	//	2. ���¹�����
	//	3. ɾ��������
	//	4. ��ȡ���й�����(���з�ҳ��ɸѡ)
	//	5. ���һ�����
	//	6. ����һ�����
	//	7. ɾ��һ�����
	//	8. ��ȡ���й��(���з�ҳ��ɸѡ)

}
