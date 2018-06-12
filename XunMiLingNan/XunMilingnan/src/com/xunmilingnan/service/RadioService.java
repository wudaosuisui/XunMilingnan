package com.xunmilingnan.service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.xunmilingnan.dao.AdvCategoryDao;
import com.xunmilingnan.dao.AlbumDao;
import com.xunmilingnan.dao.ProgramDao;
import com.xunmilingnan.entity.AdvCategory;
import com.xunmilingnan.entity.Album;
import com.xunmilingnan.entity.Program;
import com.xunmilingnan.statics.Page;
import com.xunmilingnan.statics.Result;
import com.xunmilingnan.statics.Uploads;

@Service
@Repository//�йص�̨�ķ���
public class RadioService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource//��̨���ࣨҲ�ǹ����ࣩDao
	private AdvCategoryDao advCatDao;
	@Resource//ר����
	private AlbumDao albumDao;
	@Resource//��Ŀ
	private ProgramDao proDao;
	
	
	/*��Ŀ�Ĳ���-------------------------------------------------------------------*/
	//	1. ��һ��ר������ӣ�������һ����Ŀ���û���
	public HashMap<String, Object> addProgram(Program pro) {
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		proDao.save(pro);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("program",pro);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. ��һ��ר����ɾ��һ����Ŀ���û���
	public HashMap<String, Object> deletProgram(int proId) {
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		proDao.deleteById(proId);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. ��һ��ר���¸���һ����Ŀ���û�������������
	public HashMap<String, Object> updProgram(Program pro) {
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		proDao.upDate(pro);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("Program",pro);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. (λ�� follow Service)�ղ�ĳ����Ŀ(���ݽ�Ŀid���û�id)���û��ղ�(�û�)
	public void followProgram(int id ) {
		this.proAddOne(id, 2);
	}
	//	5. (λ�� follow Service)��ȡĳ���û�(�����û�id)�ղص����н�Ŀ(�û�)
	//	6. ��ȡĳר�������н�Ŀ����ID/����ʱ�䵹��
	public HashMap<String, Object> getProgramsInAlbum(int aId,int pagNum,int limit){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		//��ȡר����Ľ�Ŀ�б�������page
		Page page = new Page(limit);
		page.setList(proDao.getListByAlbumId(aId));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("Programs",page.getPartList());
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	7. ��ȡĳ����̨���������Խ�Ŀ����ID/����ʱ�䵹��
	public HashMap<String, Object> getProgramsInRadCat(int rId,int pagNum,int limit){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		//��ȡר����Ľ�Ŀ�б�������page
		Page page = new Page(limit);
		page.setList(proDao.getListByAdvCatId(rId));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("Programs",page.getPartList());
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	8. ��ȡĳ����Ŀ
	public HashMap<String, Object> browseProgram(int id ) {
		return this.proAddOne(id, 1);
	}
	//	9. ����ĳ����Ŀ
	public HashMap<String, Object> praiseProgram(int id ) {
		return this.proAddOne(id, 0);
	}
	//��һ����
	public HashMap<String, Object> proAddOne(int id ,int type) {
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Program pro = proDao.getById(id);
		if(type ==0) {
			pro.setPraise(pro.getPraise()+1);
		}else if(type==1) {
			pro.setBrowse(pro.getBrowse()+1);
		}else if(type==2) {
			pro.setFollow(pro.getFollow()+1);
		}
		this.updProgram(pro);
		Map message =new HashMap<String, Object>(1){{
			put("Program",pro);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//ͨ��id ��ȡ��Ŀ����
	public Program getProgram(int id ) {
		Session session = sessionFactory.openSession();
		Program  pro =proDao.getById(id);
		session.close();
		return pro;
	}
	//���½�Ŀ����
	public Program getProgram(Program pro ) {
		Session session = sessionFactory.openSession();
		proDao.upDate(pro);
		session.close();
		return pro;
	}
	
	/*ר���Ĳ���-------------------------------------------------------------------*/
	//	1. ��һ����̨��������ӣ�������һ��ר�����û���/addalbum
	public HashMap<String, Object> addAlbum(Album album){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		albumDao.save(album);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("album",album);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	2. ��һ����̨������ɾ��һ��ר�����û���/delealbum
	public HashMap<String, Object> delAlbum(int id ) {
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		//ɾ����ר���µĽ�Ŀ
		List<Program> proList = proDao.getListByAlbumId(id);
		proDao.deleteList(proList);
		//ɾ����ר��
		albumDao.deleteById(id);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. ��һ����̨�����¸���һ��ר�����û������������桢�������ֵȲ���/updalbum
	public HashMap<String, Object> updAlbum(Album album){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		albumDao.upDate(album);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{
			put("album",album);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	4. ��ȡһ����̨�����µ�����ר������ID/����ʱ�䵹��/getallalbum
	public HashMap<String, Object>  getAlbumList(int advCatid,int pageNum,int limit) {
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		List<Album> albumList = albumDao.getList(advCatid);
		session.close();
		Page page = new Page(limit);
		if(albumList==null) {
			statusCode = "100005";
			desc = "���ݿ��б��ȡʧ��";
		}else {
			page.setList(albumList);
			page.setCurrentPageNum(pageNum);
		}
		Map message =new HashMap<String, Object>(1)
			{{put("albumList",page.getPartList());
			}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//��ȡһ��ר��
	public Album getAlbum(int id ) {
		Session session = sessionFactory.openSession();
		Album alb = albumDao.getById(id);
		session.close();
		return alb;
	}
	/*��̨����Ĳ���-------------------------------------------------------------------*/
	//��ȡһ����̨���� by id 
	public AdvCategory getRadCat(int id ) {
		Session session = sessionFactory.openSession();
		AdvCategory adv = advCatDao.getById(id);
		session.close();
		return adv;
	}
	//��ȡ���е�̨����
	public HashMap<String, Object> getAdvCatList(int pageNum,int limit){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		List<AdvCategory> advCatList = advCatDao.getRadioList();
		session.close();
		Page page = new Page(limit);
		if(advCatList==null) {
			statusCode = "100005";
			desc = "���ݿ��б��ȡʧ��";
		}else {
			page.setList(advCatList);
			page.setCurrentPageNum(pageNum);
		}
		Map message =new HashMap<String, Object>(2)
			{{put("advCatList",page.getPartList());
			  put("imgUrl","F:\\Documents\\GitHub\\XunMilingnan\\XunMiLingNan\\XunMilingnan\\WebContent\\img\\");
			}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//���һ����̨����
	public HashMap<String, Object> addRadCat(AdvCategory advCat) {
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�д���
		Session session = sessionFactory.openSession();
		advCatDao.save(advCat);
		session.close();
		Map message =new HashMap<String, Object>(1)
		{{put("advCat",advCat);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//ɾ��һ����̨����
	public HashMap deleteRadCat(int id ) {
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ������
		Session session = sessionFactory.openSession();
		advCatDao.deleteById(id);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//����һ����̨����
	public HashMap updRadCat(AdvCategory adv) {
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ������
		Session session = sessionFactory.openSession();
		advCatDao.upDate(adv);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("AdvCategory",adv);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
}
