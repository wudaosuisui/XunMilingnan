package com.xunmilingnan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="User_group")
public class Group {
	
	private int id;
	private String sign;
	//�û��飺  0 �� ǰ̨�飻1 - ��̨�飻
	//��Ϣ�飺2 - ������Ϣ�飻3 - ������Ϣ��4 - ��ע��Ϣ��5 - ֪ͨ��Ϣ
	private String name;//�û�������� 
	private String describe;//�û���Ȩ�޵�����
	
	public Group() {}
	
	public Group(String sign, String name, String describe) {
		this.sign = sign;
		this.name = name;
		this.describe = describe;
	}
	
	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")	
	@Column(name="ug_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="ug_sign")
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Column(name="ug_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="ug_describe")
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
}
