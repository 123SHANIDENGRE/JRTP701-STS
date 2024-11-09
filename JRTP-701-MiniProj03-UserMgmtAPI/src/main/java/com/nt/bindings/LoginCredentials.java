package com.nt.bindings;

public class LoginCredentials {

	
	private String email;
	private String Password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public LoginCredentials(String email, String password) {
		super();
		this.email = email;
		Password = password;
	}
	public LoginCredentials() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
