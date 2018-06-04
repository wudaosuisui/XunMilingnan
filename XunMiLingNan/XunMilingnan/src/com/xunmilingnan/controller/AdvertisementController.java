package com.xunmilingnan.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xunmilingnan.service.AdvertisementService;

@Controller//关于广告的所有controller
@Repository
@RequestMapping("/adver")
public class AdvertisementController {
	@Resource
	private AdvertisementService advService;
	
	
	//	1. 添加广告分类
	//	2. 更新广告分类
	//	3. 删除广告分类
	//	4. 获取所有广告分类(带有分页和筛选)
	//	5. 添加一条广告
	//	6. 更新一条广告
	//	7. 删除一条广告
	//	8. 获取所有广告(带有分页和筛选)

}
