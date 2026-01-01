package com.kashvillan.studentresult.controller;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashvillan.studentresult.dto.ResultRequestDto;
import com.kashvillan.studentresult.dto.ResultResponseDto;
import com.kashvillan.studentresult.service.ResultService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/result")
public class ResultController {
	private final ResultService resultService;

	public ResultController(ResultService resultService) {
	    this.resultService = resultService;
	}

	@PostMapping("/add")
	public ResultResponseDto addResult(@Valid @RequestBody ResultRequestDto request) {
		return resultService.addResult(request);
	}
	@GetMapping("/student/{regNo}")
	public List<ResultResponseDto> getResultsByStudent(
	        @PathVariable Long regNo) {

	    return resultService.getResultByStudent(regNo);
	}
	@GetMapping("/subject/{subCode}")
	public List<ResultResponseDto> getResultsBySubject(
	        @PathVariable Long subCode) {

	    return resultService.getResultBySubject(subCode);
	}


}
