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
	private int gro;//gro
	//消息组：0 - 点赞消息组；1 - 评论消息；2 - 关注消息；3 - 更新文章通知消息;4- 更新节目通知
	private User user;
	private String message;//消息的内容 
	private Boolean state;//状态  true 为已读  false  为未读
	private int jumpId;
	public News() {}
	
	public News(int gro, User user, String message,int jumpId) {
		this.gro = gro;
		this.user = user;
		this.message = message;
		this.jumpId = jumpId;
		this.state = false;
	}
	public News(int gro, User user, String message,int jumpId, Boolean state) {
		this.gro = gro;
		this.user = user;
		this.message = message;
		this.jumpId = jumpId;
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
	
	@Column(name="ne_gro")
	public int getGro() {
		return gro;
	}

	public void setGro(int gro) {
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
	@Column(name="ne_jumpId")
	public int getJumpId() {
		return jumpId;
	}

	public void setJumpId(int jumpId) {
		this.jumpId = jumpId;
	}

	@Column(name="ne_state")
	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
	
	
}
