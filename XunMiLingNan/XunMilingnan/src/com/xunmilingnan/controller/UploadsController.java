package com.xunmilingnan.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xunmilingnan.statics.ResponseJsonUtils;
import com.xunmilingnan.statics.Uploads;

@Controller//有关文件上传所以方法
@Repository
@RequestMapping("/uploads")
public class UploadsController {
	//有关上传文件的类
	private  Uploads uploads = new Uploads();
	
	@PostMapping("/img")
	public void addImg(HttpServletResponse response,MultipartFile img) {
		ResponseJsonUtils.json(response,uploads.addImg(img));
	}
	
	@PostMapping("/radio")
	public void addRadio(HttpServletResponse response,MultipartFile radio) {
		ResponseJsonUtils.json(response,uploads.addRadio(radio));
	}
	
	@PostMapping("/delimg")
	public void deleteImg(HttpServletResponse response,String imgName) {
		ResponseJsonUtils.json(response,uploads.deleteImg(imgName));
	}
	
	@PostMapping("/delrad")
	public void deleteRadio(HttpServletResponse response,String radName) {
		ResponseJsonUtils.json(response,uploads.deleteRad(radName));
	}
}
