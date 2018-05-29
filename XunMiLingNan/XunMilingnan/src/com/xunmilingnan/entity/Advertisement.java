package com.xunmilingnan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

//广告
@Entity
public class Advertisement {
	private int id;
	private AdvCategory adCategory;//广告类型
	private String title;
	private String img;
	private String url;
	private int sortNumber;//排序编号   用于排序，编号越大，排越前，如果排序编号相同，则根据id大小进行排序

	
	public Advertisement() {}
	
	public Advertisement(AdvCategory adCategory, String title, String img, String url,int sortNumber) {
		this.adCategory = adCategory;
		this.title = title;
		this.img = img;
		this.url = url;
		this.sortNumber = sortNumber;
	}
	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")	
	@Column(name="ad_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="ad_caid")
	public AdvCategory getAdCategory() {
		return adCategory;
	}

	public void setAdCategory(AdvCategory adCategory) {
		this.adCategory = adCategory;
	}
	@Column(name="ad_title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="ad_img")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	@Column(name="ad_url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="ad_sortNumber")
	public int getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}
	
	
	
	
}
