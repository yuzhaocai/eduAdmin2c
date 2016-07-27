package com.class8.eduAdmin.model;

import java.io.Serializable;

public class RolePermission implements Serializable {

	private static final long serialVersionUID = 2544272769364957964L;
	
	private Integer id;
	private Integer roleId;
	private String permission;
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
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
