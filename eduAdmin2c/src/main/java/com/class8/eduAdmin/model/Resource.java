package com.class8.eduAdmin.model;

import java.io.Serializable;
import java.util.Date;

import com.class8.eduAdmin.bean.TreeNode;

public class Resource implements Serializable {
	
	private static final long serialVersionUID = 4001628844867006829L;
	
	private Integer id;
	private String name;
	private String description;
	private Integer parentId;
	private String url;
	private Integer type;
	private String permission;
	private Integer priority;
	private String iconCls;
	private Integer status;
	private Integer creator;
	private Date createTime;
	private Date lastmodified;
	private Integer delFlag;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastmodified() {
		return lastmodified;
	}
	public void setLastmodified(Date lastmodified) {
		this.lastmodified = lastmodified;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	public TreeNode toTreeNode(){
		TreeNode treeNode = new TreeNode();
		treeNode.setId(this.getId());
		treeNode.setText(this.getName());
		treeNode.setPid(this.getParentId());
		treeNode.setIconCls(this.getIconCls());
		treeNode.getAttributes().put("url", this.getUrl());
		treeNode.getAttributes().put("type", this.getType());
		treeNode.getAttributes().put("status", this.getStatus());
		
		return treeNode;
	}

}
