package com.xunmilingnan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

//��̨��Ŀ
@Entity
public class Program {
	
	private int id;
	private Date time;//����ʱ��
	private int praise;//����-0
	private int browse;//��ȡ-1
	private int follow;//��ע �ղ�-2
	private Date longOfTime;//��Ŀʱ��
	private String name;//��Ŀ����
	private int advCat;//��Ӧ�ĵ�̨�����id
	private Album album;//��Ӧ��ר��
	private String FMName;//��Ŀ��Դ���ļ����ƣ���236.mp4
	private int sortNumber;//������   �������򣬱��Խ����Խǰ�������������ͬ�������id��С��������

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
	@Column(name="pr_praise")//����
	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}
	@Column(name="pr_browse")//��ȡ
	public int getBrowse() {
		return browse;
	}

	public void setBrowse(int browse) {
		this.browse = browse;
	}
	@Column(name="pr_follow")//��ע���ղ�
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
