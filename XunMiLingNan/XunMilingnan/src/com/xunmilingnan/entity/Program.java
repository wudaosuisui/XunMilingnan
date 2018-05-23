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
	private User user;//�������û������ߣ����һ
	private Date time;//����ʱ��
	private int praise;//����
	private int browse;//��ȡ
	private int forward;//ת��
	private int follow;//��ע �ղ�
	
	public Program() {}
	
	public Program(User user, Date time, int praise, int browse, int forward, int follow) {
		this.user = user;
		this.time = time;
		this.praise = praise;
		this.browse = browse;
		this.forward = forward;
		this.follow = follow;
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
	@ManyToOne
	@JoinColumn(name="pr_uid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="pr_time")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	@Column(name="pr_praise")
	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}
	@Column(name="pr_browse")
	public int getBrowse() {
		return browse;
	}

	public void setBrowse(int browse) {
		this.browse = browse;
	}
	@Column(name="pr_forward")
	public int getForward() {
		return forward;
	}

	public void setForward(int forward) {
		this.forward = forward;
	}
	@Column(name="pr_follow")
	public int getFollow() {
		return follow;
	}

	public void setFollow(int follow) {
		this.follow = follow;
	}
	
	
	
	
	
}
