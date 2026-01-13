package com.kashvillan.studentresult.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kashvillan.studentresult.dto.TeacherRequestDto;
import com.kashvillan.studentresult.dto.UserCreateResponseDto;
import com.kashvillan.studentresult.entity.Teacher;
import com.kashvillan.studentresult.repositories.TeacherRepository;
import com.kashvillan.studentresult.service.TeacherService;
import com.kashvillan.studentresult.service.UserService;
@Service
public class TeacherServiceImpl implements TeacherService {
	
	private final TeacherRepository teacherRepository;
	private final UserService userService;
	
	public TeacherServiceImpl(TeacherRepository teacherRepository,
			UserService userService) {
		this.teacherRepository = teacherRepository;
		this.userService = userService;
		
	}
	@Transactional
	@Override
	public UserCreateResponseDto createTeacher(TeacherRequestDto request) {
		if(teacherRepository.existsById(request.getTeacherId())) {
			throw new RuntimeException("teacher already exist with this id");
		}
		
		Teacher teacher = new Teacher();
		teacher.setName(request.getName());
		teacher.setTeacherId(request.getTeacherId());
		teacher.setAssignedClass(request.getAssignedClass());
		
		teacherRepository.save(teacher);
		
		String username = "Teacher@" + request.getTeacherId();
		
		UserCreateResponseDto userResponse = userService.createTeacherUser(
				username,
				request.getAssignedClass()
				);                                                  
		
		return userResponse;
	}

}
