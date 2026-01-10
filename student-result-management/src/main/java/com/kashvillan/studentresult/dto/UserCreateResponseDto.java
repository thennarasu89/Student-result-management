package com.kashvillan.studentresult.dto;

public class UserCreateResponseDto {
	
	private String username;
	private String tempPassword;
	private String role;
	private String assignedClass;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTempPassword() {
		return tempPassword;
	}
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAssignedClass() {
		return assignedClass;
	}
	public void setAssignedClass(String assignedClass) {
		this.assignedClass = assignedClass;
	}
	
	

}
