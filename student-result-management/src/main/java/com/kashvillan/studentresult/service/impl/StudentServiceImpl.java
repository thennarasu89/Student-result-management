package com.kashvillan.studentresult.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kashvillan.studentresult.dto.StudentCreateResponseDto;
import com.kashvillan.studentresult.dto.StudentRequestDto;
import com.kashvillan.studentresult.dto.StudentResponseDto;
import com.kashvillan.studentresult.entity.Student;
import com.kashvillan.studentresult.entity.User;
import com.kashvillan.studentresult.repositories.StudentRepository;
import com.kashvillan.studentresult.repositories.UserRepository;
import com.kashvillan.studentresult.service.StudentService;
import com.kashvillan.studentresult.util.PasswordGenerator;
@Service
public class StudentServiceImpl implements StudentService{
	private final StudentRepository studentRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public StudentServiceImpl(StudentRepository studentRepository,
			UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		this.studentRepository = studentRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public StudentCreateResponseDto createStudent(StudentRequestDto request) {
		Student student = new Student();
		student.setRegNo(request.getRegNo());
		student.setName(request.getName());
		student.setAssignedClass(request.getAssignedClass());;
		if(studentRepository.existsById(request.getRegNo())) {
			throw new RuntimeException("Student already exixt with is register number");
		}
		Student saved = studentRepository.save(student);
		
		String tempPassword = PasswordGenerator.generate();
		
		User user = new User();
		user.setUserId(saved.getRegNo());
		user.setUsername("student@" + saved.getRegNo());
		user.setPassword(passwordEncoder.encode(tempPassword));
		user.setRole("STUDENT");
		user.setEnabled(true);
		user.setPasswordResetrequired(true);
		
		userRepository.save(user);
		
		StudentCreateResponseDto response =  new StudentCreateResponseDto();
		response.setRegNo(saved.getRegNo());
		response.setName(saved.getName());
		response.setAssignedClass(saved.getAssignedClass());
		response.setUsername(user.getUsername());
		response.setTempPassword(tempPassword);                                                  
		
		return response;
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
