package com.kashvillan.studentresult.service;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import com.kashvillan.studentresult.dto.ResultRequestDto;
import com.kashvillan.studentresult.dto.ResultResponseDto;

public interface ResultService {
	ResultResponseDto addResult(ResultRequestDto request);
	
	List<ResultResponseDto> getResultByStudent(Long regNo) throws AccessDeniedException;
	
	List<ResultResponseDto> getResultBySubject(Long subCode);
}
