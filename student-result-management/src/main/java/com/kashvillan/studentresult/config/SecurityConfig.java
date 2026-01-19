package com.kashvillan.studentresult.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.kashvillan.studentresult.repositories.UserRepository;
import com.kashvillan.studentresult.security.JwtAuthenticationFilter;
import com.kashvillan.studentresult.security.PasswordResetFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
	
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final PasswordResetFilter passwordResetFilter;

    public SecurityConfig(
    		JwtAuthenticationFilter jwtAuthenticationFilter,
    		PasswordResetFilter passwordResetFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.passwordResetFilter = passwordResetFilter;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(
    		AuthenticationConfiguration configuration) throws Exception{
    	return configuration.getAuthenticationManager();
    }

 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .exceptionHandling(ex -> ex
            .authenticationEntryPoint((request, response, authException) -> {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized");
            })
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/auth/login"
            ).permitAll()
            .anyRequest().authenticated()
        );


        	http.addFilterBefore(
        			jwtAuthenticationFilter,
        			UsernamePasswordAuthenticationFilter.class);
            
            http.addFilterAfter(
                   passwordResetFilter,
                   JwtAuthenticationFilter.class
              );

        return http.build();
    }

    
}
