package com.class8.eduAdmin.bean;

import java.util.List;

public class ResultMessage {
	
	private Boolean success;
	
	private String message;
	
	private Object data;
	
	private List<?> rows;
	
	private Long total;
	
	public ResultMessage() {
		
	}
	
	public ResultMessage(boolean success,String message){
		this.success = success;
		this.message = message;
	}
	
	public ResultMessage(boolean success,Object data){
		this.success = success;
		this.data = data;
	}
	
	public ResultMessage(boolean success,List<?> rows,long total){
		this.success = success;
		this.rows = rows;
		this.total = total;
	}
	
	public ResultMessage(boolean success,String message,List<?> rows,long total){
		this.success = success;
		this.message = message;
		this.rows = rows;
		this.total = total;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
}
