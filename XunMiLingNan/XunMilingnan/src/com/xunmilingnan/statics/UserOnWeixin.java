package com.xunmilingnan.statics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class UserOnWeixin {

//	
//	/**
//	 * ��ȡ΢��С���� session_key �� openid
//	 *
//	 * @param code ����΢�ŵ�½���ص�Code
//	 * @return
//	 */
//	public static JSONObject getSessionKeyOropenid(String code) {
//	    //΢�Ŷ˵�¼codeֵ
//	    String wxCode = code;
//	    Locale locale = new Locale("en", "US");
//	    ResourceBundle resource = ResourceBundle.getBundle("config/wx-config",locale);   //��ȡ�����ļ�
//	    String requestUrl = resource.getString("url");  //�����ַ https://api.weixin.qq.com/sns/jscode2session
//	    Map<String, String> requestUrlParam = new HashMap<String, String>();
//	    requestUrlParam.put("appid", resource.getString("appId"));  //�����������е�appId
//	    requestUrlParam.put("secret", resource.getString("appSecret")); //�����������е�appSecret
//	    requestUrlParam.put("js_code", wxCode); //С�������wx.login���ص�code
//	    requestUrlParam.put("grant_type", resource.getString("grantType"));    //Ĭ�ϲ��� authorization_code
//	 
//	    //����post�����ȡ����΢�� https://api.weixin.qq.com/sns/jscode2session �ӿڻ�ȡopenid�û�Ψһ��ʶ
//	    JSONObject jsonObject = JSON.parseObject(sendPost(requestUrl, requestUrlParam));
//	    return jsonObject;
//	}
//	 
//	/**
//	 * ��ָ�� URL ����POST����������
//	 *
//	 * @param url ��������� URL
//	 * @return ������Զ����Դ����Ӧ���
//	 */
//	public static String sendPost(String url, Map<String, ?> paramMap) {
//	    PrintWriter out = null;
//	    BufferedReader in = null;
//	    String result = "";
//	 
//	    String param = "";
//	    Iterator<String> it = paramMap.keySet().iterator();
//	 
//	    while (it.hasNext()) {
//	        String key = it.next();
//	        param += key + "=" + paramMap.get(key) + "&";
//	    }
//	 
//	    try {
//	        URL realUrl = new URL(url);
//	        // �򿪺�URL֮�������
//	        URLConnection conn = realUrl.openConnection();
//	        // ����ͨ�õ���������
//	        conn.setRequestProperty("accept", "*/*");
//	        conn.setRequestProperty("connection", "Keep-Alive");
//	        conn.setRequestProperty("Accept-Charset", "utf-8");
//	        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//	        // ����POST�������������������
//	        conn.setDoOutput(true);
//	        conn.setDoInput(true);
//	        // ��ȡURLConnection�����Ӧ�������
//	        out = new PrintWriter(conn.getOutputStream());
//	        // �����������
//	        out.print(param);
//	        // flush������Ļ���
//	        out.flush();
//	        // ����BufferedReader����������ȡURL����Ӧ
//	        in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//	        String line;
//	        while ((line = in.readLine()) != null) {
//	            result += line;
//	        }
//	    } catch (Exception e) {
////	        log.error(e.getMessage(), e);
//	    }
//	    //ʹ��finally�����ر��������������
//	    finally {
//	        try {
//	            if (out != null) {
//	                out.close();
//	            }
//	            if (in != null) {
//	                in.close();
//	            }
//	        } catch (IOException ex) {
//	            ex.printStackTrace();
//	        }
//	    }
//	    return result;
//	}
//	 
//	 /**
//	     * �����û��������ݻ�ȡ�û���Ϣ
//	     *
//	     * @param sessionKey    ���ݽ��м���ǩ������Կ
//	     * @param encryptedData ���������������ڵ������û���Ϣ�ļ�������
//	     * @param iv            �����㷨�ĳ�ʼ����
//	     * @return
//	     * */
//	    public static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) {
//	        // �����ܵ�����
//	        byte[] dataByte = Base64Util.decodeByte(encryptedData);
//	        // ������Կ
//	        byte[] keyByte = Base64Util.decodeByte(sessionKey);
//	        // ƫ����
//	        byte[] ivByte = Base64Util.decodeByte(iv);
//	        try {
//	 
//	            // �����Կ����16λ����ô�Ͳ���.  ���if �е����ݺ���Ҫ
//	            int base = 16;
//	            if (keyByte.length % base != 0) {
//	                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
//	                byte[] temp = new byte[groups * base];
//	                Arrays.fill(temp, (byte) 0);
//	                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
//	                keyByte = temp;
//	            }
//	            // ��ʼ��
//	            Security.addProvider(new BouncyCastleProvider());
//	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
//	            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
//	            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
//	            parameters.init(new IvParameterSpec(ivByte));
//	            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// ��ʼ��
//	            byte[] resultByte = cipher.doFinal(dataByte);
//	            if (null != resultByte && resultByte.length > 0) {
//	                String result = new String(resultByte, "UTF-8");
//	                return JSON.parseObject(result);
//	            }
//	        } catch (NoSuchAlgorithmException e) {
//	            log.error(e.getMessage(), e);
//	        } catch (NoSuchPaddingException e) {
//	            log.error(e.getMessage(), e);
//	        } catch (InvalidParameterSpecException e) {
//	            log.error(e.getMessage(), e);
//	        } catch (IllegalBlockSizeException e) {
//	            log.error(e.getMessage(), e);
//	        } catch (BadPaddingException e) {
//	            log.error(e.getMessage(), e);
//	        } catch (UnsupportedEncodingException e) {
//	            log.error(e.getMessage(), e);
//	        } catch (InvalidKeyException e) {
//	            log.error(e.getMessage(), e);
//	        } catch (InvalidAlgorithmParameterException e) {
//	            log.error(e.getMessage(), e);
//	        } catch (NoSuchProviderException e) {
//	            log.error(e.getMessage(), e);
//	        }
//	        return null;
//	    }
}
