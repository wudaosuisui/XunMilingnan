package com.xunmilingnan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

//��Ϣ��
@Entity
public class News {
	private int id;
	private int gro;//gro
	//��Ϣ�飺0 - ������Ϣ�飻1 - ������Ϣ��2 - ��ע��Ϣ��3 - ��������֪ͨ��Ϣ;4- ���½�Ŀ֪ͨ
	private User user;
	private String message;//��Ϣ������ 
	private Boolean state;//״̬  true Ϊ�Ѷ�  false  Ϊδ��
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
