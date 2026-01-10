package com.kashvillan.studentresult.service;

import com.kashvillan.studentresult.dto.TeacherRequestDto;
import com.kashvillan.studentresult.dto.UserCreateResponseDto;

public interface TeacherService {
	UserCreateResponseDto createTeacher(TeacherRequestDto request);
}
