package com.xunmilingnan.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xunmilingnan.dao.ArticleDao;
import com.xunmilingnan.dao.FollowDao;
import com.xunmilingnan.dao.GroupDao;
import com.xunmilingnan.dao.NewsDao;
import com.xunmilingnan.dao.ProgramDao;
import com.xunmilingnan.dao.UserDao;
import com.xunmilingnan.entity.AdvCategory;
import com.xunmilingnan.entity.Article;
import com.xunmilingnan.entity.Follow;
import com.xunmilingnan.entity.Group;
import com.xunmilingnan.entity.News;
import com.xunmilingnan.entity.Program;
import com.xunmilingnan.entity.User;
import com.xunmilingnan.statics.HttpClientTool;
import com.xunmilingnan.statics.Page;
import com.xunmilingnan.statics.Result;

@Service
@Repository//�й��û�
public class UserService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource
	private UserDao usDao;
	@Resource
	private GroupDao grDao;
	@Resource
	private ArticleDao arDao;
	@Resource
	private FollowDao foDao;
	@Resource
	private NewsDao neDao;
	@Resource
	private ProgramDao prDao;
	
	
	private static String  url= "https://api.weixin.qq.com/sns/jscode2session?";//&js_code=JSCODE
	private static String appid = "&appid=wx2aa0d2c4ba67c9c7";
	private static String secret = "&secret=ec7abc04967536d55102800563932d09";
	private static String grant_type = "&grant_type=authorization_code";
	private static String js_code = new String();
	
	//https://api.weixin.qq.com/sns/jscode2session?
	//appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
	//����url ��̬����
	public static String getURLContent(String urlStr) {                 
        
        //�����url   
        URL url = null;        
          
        //������http����    
        HttpURLConnection httpConn = null;    
          
        //�����������  
        BufferedReader in = null;     
          
        //�������Ļ���  
        StringBuffer sb = new StringBuffer();   
          
        try{       
             url = new URL(urlStr);       
               
             in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8") );   
               
             String str = null;    
              
             //һ��һ�н��ж���  
             while((str = in.readLine()) != null) {      
                sb.append( str );       
             }       
        } catch (Exception ex) {     
                  
        } finally{      
             try{               
                  if(in!=null) {    
                   in.close(); //�ر���      
                  }       
            }catch(IOException ex) {        
              
            }       
        }       
        String result =sb.toString();       
        return result;      
    }    

	//��¼
	public HashMap<String, Object> login(String code){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		Map message =new HashMap<String, Object>(1);
		//ִ�в���
		js_code = "&js_code="+code;
		String Url = url +appid+secret+grant_type+js_code;//��code����url
		Map pr =new  HashMap<String, String>(0);//һ���յ�json������
		String jsoStr = HttpClientTool.doGet(Url,pr);//��ȡ����ֵjson String
		JSONObject jsonObject = JSONArray.parseObject(jsoStr);//��  json String -�� json 
		String openid = jsonObject.getString("openid");
		String session_key = jsonObject.getString("session_key");
		if(openid==null) {
			statusCode = "130001";
			desc ="openid��ȡʧ��";
		}else {
			Session session = sessionFactory.openSession();
//			System.out.println("beful get user");
			User  user = usDao.getByOpenid(openid);//����ͨ��unionid��ȡuser  jsonObject.getString("openid")
//			System.out.println("get user success");
			if(user==null) {//���userΪ��   ִ��ע�����  ���򷵻�user����
//				System.out.println("get if success");
				statusCode = "130000";
				desc ="���û�Ϊ�״ε�¼";
				user = this.register(openid, session_key);
				usDao.save(user);
			}
			session.close();
			message.put("user",user);
		}
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
	public User register(String openid ,String session_key) {
		User user = new User();
		Group gro = grDao.getBySign("1");
		user.setOpenid(openid);
		user.setSession_key(session_key);
		user.setGro(gro);
		return user;
	}
	public HashMap<String, Object> updateUser(User user){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		Group gro = grDao.getBySign("0");
		user.setGro(gro);
		usDao.upDate(user);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("user",user);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
//	public Group getGroup(String si) {
//		Group gro = grDao.getBySign("0");
//	}
	//ע��
//	public User register(String openid ,String session_key) {
//		String url1 = "https://api.weixin.qq.com/cgi-bin/grant_type=client_credential&appid=wx2aa0d2c4ba67c9c7 &secret=ec7abc04967536d55102800563932d09 ";//token?
//		
//		Map pr =new  HashMap<String, String>(0);//һ���յ�json������
//		String jsoStr = HttpClientTool.doGet(url1,pr);//��ȡ����ֵjson String
//		JSONObject jsonObject = JSONArray.parseObject(jsoStr);//��  json String -�� json 
//		
//		String accessToken =jsonObject.getString("access_token");
//		String params = "access_token="+accessToken+"&openid="+openid+"&lang=zh_CN";
//		String Url = url + params;
//		jsoStr = HttpClientTool.doGet(Url,pr);//��ȡ����ֵjson String
//		jsonObject = JSONArray.parseObject(jsoStr);//��  json String -�� json 
//		Group gro = grDao.getBySign("0");
//		Date time = new Date();
//		User user = new User(gro,//�û���
//				session_key,//key
//				openid,
//				jsonObject.getString("nickname"),//�û���
//				jsonObject.getString("headimgurl"),//ͷ��
//				"token",
//				jsonObject.getString("unionid"),//Ψһ��ʶ��
//				jsonObject.getString("city"),//����
//				jsonObject.getString("province"),//ʡ��
//				jsonObject.getString("country"),//����
//				jsonObject.getString("language"),//����(δȷ��)
//				(Integer)jsonObject.get("sex"),//�Ա�
//				"000000000",//Ԥ���ַ�
//				"000000000",//�绰
//				time//��������
//				);
//		usDao.save(user);
//		return user;
//		
//	}
	
	//	1. �鿴������ҳ
	public HashMap<String, Object> homePage(int uId){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		User user = usDao.getById(uId);
		int countAr = arDao.getCountInU(uId);//�ҵ�����
		//��ע
		int countFo = foDao.getCountByUT(uId, 2);
		//��˿
		int counBfo = foDao.getCountByUT(uId, 2);
		//��Ϣ
		int countNe = neDao.getCountInU(uId);
		//�ղص�����
		int countFar = foDao.getCountByUT(uId, 0);
		//�ղصĵ�̨����Ŀ��
		int countFpr = foDao.getCountByFT(uId, 1);
		session.close();
		Map message =new HashMap<String, Object>(7){{
			put("user",user);
			put("redAr",countAr);
			put("followUser",countFo);
			put("beFollow",counBfo);
			put("news",countNe);
			put("followAr",countFar);
			put("followPr",countFpr);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
		
	//	2. �޸ĸ�������
	public HashMap<String, Object> changeUserProfile(int uId,String text){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		User user = usDao.getById(uId);
		user.setUserProfile(text);
		usDao.upDate(user);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("user",user);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. ��ע�����û����򱻹�ע���û�����һ����Ϣ��
	public HashMap<String, Object> followUser(int uId,int fuId){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		//��ȡuser
		User user = usDao.getById(uId);
		//��ȡ����עuser
		User fuser = usDao.getById(fuId);
		//����һ��follow
		Follow fo = new Follow(user,2,fuId);
		//����һ��news
		News ne = new News(2,fuser,"�û�"+user.getUserName()+"��ע����",uId);
		//����
		foDao.save(fo);
		neDao.save(ne);
		session.close();
		Map message =new HashMap<String, Object>(2){{
			put("follow",fo);
			put("news",ne);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
		
		
	//	4. ��ȡ���û���ע�������û�
	public HashMap<String, Object> followList(int uId,int pagNum,int limit,int type){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		//��ȡ���û���ע���û��б�
		List<Follow> fl = foDao.getListByUT(uId, type);
		Page page = new Page(limit);
		page.setList(usDao.getUserListByFL(fl));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("followUserList",page.getPartList());
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	5. �鿴��ע��ĳ���û�(ͬ1)
	//	6. �鿴��ע�Լ��ķ�˿
	public HashMap<String, Object> beFollowList(int uId,int pagNum,int limit,int type){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		//��ȡ���û���ע���û��б�
		List<Follow> fl = foDao.getListByFT(uId, type);
		Page page = new Page(limit);
		page.setList(usDao.getUserListByFL(fl));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("followUserList",page.getPartList());
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	7. �鿴�ղص����£��˽ӿ�λ������ģ�飩
	//	8. �鿴�ղص�ר�⣨ר��ģ�飩
	//	9. �鿴�ղص�ר������̨-ר��ģ�飩
	//	10. �鿴�Լ�д�������б�����ģ�飩
	//	11. �鿴�Լ��������¼��ÿ����������¼��10��������10��������ɵģ�����ɸѡ����Ĺ��ܣ�
	public HashMap<String, Object> getBrowseList(int uId){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		//��ȡ���������¼
		List<Follow> afl = foDao.getListByFT(uId, 4);
		List<Article> al = arDao.getListByFollowList(afl);
		//��ȡ��Ŀ�����¼
		List<Follow> pfl = foDao.getListByFT(uId, 5);
		List<Program> pl = prDao.getListByFollowList(pfl);
		session.close();
		Map message =new HashMap<String, Object>(2){{
			put("browseArticleList",al);
			put("browseProgramList",pl);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	12. ɾ��ĳһ�������¼
	public HashMap<String, Object> delBrowse(int fId){
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		foDao.deleteById(fId);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	13. ����Լ��������¼(���з��࣬���ĳ������������¼)
	public HashMap<String, Object> delBrowseList(int uId,int type){//type 4 - ���������¼��5- ��Ŀ�����¼
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		foDao.deleteListByUT(uId,type);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	public User getUserById(int id ) {
		Session session = sessionFactory.openSession();
		User use = usDao.getById(id);
		session.close();
		return use;
	}
	
	//addGroup
	public HashMap<String, Object> addGroup(Group gro){//type 4 - ���������¼��5- ��Ŀ�����¼
		//����ֵ
		Result result = new Result();
		String statusCode =result.getStatusCode();//״̬��
		String desc = result.getDesc();//״̬������
		//ִ�в���
		Session session = sessionFactory.openSession();
		grDao.save(gro);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("group",gro);
		}};
		//���뷵��ֵ
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
}


//public HashMap<String, Object> test(){
//	//����ֵ
//	Result result = new Result();
//	String statusCode =result.getStatusCode();//״̬��
//	String desc = result.getDesc();//״̬������
//	//ִ�в���
//	Session session = sessionFactory.openSession();
//	String openid = "oPrQA5Zlj81rYhs3YyHquaiwgjsY---";
//	String session_key = "eMtFxyWIHygA2s3YkivawA==";
//	System.out.println("beful get user");
//	User  user = usDao.getByOpenid(openid);//����ͨ��unionid��ȡuser  jsonObject.getString("openid")
//	System.out.println("get user success");
//	if(user==null) {//���userΪ��   ִ��ע�����  ���򷵻�user����
//		System.out.println("get if success");
//		statusCode = "130000";
//		desc ="���û�Ϊ�״ε�¼";
//		Date time = new Date();
//		user = this.register(openid, session_key,time);
//		usDao.save(user);
//	}
//	session.close();
//	Map message =new HashMap<String, Object>(1);
//	message.put("user", user);
//	//���뷵��ֵ
//	result.getResult().put("message", message);
//	result.setStatusCode(statusCode);
//	result.setDesc(desc);
//	return result.getRe();
//}