package com.xunmilingnan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
//@Table(name="user")
public class User {
	private int id ;
	private Group gro;//用户组
	private String session_key;
	private String openid;
	private String userName;//用户名
	private String avatarUrl;//头像
	private String token;//令牌
	private String unionid;//开放平台唯一标识符
	private String city;//城市
	private String province;//省份
	private String country;//国家
	private String language;//语言
	private int  sex;//性别   值为1时是男性，值为2时是女性，值为0时是未知
	private String encryptedData;//预留字段
	private String phoneNumber;//注册手机号
	private String time;//注册时间
	private String userProfile;//用户简介
	//空构造
	public User() {
	}
	//除id 的 全构造
	public User(Group gro, String session_key, String openid,
			String userName, String avatarUrl, String token,
			String unionid, String city, String province,
			String country, String language, int sex,
			String encryptedData, String phoneNumber, String time,
			String userProfile) {
		this.gro = gro;
		this.session_key = session_key;
		this.openid = openid;
		this.userName = userName;
		this.avatarUrl = avatarUrl;
		this.token = token;
		this.unionid = unionid;
		this.city = city;
		this.province = province;
		this.country = country;
		this.language = language;
		this.sex = sex;
		this.encryptedData = encryptedData;
		this.phoneNumber = phoneNumber;
		this.time = time;
		this.userProfile = userProfile;
	}
	//除 id  用户简介的 全构造
	public User(Group gro, String session_key, String openid, String userName, String avatarUrl, String token,
			String unionid, String city, String province, String country, String language, int sex,
			String encryptedData, String phoneNumber, String time) {
		this.gro = gro;
		this.session_key = session_key;
		this.openid = openid;
		this.userName = userName;
		this.avatarUrl = avatarUrl;
		this.token = token;
		this.unionid = unionid;
		this.city = city;
		this.province = province;
		this.country = country;
		this.language = language;
		this.sex = sex;
		this.encryptedData = encryptedData;
		this.phoneNumber = phoneNumber;
		this.time = time;
	}
	public User(int id, String avatarUrl, String city,
			String country, String language,String username,
			String province,int sex
			) {
		this.id = id;
		this.avatarUrl = avatarUrl;
		this.city = city;
		this.country = country;
		this.language = language;
		this.userName = username;
		this.province = province;
		this.sex = sex;
	}
	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="gro")
	public Group getGro() {
		return gro;
	}
	public void setGro(Group gro) {
		this.gro = gro;
	}
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getEncryptedData() {
		return encryptedData;
	}
	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}
	
}
