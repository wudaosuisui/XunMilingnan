package com.xunmilingnan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

//�û��ղر�
@Entity
public class Follow {
	private int id;
	private User user;
	private int type;
	//0 - ����; 1 - ��̨��Ŀ ; 2 - �û���3 - ��̨ר�� ��4 - ���������¼��5- ��Ŀ�����¼
	private int fsid;//����ע�� �����id
	
	public Follow() {	}

	public Follow(User user, int type, int fsid) {
		this.user = user;
		this.type = type;
		this.fsid = fsid;
	}
	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")	
	@Column(name="fo_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="fo_uid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="fo_type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	@Column(name="fo_fsid")
	public int getFsid() {
		return fsid;
	}

	public void setFsid(int fsid) {
		this.fsid = fsid;
	}
	
	
	
}
