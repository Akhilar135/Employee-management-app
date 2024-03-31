package com.gds.ey.akhila.employee;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {
	
	
	private static final long serialVersionUID = -8609237543013944804L;
	private int userId;
	private String name;
	private EmployeeRole role;
	private LocalDate dob;
	private String fullAddress;
	private String city;
	
	public Employee() {
		
		super();
	}
	public Employee(int userId, String name, EmployeeRole role, LocalDate dob, String fullAddress, String city) {
		super();
		this.userId = userId;
		this.name = name;
		this.role = role;
		this.dob = dob;
		this.fullAddress = fullAddress;
		this.city = city;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EmployeeRole getRole() {
		return role;
	}
	public void setRole(EmployeeRole role) {
		this.role = role;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Employee [userId=" + userId + ", name=" + name + ", role=" + role + ", dob=" + dob + ", fullAddress="
				+ fullAddress + ", city=" + city + "]";
	}
	
	

	
	
	}


