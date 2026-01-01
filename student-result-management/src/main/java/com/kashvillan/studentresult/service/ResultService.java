package com.kashvillan.studentresult.service;

import java.util.List;

import com.kashvillan.studentresult.dto.ResultRequestDto;
import com.kashvillan.studentresult.dto.ResultResponseDto;

public interface ResultService {
	ResultResponseDto addResult(ResultRequestDto request);
	
	List<ResultResponseDto> getResultByStudent(Long regNo);
	
	List<ResultResponseDto> getResultBySubject(Long subCode);
}
