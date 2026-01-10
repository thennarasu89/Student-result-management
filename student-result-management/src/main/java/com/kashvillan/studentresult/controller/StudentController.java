package com.kashvillan.studentresult.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashvillan.studentresult.dto.UserCreateResponseDto;
import com.kashvillan.studentresult.dto.StudentRequestDto;
import com.kashvillan.studentresult.dto.StudentResponseDto;
import com.kashvillan.studentresult.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {
	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public UserCreateResponseDto CreateStudent(@Valid @RequestBody StudentRequestDto request) {
		return studentService.createStudent(request);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
	@GetMapping("/{regNo}")
	public StudentResponseDto getStudentByRegNo(
	        @PathVariable Long regNo) {

	    return studentService.getStudentByRegNo(regNo);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
	@GetMapping
	public List<StudentResponseDto> getAllStudents() {
	    return studentService.getAllStudents();
	}

	
	
}
