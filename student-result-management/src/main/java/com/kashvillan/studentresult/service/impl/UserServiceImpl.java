package com.kashvillan.studentresult.service.impl;

import java.nio.file.AccessDeniedException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kashvillan.studentresult.dto.PasswordRequestDto;
import com.kashvillan.studentresult.dto.UserCreateResponseDto;
import com.kashvillan.studentresult.entity.User;
import com.kashvillan.studentresult.repositories.UserRepository;
import com.kashvillan.studentresult.service.UserService;
import com.kashvillan.studentresult.util.PasswordGenerator;
@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordEncoder passwordEncoder2) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	public UserCreateResponseDto createStudentUser(String username, String assignedClass) {
		if (userRepository.findByUsername(username).isPresent()) {
		    throw new RuntimeException("User already exists with username " + username);
		}
		String tempPassword = PasswordGenerator.generate();
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(tempPassword));
		user.setRole("STUDENT");
		user.setAssignedClass(assignedClass);
		user.setEnabled(true);
		user.setPasswordResetrequired(true);
		
		userRepository.save(user);
		
		UserCreateResponseDto response = new UserCreateResponseDto();
		response.setUsername(username);
		response.setTempPassword(tempPassword);
		response.setRole("STUDENT");
		response.setAssignedClass(assignedClass);
		
		return response;
	}
	
	@Override
	public UserCreateResponseDto createTeacherUser(String username, String assignedClass) {
		if(userRepository.findByUsername(username).isPresent()) {
			throw new RuntimeException("user with same uysername already present");
			
		}
		
		String tempPassword = PasswordGenerator.generate();
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(tempPassword));
		user.setRole("TEACHER");
		user.setAssignedClass(assignedClass);
		user.setPasswordResetrequired(true);
		user.setEnabled(true);
		
		userRepository.save(user);
		
		
		UserCreateResponseDto response = new UserCreateResponseDto();
		response.setUsername(username);
		response.setTempPassword(tempPassword);
		response.setRole("TEACHER");
		response.setAssignedClass(assignedClass);
		
		return response;
		
	}
	
	@Override
	public void changePassword(PasswordRequestDto dto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
		
		if(!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
			throw new BadCredentialsException("old password is incorrect");
		}
		
		if(!dto.getNewPassword().equals(dto.getConfirmPassword())) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		String encodedPassword = passwordEncoder.encode(dto.getNewPassword());
		
		user.setPassword(encodedPassword);
		user.setPasswordResetrequired(false);
		
		userRepository.save(user);
			
	}
	private void checkFirstLogin(User user) throws AccessDeniedException {
		if("STUDENT".equals(user.getRole()) && user.isPasswordResetrequired()) {
			throw new AccessDeniedException("password reset required before accessing other");
		}
	}
}
