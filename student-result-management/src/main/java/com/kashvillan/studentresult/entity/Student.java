package com.kashvillan.studentresult.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
	@Id
	private Long regNo;
	
	private String name;
	private String assignedClass;
}
