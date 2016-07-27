package com.class8.eduAdmin.shiro;

public class HashPassword {
	private String salt;
	
	private String password;

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
