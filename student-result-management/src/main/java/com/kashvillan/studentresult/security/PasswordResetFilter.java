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
		String requestUri = request.getRequestURI();
		System.out.println(">>>password reset filyer HIT" + requestUri);
		
		if(requestUri.startsWith("/swagger-ui")||
				requestUri.startsWith("/v3/api-docs")||
				requestUri.equals("/auth/login")||
				requestUri.equals("/user/change-password")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		Authentication authentication = 
				SecurityContextHolder
				.getContext()
				.getAuthentication();


		if(authentication == null || authentication instanceof AnonymousAuthenticationToken ) {
			System.out.println("its zero");
			filterChain.doFilter(request, response);
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
		if(user.isPasswordResetrequired()) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.getWriter().write("password reset is required");
			return;
		}
		
		
		filterChain.doFilter(request, response);
	}

}
