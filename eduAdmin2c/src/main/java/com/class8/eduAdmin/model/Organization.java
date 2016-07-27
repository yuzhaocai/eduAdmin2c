package com.class8.eduAdmin.model;

import java.io.Serializable;

import com.class8.eduAdmin.bean.TreeNode;

public class Organization implements Serializable {

	private static final long serialVersionUID = 4407729856926628210L;
	
	private Integer id;
	private String name;
	private Integer parentId;
	private Integer priority;
	private String description;
	
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TreeNode toTreeNode(){
		TreeNode treeNode = new TreeNode();
		treeNode.setId(this.getId());
		treeNode.setText(this.getName());
		treeNode.setPid(this.getParentId());
		return treeNode;
	}

}
