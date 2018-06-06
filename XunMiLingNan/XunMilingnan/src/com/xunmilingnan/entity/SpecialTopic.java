package com.xunmilingnan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

//活动专题
@Entity
public class SpecialTopic {
	
	private int id;
	private AdvCategory SpeTopCat;
	private String title;//标题
	private Date startTime;//开始时间
	private Date endTime;//截止时间
	private String article;//活动内容（宣传的文字）
	private String img;//活动宣传图片（海报）
	private int browse;//关注量
	private Date showTime;//发布时间
	private int isHot;//热门  0为非热门专题，1为热门专题
	private int sort;//排序编号
	
	//空构造
	public SpecialTopic() {}
	//基本全构造
	public SpecialTopic(AdvCategory speTopCat, String title, Date startTime, Date endTime, String article, String img,
			Date showTime, int isHot, int sort) {
		SpeTopCat = speTopCat;
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.article = article;
		this.img = img;
		browse = 0;
		this.showTime = showTime;
		this.isHot = isHot;
		this.sort = sort;
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
	
	@ManyToOne
	@JoinColumn(name="ac_stcat")
	public AdvCategory getSpeTopCat() {
		return SpeTopCat;
	}
	public void setSpeTopCat(AdvCategory speTopCat) {
		SpeTopCat = speTopCat;
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
	@Column(name="ac_browse")
	public int getBrowse() {
		return browse;
	}
	public void setBrowse(int browse) {
		this.browse = browse;
	}
	@Column(name="ac_showTime")
	public Date getShowTime() {
		return showTime;
	}
	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}
	@Column(name="ac_isHot")
	public int getIsHot() {
		return isHot;
	}
	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}
	@Column(name="ac_sort")
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
	
	
}
