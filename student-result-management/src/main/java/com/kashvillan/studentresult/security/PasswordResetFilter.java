package com.kashvillan.studentresult.security;

import java.io.IOException;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kashvillan.studentresult.entity.User;
import com.kashvillan.studentresult.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PasswordResetFilter extends OncePerRequestFilter {
	private final UserRepository userRepository;
	
	public PasswordResetFilter(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain
			) throws ServletException, IOException{
		System.out.println(">>> PasswordResetFilter HIT for URI: " + request.getRequestURI());

		Authentication authentication = 
				SecurityContextHolder
				.getContext()
				.getAuthentication();


		if(authentication == null || authentication instanceof AnonymousAuthenticationToken ) {
			System.out.println("its zero");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
			return;
		}
		String username = authentication.getName();
		
		User user = userRepository.findByUsername(username).orElse(null);
		
		if(user == null) {
			System.out.println("its one");
			filterChain.doFilter(request, response);
			return;
			
		}

		if("ADMIN".equals(user.getRole())) {
			System.out.println("its two");
			filterChain.doFilter(request, response);
			return;
		}
		if(!user.isPasswordResetrequired()) {
			System.out.println("its three");
			filterChain.doFilter(request, response);
			return;
		}
		String requestUri = request.getRequestURI();
		
		if(requestUri.equals("/user/change-password")) {
			System.out.println("its four");
			filterChain.doFilter(request, response);
			return;
		}
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getWriter().write("password reset required before accessing the system");
	}

}
