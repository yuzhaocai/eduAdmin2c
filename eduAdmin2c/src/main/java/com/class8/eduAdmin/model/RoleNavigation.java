package com.class8.eduAdmin.model;

import java.io.Serializable;

public class RoleNavigation implements Serializable {

	private static final long serialVersionUID = 1810357930266069547L;
	
	private Integer id;
	private Integer roleId;
	private Integer navigationId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getNavigationId() {
		return navigationId;
	}
	public void setNavigationId(Integer navigationId) {
		this.navigationId = navigationId;
	}
	
}
