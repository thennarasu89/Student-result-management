package com.kashvillan.studentresult.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashvillan.studentresult.dto.SubjectRequestDto;
import com.kashvillan.studentresult.dto.SubjectResponseDto;
import com.kashvillan.studentresult.service.SubjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/subject")
public class SubjectController {
	private final SubjectService subjectService;
	
	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	@PostMapping("/create")
	public SubjectResponseDto createSubject(@Valid @RequestBody SubjectRequestDto request) {
		return subjectService.createSubject(request);
		
	}
	@GetMapping("/{subCode}")
	public SubjectResponseDto getSubjectByCode(
	        @PathVariable Long subCode) {

	    return subjectService.getSubjectByCode(subCode);
	}
	@GetMapping
	public List<SubjectResponseDto> getAllSubjects() {
	    return subjectService.getAllSubjects();
	}



}
