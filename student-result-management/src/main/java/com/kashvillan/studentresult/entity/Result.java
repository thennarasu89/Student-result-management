package com.kashvillan.studentresult.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long resultId;
	
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;
	
	@ManyToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;
	
	 private int marks;
}
