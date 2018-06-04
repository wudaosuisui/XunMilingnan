package com.xunmilingnan.statics;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public  class Uploads {
	//WebContentUrl地址
	private static String  WebContentUrl ="F:\\Documents\\GitHub\\XunMilingnan\\XunMiLingNan\\XunMilingnan\\WebContent\\";
	//图片的地址
	private static String  ImgsUrl="F:\\Documents\\GitHub\\XunMilingnan\\XunMiLingNan\\XunMilingnan\\WebContent\\img\\";
	//音频的地址
	private static String RadioUrl="F:\\Documents\\GitHub\\XunMilingnan\\XunMiLingNan\\XunMilingnan\\WebContent\\radio\\";
	
	
	
	//存入图片
	public  HashMap<String, Object> addImg(MultipartFile img) {
	  return this.add(ImgsUrl, img);
	}
	
	//存入音频
	public  HashMap<String, Object> addRadio(MultipartFile radio) {
		return this.add(RadioUrl, radio);

	}
	//删除图片
	public HashMap<String, Object> deleteImg(String fileName) {
		return this.delete(ImgsUrl, fileName);
	}
	
	//删除音频
	public HashMap<String, Object> deleteRad(String fileName) {
		return this.delete(RadioUrl, fileName);
	}
	
	
	//存入住函数
	public HashMap<String, Object> add(String url ,MultipartFile img) {
		//返回值
		Result result = new Result();
		String statusCode ="0002xx";//状态码
		String desc ;//状态码描述
		//执行内容
		// 生成一个新的文件名
	    String picName = UUID.randomUUID().toString();
	    // 截取文件的扩展名(如.jpg .mp4)
	    String oriName = img.getOriginalFilename();//文件的整个的名字
	    String extName = oriName.substring(oriName.lastIndexOf("."));//文件的扩展名
	    // 保存文件
	    try {
			img.transferTo(new File(url +picName+extName));
			statusCode = "000000";//状态码（添加失败）
			desc = "添加成功";//状态码描述
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			statusCode = "000001";//状态码（添加失败）
			desc = "存入失败";//状态码描述
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			statusCode = "000001";//状态码（添加失败）
			desc = "存入失败";//状态码描述
		}
		//存入返回值
        Map message = new HashMap<String,Object>(3)
        {{
			put("fileUrl",url);//存储地址
			put("fileName",picName);//图片名称
			put("extName",extName);//图片后缀
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
        return result.getRe();
		
	}
	
	
	//删除 （主函数）
	public HashMap<String, Object> delete(String url,String name){
		//返回值
		Result result = new Result();
		String statusCode ="0002xx";//状态码
		String desc ;//状态码描述
		/*执行操作*/
		File file = new File(url +name);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {//文件路径所对应的文件存在，并且是一个文件，
            if (file.delete()) {//删除
                statusCode = "000000";
                desc = "删除成功";
            }else {
                statusCode = "000200";
            	desc = "删除失败";
            }
        }else {
            statusCode = "000201";
        	desc = "文件所路径所对应的文件不存在，或不是一个文件";
        }
        Map message = new HashMap<String,Object>(0);
	    //存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
        return result.getRe();
	}
	
	
//	return new HashMap<String, Object>(10)
//			{{put("UserGroup",usGroDao.getById(1));
//			put("AdvertisementCategory",adverCatDao.getById(1));
//			put("Channel",chaDao.getById(1));
//			put("User",userDao.getById(1));
//			put("Activity",actDao.getById(1));
//			put("Admin",admDao.getById(1));
//			put("Advertisement",aderDao.getById(1));
//			put("Article",artDao.getById(1));
//			put("Comment",comDao.getById(1));
//			put("Program",proDao.getById(1));
//			}} ;
}
