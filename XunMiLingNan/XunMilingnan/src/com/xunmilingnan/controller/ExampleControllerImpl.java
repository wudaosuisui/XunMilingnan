package com.xunmilingnan.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xunmilingnan.service.UserGroupServiceImpl;

@Controller
@Repository
@RequestMapping("/extest")/*���ĳɶ�Ӧ�����֣����Ӧ��ַ*/
public class ExampleControllerImpl {/*�շ�ʽ����*/
	@Resource
	private UserGroupServiceImpl exampleServiceImple;

	@RequestMapping(value="/add")/*������Ӧ��ַ*/
//	@PostMapping("/add")
	public String Add() {/*�շ�ʽ����*/
		System.out.println("get add controller");
		return "Example/Example";
	}
}
