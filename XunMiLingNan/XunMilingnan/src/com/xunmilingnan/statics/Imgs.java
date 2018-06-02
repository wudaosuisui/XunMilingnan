package com.xunmilingnan.statics;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public  class Imgs {
	//WebContentUrl��ַ
	private static String  WebContentUrl = "F:\\\\Documents\\\\GitHub\\\\XunMilingnan\\\\XunMiLingNan\\\\XunMilingnan\\\\WebContent";
	//ͼƬ�ĵ�ַ
	private static String  ImgsUrl="F:\\Documents\\GitHub\\XunMilingnan\\XunMiLingNan\\XunMilingnan\\WebContent\\img\\";
	
	//��ȡһ��ͼƬ ��  ������ �� �洢 �� �������ͼƬ�Ĵ���Url
	public  String getImgUrl(MultipartFile img) {
	    System.out.println("get getImg  success");
		// ��ͼƬ���浽ͼƬĿ¼��
	    // ����ͼƬ�����ͼƬ�е�ʱ���ļ������ܻ��ظ����㱣����˻��ԭ����ͼƬ�����ǵ�����Ͳ�̫�����ˡ�
	    // ����Ϊÿ���ļ�����һ���µ��ļ���
	    String picName = UUID.randomUUID().toString();
	    System.out.println("get  picName success");
	    // ��ȡ�ļ�����չ��(��.jpg)
	    String oriName = img.getOriginalFilename();//�ļ�������������
	    String extName = oriName.substring(oriName.lastIndexOf("."));//�ļ�����չ��
	    String imgUrl = ImgsUrl +picName+extName;
	    // �����ļ�
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
	//ɾ��һ��ͼƬ
	public void deleteImg(String fileName) {
		 File file = new File(fileName);
	        // ����ļ�·������Ӧ���ļ����ڣ�������һ���ļ�����ֱ��ɾ��
	        if (file.exists() && file.isFile()) {
	            if (file.delete()) {
	                System.out.println("ɾ�������ļ�" + fileName + "�ɹ���");
//	                return true;
	            } else {
	                System.out.println("ɾ�������ļ�" + fileName + "ʧ�ܣ�");
//	                return false;
	            }
	        } else {
	            System.out.println("ɾ�������ļ�ʧ�ܣ�" + fileName + "�����ڣ�");
//	            return false;
	        }
	}
}
