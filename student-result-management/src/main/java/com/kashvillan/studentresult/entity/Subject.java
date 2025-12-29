package com.kashvillan.studentresult.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Subject {
	@Id
	private Long subCode;
	
	private String subjectName;
}
