package com.xunmilingnan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//广告分类、电台分类
@Entity
@Table(name="catever")
public class AdvCategory {
	private int id;
	private String name;
	private int type;//0 - 广告分类；1 - 电台分类；
	private int sortNumber;//排序编号   用于排序，编号越大，排越前，如果排序编号相同，则根据id大小进行排序
	
	public AdvCategory() {}
	
	public AdvCategory(String name,int type,int sortNumber) {
		this.name = name;
		this.sortNumber = sortNumber;
		this.type = type;
	}
	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")	
	@Column(name="cv_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="cv_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="cv_type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name="cv_sortNumber")
	public int getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}
	
	
}
