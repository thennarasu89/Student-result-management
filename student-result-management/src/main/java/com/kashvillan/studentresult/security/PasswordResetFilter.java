package com.kashvillan.studentresult.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kashvillan.studentresult.entity.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PasswordResetFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain
			) throws ServletException, IOException{
		
		Authentication authentication = 
				SecurityContextHolder
				.getContext()
				.getAuthentication();
		if(authentication == null || !authentication.isAuthenticated()) {
			filterChain.doFilter(request, response);
			return;
		}
		Object principal = authentication.getPrincipal();
		if(!(principal instanceof User)) {
			filterChain.doFilter(request, response);
			return;
		}
		User user = (User) principal;
		
		if(!"STUDENT".equals(user.getRole())) {
			filterChain.doFilter(request, response);
			return;
		}
		if(!user.isPasswordResetrequired()) {
			filterChain.doFilter(request, response);
			return;
		}
		String requestUri = request.getRequestURI();
		
		if(requestUri.equals("/user/change-password")) {
			filterChain.doFilter(request, response);
			return;
		}
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getWriter().write("password reset required before accessing the system");
	}

}
