package com.xunmilingnan.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xunmilingnan.service.UserGroupServiceImpl;

@Controller
@Repository
@RequestMapping("/extest")/*更改成对应的名字；类对应地址*/
public class ExampleControllerImpl {/*驼峰式命名*/
	@Resource
	private UserGroupServiceImpl exampleServiceImple;

	@RequestMapping(value="/add")/*方法对应地址*/
//	@PostMapping("/add")
	public String Add() {/*驼峰式命名*/
		System.out.println("get add controller");
		return "Example/Example";
	}
}
