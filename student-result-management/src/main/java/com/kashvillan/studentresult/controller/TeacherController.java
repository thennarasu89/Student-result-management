package com.kashvillan.studentresult.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashvillan.studentresult.dto.TeacherRequestDto;
import com.kashvillan.studentresult.dto.UserCreateResponseDto;
import com.kashvillan.studentresult.service.TeacherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("teacher")
public class TeacherController {
	private final TeacherService teacherService;
	
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public UserCreateResponseDto createTeacher(@Valid @RequestBody TeacherRequestDto request) {
		return teacherService.createTeacher(request);
	}
}
