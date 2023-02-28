package com.deliveryapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 
 * This is the class which contains the spring boot security configuration for the whole application
 * 
 * @author mohd.shadab
 *
 */
@Configuration
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeHttpRequests().anyRequest()
				.permitAll().and().formLogin();
		return http.build();
	}

}
