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
//			throw new RecruitException(ErrorsInfo.SYS_ERROR, "ϵͳ�쳣��ȱ��openId URL����", "ϵͳ�쳣");
//		}
//		String params = "&code=" + code;
//		url = url + params;
//		JSONObject json = HttpClientTools.doGet(url);
//		logger.error("΢�ŷ���========================" + json + "==========================");
//		logger.error("΢�ŷ���========================" + json + "==========================");
//		logger.error("΢�ŷ���========================" + json + "==========================");	
//		/*if (json != null) {
//			openId = json.getString("openid");
//			if (openId == null) {
//				throw new RecruitException(ErrorsInfo.ERR00003, "΢�Ŵ��󣬻�ȡ����openId", "΢�Ŵ���");
//			}
//		}
//		String src = json.toString();*/
//		return json;
//	}
//	doget����
///**
// * Http Client �Զ��幤����
// * @author kail.huang
// */
//public class HttpClientTools {
//
//
//	private static Logger logger = Logger.getLogger(HttpClientTools.class);
//
//
//	/**
//	 * Http Get ���󷽷�
//	 * @param url
//	 * @return
//	 * @throws Exception
//	 */
//	public static JSONObject doGet(String url) throws Exception {
//
//
//		JSONObject json = null;
//		HttpGet httpGet = new HttpGet(url);
//		// ����httpClientBuilder
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
//			throw new RecruitException(ErrorsInfo.ERR00001, "HttpClient �����쳣", "HttpClient �����쳣��������־��");
//		} catch (IOException e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			throw new RecruitException(ErrorsInfo.ERR00002, "IO ��ȡ�쳣", "IO ��ȡ�쳣��������־��");
//		}
//
//
//		return json;
//	}
//	/**wechat.userInfo.url
//	 *  ��ȡ΢���û�������Ϣ
//	 * @param openId
//	 * @param userId 
//	 * @return
//	 */
//	public static SnsUserinfoVO snsUserinfoVO(String openId,String accessToken, long userId) throws Exception{
//		SnsUserinfoVO snsUserInfo = null;
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
//		String url = (String)PropertiesHandler.getConfigValue("wechat.userInfo.url").toString();
//		if (url == null || "".equals(url)) {
//			throw new RecruitException(ErrorsInfo.SYS_ERROR, "ϵͳ�쳣��ȱ�ٻ�ȡ΢��userInfo URL����", "ϵͳ�쳣");
//		}
//		String params = "access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
//		url = url + params;
//		logger.error("���ýӿ�url========================" + url + "==========================");	
//		JSONObject jsonObject = HttpClientTools.doGet(url);
//		logger.error("���ýӿڻ�ȡ΢���û�������Ϣ ΢�ŷ��ز���========================" + jsonObject + "==========================");	
//		 snsUserInfo = new SnsUserinfoVO();
//         // �û��ı�ʶ
//         snsUserInfo.setOpenId(jsonObject.getString("openid"));
//         // �ǳ�
//         snsUserInfo.setNickName(jsonObject.getString("nickname"));
//         // �Ա�1�����ԣ�2��Ů�ԣ�0��δ֪��
//         snsUserInfo.setSex(jsonObject.getInt("sex"));
//         // �û����ڹ���
//         snsUserInfo.setCountry(jsonObject.getString("country"));
//         // �û�����ʡ��
//         snsUserInfo.setProvince(jsonObject.getString("province"));
//         // �û����ڳ���
//         snsUserInfo.setCity(jsonObject.getString("city"));
//         // �û�ͷ��
//         snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));   
//         //�ύʱ��
//         snsUserInfo.setInputTime(df.format(new Date()));
//         //�޸�ʱ�� 
//         snsUserInfo.setModifyTime(df.format(new Date()));
//         //�ύ��
//         snsUserInfo.setInputBy(userId);
//         //�޸���
//         snsUserInfo.setModifyBy(userId);
//         //״̬
//         snsUserInfo.setStatus(1);
//		return snsUserInfo;
//	}