package com.kashvillan.studentresult.service.impl;

import java.nio.file.AccessDeniedException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kashvillan.studentresult.dto.PasswordRequestDto;
import com.kashvillan.studentresult.entity.User;
import com.kashvillan.studentresult.repositories.UserRepository;
import com.kashvillan.studentresult.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordEncoder passwordEncoder2) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
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
