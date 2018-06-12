package com.xunmilingnan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Admin {
	private int id;
	private String userName;
	private String passWord;
	private Group userg;//用户组  多对一
	private String time;//创建时间
	
	public Admin() {}
	
	public Admin(String userName, String passWord, Group userg, String time) {
		this.userName = userName;
		this.passWord = passWord;
		this.userg = userg;
		this.time = time;
	}
	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")	
	@Column(name="adm_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="adm_username")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="adm_password")
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	@ManyToOne
	@JoinColumn(name="adm_ugid")
	public Group getUserg() {
		return userg;
	}

	public void setUserg(Group userg) {
		this.userg = userg;
	}
	@Column(name="adm_time")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
	
	
}
