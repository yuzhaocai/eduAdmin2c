package com.class8.eduAdmin.model;

import java.io.Serializable;

public class NavigationButton implements Serializable {

	private static final long serialVersionUID = 8438708192200338363L;
	
	private Integer id;
	private Integer navigationId;
	private Integer buttonId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNavigationId() {
		return navigationId;
	}
	public void setNavigationId(Integer navigationId) {
		this.navigationId = navigationId;
	}
	public Integer getButtonId() {
		return buttonId;
	}
	public void setButtonId(Integer buttonId) {
		this.buttonId = buttonId;
	}

}
