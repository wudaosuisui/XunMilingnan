package com.xunmilingnan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

//电台节目
@Entity
public class Program {
	
	private int id;
	private Date time;//发布时间
	private int praise;//点赞-0
	private int browse;//听取-1
	private int follow;//关注 收藏-2
	private Date longOfTime;//节目时长
	private String name;//节目名称
	private int advCat;//对应的电台分类的id
	private Album album;//对应的专辑
	private String FMName;//节目资源的文件名称，如236.mp4
	private int sortNumber;//排序编号   用于排序，编号越大，排越前，如果排序编号相同，则根据id大小进行排序

	public Program() {}
	
	public Program(Date time, Date longOfTime, String name, int advCat,Album album,
			String fMName, int sortNumber) {
		this.time = time;
		this.praise = 0;
		this.browse = 0;
		this.follow = 0;
		this.longOfTime = longOfTime;
		this.name = name;
		this.advCat = advCat;
		this.album = album;
		FMName = fMName;
		this.sortNumber = sortNumber;
	}
	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")	
	@Column(name="pr_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="pr_time")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	@Column(name="pr_praise")//点赞
	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}
	@Column(name="pr_browse")//听取
	public int getBrowse() {
		return browse;
	}

	public void setBrowse(int browse) {
		this.browse = browse;
	}
	@Column(name="pr_follow")//关注、收藏
	public int getFollow() {
		return follow;
	}

	public void setFollow(int follow) {
		this.follow = follow;
	}
	@Column(name="pr_longOfTime")
	public Date getLongOfTime() {
		return longOfTime;
	}

	public void setLongOfTime(Date longOfTime) {
		this.longOfTime = longOfTime;
	}
	@Column(name="pr_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="pr_advCat")
	public int getAdvCat() {
		return advCat;
	}

	public void setAdvCat(int advCat) {
		this.advCat = advCat;
	}
	@ManyToOne
	@JoinColumn(name="pr_album")
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Column(name="pr_FMName")
	public String getFMName() {
		return FMName;
	}

	public void setFMName(String fMName) {
		FMName = fMName;
	}
	@Column(name="pr_sortNumber")
	public int getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}
	
	
}
