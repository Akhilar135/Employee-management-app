package com.gds.ey.akhila.employee;

public class Login {
	
	private int userId;
	private String password;
	private LoginType role;
	
	
	public Login() {
		super();
	}
	public Login(int userId, String password, LoginType role) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginType getRole() {
		return role;
	}
	public void setRole(LoginType role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Login [userId=" + userId + ", password=" + password + ", role=" + role + "]";
	}
	
	

}
