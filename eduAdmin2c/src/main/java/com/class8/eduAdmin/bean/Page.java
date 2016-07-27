package com.class8.eduAdmin.bean;

import org.apache.commons.lang3.StringUtils;

public class Page {
	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	public static final int DEFAULT_PAGE = 1;
	public static final int DEFAULT_ROWS = 10;
	
	public int page = DEFAULT_PAGE;
	public int rows = DEFAULT_ROWS;
	public String sort;
	public String order = ASC;
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page > 0 ? page:1;
	}
	
	public int getRows() {
		return rows;
	}
	
	public void setRows(int rows) {
		this.rows = rows > 0 ? rows : DEFAULT_ROWS;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		if(StringUtils.isEmpty(sort))
			this.sort = null;
		else
			this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		if(StringUtils.isEmpty(order) || (Page.ASC.equalsIgnoreCase(order.trim()) && order.trim() != Page.DESC))
			this.order = Page.ASC;
		else 
			this.order = order;
	}
	
	public int getOffset(){
		return (this.getPage()-1)*this.getRows();
	}
	
}
