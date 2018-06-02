package com.xunmilingnan.statics;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public  class Imgs {
	//WebContentUrl地址
	private static String  WebContentUrl = "F:\\\\Documents\\\\GitHub\\\\XunMilingnan\\\\XunMiLingNan\\\\XunMilingnan\\\\WebContent";
	//图片的地址
	private static String  ImgsUrl="F:\\Documents\\GitHub\\XunMilingnan\\XunMiLingNan\\XunMilingnan\\WebContent\\img\\";
	
	//获取一张图片 、  重命名 、 存储 、 返回这个图片的存贮Url
	public  String getImgUrl(MultipartFile img) {
	    System.out.println("get getImg  success");
		// 把图片保存到图片目录下
	    // 保存图片，这个图片有的时候文件名可能会重复，你保存多了会把原来的图片给覆盖掉，这就不太合适了。
	    // 所以为每个文件生成一个新的文件名
	    String picName = UUID.randomUUID().toString();
	    System.out.println("get  picName success");
	    // 截取文件的扩展名(如.jpg)
	    String oriName = img.getOriginalFilename();//文件的整个的名字
	    String extName = oriName.substring(oriName.lastIndexOf("."));//文件的扩展名
	    String imgUrl = ImgsUrl +picName+extName;
	    // 保存文件
	    try {
			img.transferTo(new File(imgUrl));
		    System.out.println("get new File  success");

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("out  success");

		return picName+extName;
	}
	//删除一张图片
	public void deleteImg(String fileName) {
		 File file = new File(fileName);
	        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
	        if (file.exists() && file.isFile()) {
	            if (file.delete()) {
	                System.out.println("删除单个文件" + fileName + "成功！");
//	                return true;
	            } else {
	                System.out.println("删除单个文件" + fileName + "失败！");
//	                return false;
	            }
	        } else {
	            System.out.println("删除单个文件失败：" + fileName + "不存在！");
//	            return false;
	        }
	}
}
