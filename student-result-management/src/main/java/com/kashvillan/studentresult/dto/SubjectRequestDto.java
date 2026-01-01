package com.kashvillan.studentresult.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubjectRequestDto {	
	@NotNull
	private Long subCode;
	@NotBlank
	private String subjectName;
	
	
	public Long getSubCode() {
		return subCode;
	}
	public void setSubCode(Long subCode) {
		this.subCode = subCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
}
