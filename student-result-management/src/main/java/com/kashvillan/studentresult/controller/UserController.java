package com.kashvillan.studentresult.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashvillan.studentresult.dto.PasswordRequestDto;
import com.kashvillan.studentresult.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/change-password")
	public ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordRequestDto dto){
		userService.changePassword(dto);
		return ResponseEntity.noContent().build();
	}

}
