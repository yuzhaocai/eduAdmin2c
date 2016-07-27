package com.class8.eduAdmin.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CaptachaUsernamePasswordToken extends UsernamePasswordToken {

	private static final long serialVersionUID = -6027583182675705455L;
	
	private String captacha;

	public String getCaptacha() {
		return captacha;
	}

	public void setCaptacha(String captacha) {
		this.captacha = captacha;
	}
	
	public CaptachaUsernamePasswordToken(){
		
	}
	
	public CaptachaUsernamePasswordToken(String username, String password, boolean rememberMe, String host,String captacha){
		super(username, password, rememberMe, host);
		this.captacha = captacha;
	}
	

}
