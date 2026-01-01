package com.kashvillan.studentresult.service;

import java.util.List;

import com.kashvillan.studentresult.dto.SubjectRequestDto;
import com.kashvillan.studentresult.dto.SubjectResponseDto;

public interface SubjectService {
	SubjectResponseDto createSubject(SubjectRequestDto request);
	
	SubjectResponseDto getSubjectByCode(Long subCode);
	
	List<SubjectResponseDto> getAllSubjects();
}
