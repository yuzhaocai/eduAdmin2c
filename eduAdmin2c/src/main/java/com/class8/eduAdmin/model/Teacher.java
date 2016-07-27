package com.class8.eduAdmin.model;

import java.io.Serializable;
import java.util.Date;

public class Teacher implements Serializable {

	private static final long serialVersionUID = 2392290363752703321L;
	
	private Integer id;
	//private Date createTime;
	private Long createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
//	public Date getCreateTime() {
//		return createTime;
//	}
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
}
