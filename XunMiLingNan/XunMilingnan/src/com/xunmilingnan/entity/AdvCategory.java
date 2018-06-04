package com.xunmilingnan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//�����ࡢ��̨����
@Entity
@Table(name="catever")
public class AdvCategory {
	private int id;
	private String name;
	private int type;//0 - �����ࣻ1 - ��̨���ࣻtype 2 �� ר�����
	private int sortNumber;//������   �������򣬱��Խ����Խǰ�������������ͬ�������id��С��������
	private String img;//��̨����ķ���
	
	public AdvCategory() {}
	
	public AdvCategory(String name,int type,int sortNumber,String img) {
		this.name = name;
		this.sortNumber = sortNumber;
		this.type = type;
		this.img = img;
	}
	//����һ��������
	public AdvCategory(String name,int sortNumber) {
		this.name = name;
		this.sortNumber = sortNumber;
		this.type = 0;
	}
	//����һ����̨����

	public AdvCategory(String name,  int sortNumber, String img) {
		this.name = name;
		this.type = 1;
		this.sortNumber = sortNumber;
		this.img = img;
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
	@Column(name="cv_img")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
