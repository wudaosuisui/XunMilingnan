package com.xunmilingnan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

//�
@Entity
public class SpecialTopic {
	
	private int id;
	private String title;//����
	private Date startTime;//��ʼʱ��
	private Date endTime;//��ֹʱ��
	private String article;//����ݣ����������֣�
	private String img;//�����ͼƬ��������
	private int browse;//��ע��
	private Date showTime;//����ʱ��
	private int isHot;//����
	private int sort;//������
	
	//�չ���
	public SpecialTopic() {}
	//����ȫ����
	public SpecialTopic( String title, Date startTime, Date endTime, String article, String img, int browse,
			Date showTime, int isHot, int sort) {
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.article = article;
		this.img = img;
		this.browse = browse;
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
