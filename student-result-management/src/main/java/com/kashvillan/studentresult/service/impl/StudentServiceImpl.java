package com.kashvillan.studentresult.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kashvillan.studentresult.dto.UserCreateResponseDto;
import com.kashvillan.studentresult.dto.StudentRequestDto;
import com.kashvillan.studentresult.dto.StudentResponseDto;
import com.kashvillan.studentresult.entity.Student;
import com.kashvillan.studentresult.entity.User;
import com.kashvillan.studentresult.repositories.StudentRepository;
import com.kashvillan.studentresult.repositories.UserRepository;
import com.kashvillan.studentresult.service.StudentService;
import com.kashvillan.studentresult.service.UserService;
import com.kashvillan.studentresult.util.PasswordGenerator;
@Service
public class StudentServiceImpl implements StudentService{
	private final StudentRepository studentRepository;
	private final UserService userService;
	
	public StudentServiceImpl(StudentRepository studentRepository,
			UserService userService) {
		this.studentRepository = studentRepository;
		this.userService = userService;
	}
	
	@Override
	public UserCreateResponseDto createStudent(StudentRequestDto request) {
		if(studentRepository.existsById(request.getRegNo())) {
			throw new RuntimeException("Student already exixt with is register number");
		}
		Student student = new Student();
		student.setRegNo(request.getRegNo());
		student.setName(request.getName());
		student.setAssignedClass(request.getAssignedClass());;
		
		Student saved = studentRepository.save(student);
		
		String username = "student@" + request.getRegNo();
		
		UserCreateResponseDto userResponse = userService.createStudentUser(
				username,
				request.getAssignedClass()
				);                                                  
		
		return userResponse;
	}
	
	@Override
	public StudentResponseDto getStudentByRegNo(Long regNo) {
		Student student = studentRepository.findById(regNo).orElseThrow();
		
		StudentResponseDto response = new StudentResponseDto();
		
		response.setRegNo(student.getRegNo());
		response.setName(student.getName());
		response.setAssignedClass(student.getAssignedClass());

		return response;
	}
	@Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> {
                    StudentResponseDto dto = new StudentResponseDto();
                    dto.setRegNo(student.getRegNo());
                    dto.setName(student.getName());
                    dto.setAssignedClass(student.getAssignedClass());
                    return dto;
                })
                .collect(Collectors.toList());
    }
	
}
