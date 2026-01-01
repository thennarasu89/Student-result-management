package com.kashvillan.studentresult.dto;

public class ResultResponseDto {
	private Long studentRegNo;
    private String studentName;
    private Long subjectCode;
    private String subjectName;
    private int marks;
	public Long getStudentRegNo() {
		return studentRegNo;
	}
	public void setStudentRegNo(Long studentRegNo) {
		this.studentRegNo = studentRegNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Long getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(Long subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
    
}
