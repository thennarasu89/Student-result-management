package com.kashvillan.studentresult.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ResultRequestDto {
	
	@NotNull
	private Long studentRegNo;
	@NotNull
	private Long subjectCode;
	@Min(0)
	@Max(100)
	private int marks;
	
	
	public Long getStudentRegNo() {
		return studentRegNo;
	}
	public void setStudentRegNo(Long studentRegNo) {
		this.studentRegNo = studentRegNo;
	}
	public Long getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(Long subjectCode) {
		this.subjectCode = subjectCode;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	

}
