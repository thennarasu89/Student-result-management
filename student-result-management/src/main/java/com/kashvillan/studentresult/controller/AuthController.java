package com.kashvillan.studentresult.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kashvillan.studentresult.security.*;
import com.kashvillan.studentresult.dto.LoginRequestDto;
import com.kashvillan.studentresult.dto.LoginResponseDto;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final AuthenticationManager authenticationManager;
	private final JwtUtill jwtUtill;
	
	public AuthController(AuthenticationManager authenticationManager,
			JwtUtill jwtUtill) {
		this.authenticationManager = authenticationManager;
		this.jwtUtill = jwtUtill;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request){
		try{
			Authentication authentication =
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								request.getUsername(),
								request.getPassword()
								)
						);
		
		String username = authentication.getName();
		
		String role = authentication
				.getAuthorities()
				.iterator()
				.next()
				.getAuthority(); 
		
		String token = jwtUtill.generateToken(username, role);
		
		return ResponseEntity.ok(new LoginResponseDto(token));
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
