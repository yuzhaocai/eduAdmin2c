package com.class8.eduAdmin.shiro;

import java.io.Serializable;

import com.class8.eduAdmin.model.User;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class ShiroUser implements Serializable {
	
	private static final long serialVersionUID = -456250953603323433L;

	private Integer id;
	private String loginName;
	private User user;
	
	public ShiroUser(){
		
	}
	
	public ShiroUser(Integer id,String loginName,User user){
		this.id = id;
		this.loginName = loginName;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return loginName;
	}
}
