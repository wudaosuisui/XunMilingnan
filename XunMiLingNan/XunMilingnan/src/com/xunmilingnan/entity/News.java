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
	private Group gro;//��������   //��ı�־  0 �� ǰ̨�飻1 - ��̨�飻2 - ��Ϣ��(���ޡ����ۡ���ע��)
	private User user;
	private String message;//��Ϣ������ 
	private Boolean state;//״̬  true Ϊ�Ѷ�  false  Ϊδ��
	
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
