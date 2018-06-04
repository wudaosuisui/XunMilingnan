package com.xunmilingnan.statics;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public  class Uploads {
	//WebContentUrl��ַ
	private static String  WebContentUrl ="F:\\Documents\\GitHub\\XunMilingnan\\XunMiLingNan\\XunMilingnan\\WebContent\\";
	//ͼƬ�ĵ�ַ
	private static String  ImgsUrl="F:\\Documents\\GitHub\\XunMilingnan\\XunMiLingNan\\XunMilingnan\\WebContent\\img\\";
	//��Ƶ�ĵ�ַ
	private static String RadioUrl="F:\\Documents\\GitHub\\XunMilingnan\\XunMiLingNan\\XunMilingnan\\WebContent\\radio\\";
	
	
	
	//����ͼƬ
	public  HashMap<String, Object> addImg(MultipartFile img) {
	  return this.add(ImgsUrl, img);
	}
	
	//������Ƶ
	public  HashMap<String, Object> addRadio(MultipartFile radio) {
		return this.add(RadioUrl, radio);

	}
	//ɾ��ͼƬ
	public HashMap<String, Object> deleteImg(String fileName) {
		return this.delete(ImgsUrl, fileName);
	}
	
	//ɾ����Ƶ
	public HashMap<String, Object> deleteRad(String fileName) {
		return this.delete(RadioUrl, fileName);
	}
	
	
	//����ס����
	public HashMap<String, Object> add(String url ,MultipartFile img) {
		//����ֵ
		Result result = new Result();
		String statusCode ="0002xx";//״̬��
		String desc ;//״̬������
		//ִ������
		// ����һ���µ��ļ���
	    String picName = UUID.randomUUID().toString();
	    // ��ȡ�ļ�����չ��(��.jpg .mp4)
	    String oriName = img.getOriginalFilename();//�ļ�������������
	    String extName = oriName.substring(oriName.lastIndexOf("."));//�ļ�����չ��
	    // �����ļ�
	    try {
			img.transferTo(new File(url +picName+extName));
			statusCode = "000000";//״̬�루���ʧ�ܣ�
			desc = "��ӳɹ�";//״̬������
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			statusCode = "000001";//״̬�루���ʧ�ܣ�
			desc = "����ʧ��";//״̬������
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			statusCode = "000001";//״̬�루���ʧ�ܣ�
			desc = "����ʧ��";//״̬������
		}
		//���뷵��ֵ
        Map message = new HashMap<String,Object>(3)
        {{
			put("fileUrl",url);//�洢��ַ
			put("fileName",picName);//ͼƬ����
			put("extName",extName);//ͼƬ��׺
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
        return result.getRe();
		
	}
	
	
	//ɾ�� ����������
	public HashMap<String, Object> delete(String url,String name){
		//����ֵ
		Result result = new Result();
		String statusCode ="0002xx";//״̬��
		String desc ;//״̬������
		/*ִ�в���*/
		File file = new File(url +name);
        // ����ļ�·������Ӧ���ļ����ڣ�������һ���ļ�����ֱ��ɾ��
        if (file.exists() && file.isFile()) {//�ļ�·������Ӧ���ļ����ڣ�������һ���ļ���
            if (file.delete()) {//ɾ��
                statusCode = "000000";
                desc = "ɾ���ɹ�";
            }else {
                statusCode = "000200";
            	desc = "ɾ��ʧ��";
            }
        }else {
            statusCode = "000201";
        	desc = "�ļ���·������Ӧ���ļ������ڣ�����һ���ļ�";
        }
        Map message = new HashMap<String,Object>(0);
	    //���뷵��ֵ
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
