package com.class8.eduAdmin.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.class8.eduAdmin.bean.ResultMessage;
import com.class8.eduAdmin.shiro.ShiroUser;
import com.class8.eduAdmin.util.JsonUtil;

public class BaseController {
	
	protected final Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	
	protected final ShiroUser getCurrentUser(){
		return (ShiroUser)SecurityUtils.getSubject().getPrincipal();
	}
	
	protected static final String success(String message){
		ResultMessage rs = new ResultMessage();
		rs.setSuccess(true);
		rs.setMessage(message);
		return JsonUtil.toJson(rs);
	}
	
	protected final String success(Object data){
		return JsonUtil.toJson(data);
	}
	
	protected final String success(String message,Object data){
		ResultMessage rs = new ResultMessage();
		rs.setSuccess(true);
		rs.setMessage(message);
		rs.setData(data);
		return JsonUtil.toJson(rs);
	}
	
	protected final String success(List<?> rows,long total){
		ResultMessage rs = new ResultMessage();
		rs.setSuccess(true);
		rs.setRows(rows);
		rs.setTotal(total);
		return JsonUtil.toJson(rs);
	}
	
	protected final String failure(String message){
		ResultMessage rs = new ResultMessage();
		rs.setSuccess(false);
		rs.setMessage(message);
		return JsonUtil.toJson(rs);
	}
	
}
