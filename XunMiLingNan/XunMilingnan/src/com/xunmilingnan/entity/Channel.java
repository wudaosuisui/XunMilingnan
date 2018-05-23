package com.xunmilingnan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

//∆µµ¿¿‡–Õ
@Entity
public class Channel {
	private int id;
	private String name;
	private String describe;
	private String img;
	
	public Channel() {}
	
	public Channel(String name, String describe, String img) {
		this.name = name;
		this.describe = describe;
		this.img = img;
	}
	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")	
	@Column(name="ch_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="ch_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="ch_describe")
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	@Column(name="ch_img")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
	
}
