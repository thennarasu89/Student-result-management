package com.kashvillan.studentresult.service;

import java.util.List;

import com.kashvillan.studentresult.dto.StudentRequestDto;
import com.kashvillan.studentresult.dto.StudentResponseDto;

public interface StudentService {
	StudentResponseDto createStudent(StudentRequestDto request);
	
	StudentResponseDto getStudentByRegNo(Long regNo);
	
	List<StudentResponseDto> getAllStudents();

}
