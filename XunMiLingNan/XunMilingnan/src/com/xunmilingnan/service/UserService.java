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
@Repository//有关用户
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
	//访问url 静态方法
	public static String getURLContent(String urlStr) {                 
        
        //请求的url   
        URL url = null;        
          
        //建立的http链接    
        HttpURLConnection httpConn = null;    
          
        //请求的输入流  
        BufferedReader in = null;     
          
        //输入流的缓冲  
        StringBuffer sb = new StringBuffer();   
          
        try{       
             url = new URL(urlStr);       
               
             in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8") );   
               
             String str = null;    
              
             //一行一行进行读入  
             while((str = in.readLine()) != null) {      
                sb.append( str );       
             }       
        } catch (Exception ex) {     
                  
        } finally{      
             try{               
                  if(in!=null) {    
                   in.close(); //关闭流      
                  }       
            }catch(IOException ex) {        
              
            }       
        }       
        String result =sb.toString();       
        return result;      
    }    

	//登录
	public HashMap<String, Object> login(String code){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		Map message =new HashMap<String, Object>(1);
		//执行操作
		js_code = "&js_code="+code;
		String Url = url +appid+secret+grant_type+js_code;//将code存入url
		Map pr =new  HashMap<String, String>(0);//一个空的json参数表
		String jsoStr = HttpClientTool.doGet(Url,pr);//获取返回值json String
		JSONObject jsonObject = JSONArray.parseObject(jsoStr);//将  json String -》 json 
		String openid = jsonObject.getString("openid");
		String session_key = jsonObject.getString("session_key");
		if(openid==null) {
			statusCode = "130001";
			desc ="openid获取失败";
		}else {
			Session session = sessionFactory.openSession();
//			System.out.println("beful get user");
			User  user = usDao.getByOpenid(openid);//尝试通过unionid获取user  jsonObject.getString("openid")
//			System.out.println("get user success");
			if(user==null) {//如果user为空   执行注册操作  否则返回user即可
//				System.out.println("get if success");
				statusCode = "130000";
				desc ="此用户为首次登录";
				user = this.register(openid, session_key);
				usDao.save(user);
			}
			session.close();
			message.put("user",user);
		}
		//存入返回值
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
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		Group gro = grDao.getBySign("0");
		user.setGro(gro);
		usDao.upDate(user);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("user",user);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
//	public Group getGroup(String si) {
//		Group gro = grDao.getBySign("0");
//	}
	//注册
//	public User register(String openid ,String session_key) {
//		String url1 = "https://api.weixin.qq.com/cgi-bin/grant_type=client_credential&appid=wx2aa0d2c4ba67c9c7 &secret=ec7abc04967536d55102800563932d09 ";//token?
//		
//		Map pr =new  HashMap<String, String>(0);//一个空的json参数表
//		String jsoStr = HttpClientTool.doGet(url1,pr);//获取返回值json String
//		JSONObject jsonObject = JSONArray.parseObject(jsoStr);//将  json String -》 json 
//		
//		String accessToken =jsonObject.getString("access_token");
//		String params = "access_token="+accessToken+"&openid="+openid+"&lang=zh_CN";
//		String Url = url + params;
//		jsoStr = HttpClientTool.doGet(Url,pr);//获取返回值json String
//		jsonObject = JSONArray.parseObject(jsoStr);//将  json String -》 json 
//		Group gro = grDao.getBySign("0");
//		Date time = new Date();
//		User user = new User(gro,//用户组
//				session_key,//key
//				openid,
//				jsonObject.getString("nickname"),//用户名
//				jsonObject.getString("headimgurl"),//头像
//				"token",
//				jsonObject.getString("unionid"),//唯一标识符
//				jsonObject.getString("city"),//城市
//				jsonObject.getString("province"),//省份
//				jsonObject.getString("country"),//国家
//				jsonObject.getString("language"),//语言(未确定)
//				(Integer)jsonObject.get("sex"),//性别
//				"000000000",//预留字符
//				"000000000",//电话
//				time//创建日期
//				);
//		usDao.save(user);
//		return user;
//		
//	}
	
	//	1. 查看个人主页
	public HashMap<String, Object> homePage(int uId){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		User user = usDao.getById(uId);
		int countAr = arDao.getCountInU(uId);//我的文章
		//关注
		int countFo = foDao.getCountByUT(uId, 2);
		//粉丝
		int counBfo = foDao.getCountByUT(uId, 2);
		//消息
		int countNe = neDao.getCountInU(uId);
		//收藏的文章
		int countFar = foDao.getCountByUT(uId, 0);
		//收藏的电台（节目）
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
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
		
	//	2. 修改个人资料
	public HashMap<String, Object> changeUserProfile(int uId,String text){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		User user = usDao.getById(uId);
		user.setUserProfile(text);
		usDao.upDate(user);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("user",user);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	3. 关注其他用户（向被关注的用户发送一条消息）
	public HashMap<String, Object> followUser(int uId,int fuId){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		//获取user
		User user = usDao.getById(uId);
		//获取被关注user
		User fuser = usDao.getById(fuId);
		//创建一个follow
		Follow fo = new Follow(user,2,fuId);
		//创建一个news
		News ne = new News(2,fuser,"用户"+user.getUserName()+"关注了您",uId);
		//存入
		foDao.save(fo);
		neDao.save(ne);
		session.close();
		Map message =new HashMap<String, Object>(2){{
			put("follow",fo);
			put("news",ne);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
		
		
	//	4. 获取此用户关注的所有用户
	public HashMap<String, Object> followList(int uId,int pagNum,int limit,int type){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		//获取此用户关注的用户列表
		List<Follow> fl = foDao.getListByUT(uId, type);
		Page page = new Page(limit);
		page.setList(usDao.getUserListByFL(fl));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("followUserList",page.getPartList());
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	5. 查看关注的某个用户(同1)
	//	6. 查看关注自己的粉丝
	public HashMap<String, Object> beFollowList(int uId,int pagNum,int limit,int type){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		//获取此用户关注的用户列表
		List<Follow> fl = foDao.getListByFT(uId, type);
		Page page = new Page(limit);
		page.setList(usDao.getUserListByFL(fl));
		page.setCurrentPageNum(pagNum);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("followUserList",page.getPartList());
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	7. 查看收藏的文章（此接口位于文章模块）
	//	8. 查看收藏的专题（专题模块）
	//	9. 查看收藏的专辑（电台-专辑模块）
	//	10. 查看自己写的文章列表（文章模块）
	//	11. 查看自己的浏览记录（每种浏览分类记录仅10条，超出10条顶掉最旧的，带有筛选分类的功能）
	public HashMap<String, Object> getBrowseList(int uId){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		//获取文章浏览记录
		List<Follow> afl = foDao.getListByFT(uId, 4);
		List<Article> al = arDao.getListByFollowList(afl);
		//获取节目浏览记录
		List<Follow> pfl = foDao.getListByFT(uId, 5);
		List<Program> pl = prDao.getListByFollowList(pfl);
		session.close();
		Map message =new HashMap<String, Object>(2){{
			put("browseArticleList",al);
			put("browseProgramList",pl);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	12. 删除某一条浏览记录
	public HashMap<String, Object> delBrowse(int fId){
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		foDao.deleteById(fId);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	//	13. 清空自己的浏览记录(带有分类，清空某个分类的浏览记录)
	public HashMap<String, Object> delBrowseList(int uId,int type){//type 4 - 文章浏览记录；5- 节目浏览记录
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		foDao.deleteListByUT(uId,type);
		session.close();
		Map message =new HashMap<String, Object>(0);
		//存入返回值
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
	public HashMap<String, Object> addGroup(Group gro){//type 4 - 文章浏览记录；5- 节目浏览记录
		//返回值
		Result result = new Result();
		String statusCode =result.getStatusCode();//状态码
		String desc = result.getDesc();//状态码描述
		//执行操作
		Session session = sessionFactory.openSession();
		grDao.save(gro);
		session.close();
		Map message =new HashMap<String, Object>(1){{
			put("group",gro);
		}};
		//存入返回值
		result.getResult().put("message", message);
		result.setStatusCode(statusCode);
		result.setDesc(desc);
		return result.getRe();
	}
	
}


//public HashMap<String, Object> test(){
//	//返回值
//	Result result = new Result();
//	String statusCode =result.getStatusCode();//状态码
//	String desc = result.getDesc();//状态码描述
//	//执行操作
//	Session session = sessionFactory.openSession();
//	String openid = "oPrQA5Zlj81rYhs3YyHquaiwgjsY---";
//	String session_key = "eMtFxyWIHygA2s3YkivawA==";
//	System.out.println("beful get user");
//	User  user = usDao.getByOpenid(openid);//尝试通过unionid获取user  jsonObject.getString("openid")
//	System.out.println("get user success");
//	if(user==null) {//如果user为空   执行注册操作  否则返回user即可
//		System.out.println("get if success");
//		statusCode = "130000";
//		desc ="此用户为首次登录";
//		Date time = new Date();
//		user = this.register(openid, session_key,time);
//		usDao.save(user);
//	}
//	session.close();
//	Map message =new HashMap<String, Object>(1);
//	message.put("user", user);
//	//存入返回值
//	result.getResult().put("message", message);
//	result.setStatusCode(statusCode);
//	result.setDesc(desc);
//	return result.getRe();
//}