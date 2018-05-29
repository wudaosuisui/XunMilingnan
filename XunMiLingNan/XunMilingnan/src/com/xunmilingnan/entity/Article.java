package com.xunmilingnan.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Article {//活动
	
	private int id ;
	private SpecialTopic activity;//活动id  多对一
	private User user;//用户（作者）id 多对一
	private Date publishTime;//发表时间
	private String title;//文章标题
	private String text;//文章内容
	private int forward;//收藏量
	private int browse;//浏览量
	private int praise;//点赞量	
	private int sort;//排序编号
	
	public Article(){}

	public Article(SpecialTopic activity, User user, Date publishTime,String title,String text, int browse, int praise, int forward,int sort) {
		this.activity = activity;
		this.user = user;
		this.publishTime = publishTime;
		this.title = title;
		this.text = text;
		this.browse = browse;
		this.praise = praise;
		this.forward = forward;
		this.sort = sort;
	}


	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")	
	@Column(name="ar_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="ar_acid")
	public SpecialTopic getActivity() {
		return activity;
	}


	public void setActivity(SpecialTopic activity) {
		this.activity = activity;
	}


	@ManyToOne
	@JoinColumn(name="ar_usid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="ar_publishtime")
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	@Column(name="ar_text")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	@Column(name="ar_browse")
	public int getBrowse() {
		return browse;
	}

	public void setBrowse(int browse) {
		this.browse = browse;
	}
	@Column(name="ar_praise")
	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}
	@Column(name="ar_forward")
	public int getForward() {
		return forward;
	}
	public void setForward(int forward) {
		this.forward = forward;
	}
	@Column(name="ar_title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="ar_sort")
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
	
	
	
	
}
