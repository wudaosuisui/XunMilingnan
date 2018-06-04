package com.xunmilingnan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

//电台专辑
@Entity
public class Album {
	private int id ;
	private AdvCategory advCat;
	private User user;
	private String name;
	private Date relTime;//发布时间 releaseTime
	private String img;//封面
	private int follow;//关注量
	
	public Album(){	}
	
	public Album(AdvCategory advCat, User user, String name, Date relTime, String img) {
		this.advCat = advCat;
		this.user = user;
		this.name = name;
		this.relTime = relTime;
		this.img = img;
	}
	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")	
	@Column(name="al_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="al_advcatid")
	public AdvCategory getAdvCat() {
		return advCat;
	}

	public void setAdvCat(AdvCategory advCat) {
		this.advCat = advCat;
	}
	@ManyToOne
	@JoinColumn(name="al_uid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="al_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="al_relTime")
	public Date getRelTime() {
		return relTime;
	}

	public void setRelTime(Date relTime) {
		this.relTime = relTime;
	}
	@Column(name="al_img")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	@Column(name="al_follow")
	public int getFollow() {
		return follow;
	}

	public void setFollow(int follw) {
		this.follow = follw;
	}
	
	
	
	
}
