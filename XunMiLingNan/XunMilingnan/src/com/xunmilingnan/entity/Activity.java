package com.xunmilingnan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

//活动
@Entity
public class Activity {
	
	private int id;
	private String title;
	private Date startTime;
	private Date endTime;
	private String article;//活动内容
	private String img;
	private int follow;//关注量
	
	//空构造
	public Activity() {}
	//基本全构造
	public Activity( String title, Date startTime, 
			Date endTime, String article, String img, int follow) {
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.article = article;
		this.img = img;
		this.follow = follow;
	}
	
	//setter & getter
	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")	
	@Column(name="ac_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="ac_title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="ac_starttime")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name="ac_endtime")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name="ac_article")
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	@Column(name="ac_img")
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Column(name="ac_follow")
	public int getFollow() {
		return follow;
	}
	public void setFollow(int follow) {
		this.follow = follow;
	}
	
	
	
}
