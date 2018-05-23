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
	private AdvertisementCategory adCategory;//广告类型
	private String title;
	private String img;
	private String url;
	
	public Advertisement() {}
	
	public Advertisement(AdvertisementCategory adCategory, String title, String img, String url) {
		this.adCategory = adCategory;
		this.title = title;
		this.img = img;
		this.url = url;
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
	public AdvertisementCategory getAdCategory() {
		return adCategory;
	}

	public void setAdCategory(AdvertisementCategory adCategory) {
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
	
	
	
	
	
}
