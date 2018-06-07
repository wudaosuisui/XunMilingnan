//package com.xunmilingnan.service;
//
//import com.alibaba.fastjson.JSONObject;
////import com.sun.istack.internal.logging.Logger;
//
//public class WechatTools {
//	private static Logger logger = Logger.getLogger(WechatTools.class);
//	
//	public static JSONObject queryOpenId(String code) throws Exception {		
//		//String openId = "";
//		String url = (String)PropertiesHandler.getConfigValue("wechat.openid.url").toString();
//		if (url == null || "".equals(url)) {
//			throw new RecruitException(ErrorsInfo.SYS_ERROR, "系统异常，缺少openId URL配置", "系统异常");
//		}
//		String params = "&code=" + code;
//		url = url + params;
//		JSONObject json = HttpClientTools.doGet(url);
//		logger.error("微信返回========================" + json + "==========================");
//		logger.error("微信返回========================" + json + "==========================");
//		logger.error("微信返回========================" + json + "==========================");	
//		/*if (json != null) {
//			openId = json.getString("openid");
//			if (openId == null) {
//				throw new RecruitException(ErrorsInfo.ERR00003, "微信错误，获取不到openId", "微信错误");
//			}
//		}
//		String src = json.toString();*/
//		return json;
//	}
//	doget方法
///**
// * Http Client 自定义工具类
// * @author kail.huang
// */
//public class HttpClientTools {
//
//
//	private static Logger logger = Logger.getLogger(HttpClientTools.class);
//
//
//	/**
//	 * Http Get 请求方法
//	 * @param url
//	 * @return
//	 * @throws Exception
//	 */
//	public static JSONObject doGet(String url) throws Exception {
//
//
//		JSONObject json = null;
//		HttpGet httpGet = new HttpGet(url);
//		// 创建httpClientBuilder
//		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
//		// httpclient
//		CloseableHttpClient httpClient = clientBuilder.build();
//
//
//		try {
//			HttpResponse httpResponse = httpClient.execute(httpGet);
//			HttpEntity entity = httpResponse.getEntity();
//			if (entity != null) {
//				String result = EntityUtils.toString(entity, "UTF-8");
//				json = JSONObject.fromObject(result);
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			throw new RecruitException(ErrorsInfo.ERR00001, "HttpClient 请求异常", "HttpClient 请求异常，请检查日志！");
//		} catch (IOException e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			throw new RecruitException(ErrorsInfo.ERR00002, "IO 读取异常", "IO 读取异常，请检查日志！");
//		}
//
//
//		return json;
//	}
//	/**wechat.userInfo.url
//	 *  获取微信用户个人信息
//	 * @param openId
//	 * @param userId 
//	 * @return
//	 */
//	public static SnsUserinfoVO snsUserinfoVO(String openId,String accessToken, long userId) throws Exception{
//		SnsUserinfoVO snsUserInfo = null;
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		String url = (String)PropertiesHandler.getConfigValue("wechat.userInfo.url").toString();
//		if (url == null || "".equals(url)) {
//			throw new RecruitException(ErrorsInfo.SYS_ERROR, "系统异常，缺少获取微信userInfo URL配置", "系统异常");
//		}
//		String params = "access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
//		url = url + params;
//		logger.error("调用接口url========================" + url + "==========================");	
//		JSONObject jsonObject = HttpClientTools.doGet(url);
//		logger.error("调用接口获取微信用户个人信息 微信返回参数========================" + jsonObject + "==========================");	
//		 snsUserInfo = new SnsUserinfoVO();
//         // 用户的标识
//         snsUserInfo.setOpenId(jsonObject.getString("openid"));
//         // 昵称
//         snsUserInfo.setNickName(jsonObject.getString("nickname"));
//         // 性别（1是男性，2是女性，0是未知）
//         snsUserInfo.setSex(jsonObject.getInt("sex"));
//         // 用户所在国家
//         snsUserInfo.setCountry(jsonObject.getString("country"));
//         // 用户所在省份
//         snsUserInfo.setProvince(jsonObject.getString("province"));
//         // 用户所在城市
//         snsUserInfo.setCity(jsonObject.getString("city"));
//         // 用户头像
//         snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));   
//         //提交时间
//         snsUserInfo.setInputTime(df.format(new Date()));
//         //修改时间 
//         snsUserInfo.setModifyTime(df.format(new Date()));
//         //提交者
//         snsUserInfo.setInputBy(userId);
//         //修改者
//         snsUserInfo.setModifyBy(userId);
//         //状态
//         snsUserInfo.setStatus(1);
//		return snsUserInfo;
//	}