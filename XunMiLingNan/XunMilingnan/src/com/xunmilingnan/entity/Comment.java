package com.xunmilingnan.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
//����
@Entity
public class Comment {
	
	private int id;
	private boolean auditing;//�Ƿ�������ʾ
	private Admin admin;//�����  ���һ
	private Comment reply;//�ظ�   һ��һ
	private User user;//�������û�   ���һ
	private Date time;//����ʱ��
	private String text;//��������
	
	public Comment(){}
	
	public Comment(boolean auditing, Admin admin, Comment reply, User user, Date time, String text) {
		this.auditing = auditing;
		this.admin = admin;
		this.reply = reply;
		this.user = user;
		this.time = time;
		this.text = text;
	}
	
	public Comment(boolean auditing, Admin admin, User user, Date time, String text) {
		this.auditing = auditing;
		this.admin = admin;
		this.user = user;
		this.time = time;
		this.text = text;
	}
	
	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")	
	@Column(name="co_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="co_auditin")
	public boolean isAuditing() {
		return auditing;
	}

	public void setAuditing(boolean auditing) {
		this.auditing = auditing;
	}
	@ManyToOne
	@JoinColumn(name="co_ad_id")
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	@OneToOne
	@JoinColumn(name="co_reid")
	public Comment getReply() {
		return reply;
	}

	public void setReply(Comment reply) {
		this.reply = reply;
	}
	@ManyToOne
	@JoinColumn(name="co_usid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="co_time")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	@Column(name="co_text")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
	
	
}
