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
	private User admin;//�����  ���һ
	private Comment reply;//�ظ�   һ��һ
	private User user;//�������û�   ���һ
	private String time;//����ʱ��
	private String text;//��������
	private int type;//0Ϊ���£�1Ϊ��̨��Ŀ
	private int works;//��̨��ĿID/����ID
	
	public Comment(){}
	
	public Comment(boolean auditing, User admin, Comment reply, User user, String time, String text,int type,int works) {
		this.auditing = auditing;
		this.admin = admin;
		this.reply = reply;
		this.user = user;
		this.time = time;
		this.text = text;
		this.type = type;
		this.works = works;
	}
	//�û������»��Ŀ������
	public Comment( User user, String time, String text,int type,int works) {
		this.user = user;
		this.time = time;
		this.text = text;
		this.type = type;
		this.works = works;
	}
	
	//����Ա�Ļظ�����
	public Comment(Comment reply, User user, String time, String text,int type,int works) {
		this.auditing = true;
		this.reply = reply;
		this.user = user;
		this.time = time;
		this.text = text;
		this.type = type;
		this.works = works;
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
	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
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
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	@Column(name="co_text")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	@Column(name="co_type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	@Column(name="co_works")
	public int getWorks() {
		return works;
	}

	public void setWorks(int works) {
		this.works = works;
	}
	
	
	
	
	
	
}
