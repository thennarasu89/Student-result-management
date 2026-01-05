package com.kashvillan.studentresult.dto;

public class StudentCreateResponsedto {
	private Long regNo;
	private String name;
	private String assignedClass;
	private String username;
	private String tempPassword;
	public Long getRegNo() {
		return regNo;
	}
	public void setRegNo(Long regNo) {
		this.regNo = regNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAssignedClass() {
		return assignedClass;
	}
	public void setAssignedClass(String assignedClass) {
		this.assignedClass = assignedClass;
	}
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
	
	  

}
