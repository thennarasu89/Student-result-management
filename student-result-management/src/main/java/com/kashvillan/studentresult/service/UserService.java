package com.kashvillan.studentresult.service;

import com.kashvillan.studentresult.dto.PasswordRequestDto;
import com.kashvillan.studentresult.dto.UserCreateResponseDto;

public interface UserService {
	void changePassword(PasswordRequestDto dto);
	
	UserCreateResponseDto createStudentUser(String username, String assignedClass);

	UserCreateResponseDto createTeacherUser(String username, String assignedClass);
	
}
