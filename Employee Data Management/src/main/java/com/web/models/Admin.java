package com.web.models;

public class Admin {
	
	private String username;
	private String password;
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public Admin() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "username=" + username + ", password=" + password ;
	}
	
	
	
	
	
	
	

}
