package com.xunmilingnan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

//消息表
@Entity
public class News {
	private int id;
	private Group gro;//所属分组   //组的标志  0 — 前台组；1 - 后台组；2 - 消息组(点赞、评论、关注等)
	private User user;
	private String message;//消息的内容 
	private Boolean state;//状态  true 为已读  false  为未读
	
	public News() {}
	
	public News(Group gro, User user, String message) {
		this.gro = gro;
		this.user = user;
		this.message = message;
		this.state = false;
	}
	public News(Group gro, User user, String message, Boolean state) {
		this.gro = gro;
		this.user = user;
		this.message = message;
		this.state = state;
	}
	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")	
	@Column(name="ne_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="ne_gro")
	public Group getGro() {
		return gro;
	}

	public void setGro(Group gro) {
		this.gro = gro;
	}
	@ManyToOne
	@JoinColumn(name="ne_uid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="ne_message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@Column(name="ne_state")
	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
	
	
}
