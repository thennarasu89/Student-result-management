package com.kashvillan.studentresult.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import com.kashvillan.studentresult.repositories.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + username)
                );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	http
    	  .csrf(csrf -> csrf.disable())
    	  .authorizeHttpRequests(auth -> auth
    	      .requestMatchers("/students/**").hasRole("ADMIN")
    	      .requestMatchers("/subject/**").hasAnyRole("ADMIN", "TEACHER")
    	      .requestMatchers("/result/**").hasAnyRole("ADMIN", "TEACHER")
    	      .anyRequest().authenticated()
    	  )
    	  .httpBasic();

        return http.build();
    }

    
}
