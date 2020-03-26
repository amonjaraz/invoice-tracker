package com.alexmonjaraz.customuserregistration.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewUser {

	@NotNull(message="is required")
	@Size(min=4, max=50, message="must be 6-50 characters.")
	private String userName;
	
	@Size(min=6, max=50, message="must be 6-50 characters.")
	@NotNull(message="is required")
	private String password;
	
	@Size(min=6, max=50, message="must be 6-50 characters.")
	@NotNull(message="is required")
	private String passwordConfirm;
	
	@Size(min=6, max=50, message="must be 6-50 characters.")
	@NotNull(message="is required")
	private String email;
	
	public NewUser() {}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "NewUser [userName=" + userName + ", password=" + password + ", passwordConfirm=" + passwordConfirm
				+ ", email=" + email + "]";
	}
	
	
}
