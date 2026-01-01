package com.kashvillan.studentresult.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentRequestDto {
	@NotNull
	private Long regNo;
	@NotBlank
	private String name;
	@NotBlank
	private String assignedClass;
	
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
	
}
