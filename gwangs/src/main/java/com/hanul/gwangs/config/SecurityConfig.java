package com.hanul.gwangs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	SecurityFilterChain filter(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((request) -> request.requestMatchers("/Gwangs/main" , "/Gwangs/terms", "/Gwangs/signup",
																		"/Gwangs/signupSuccess", "/Gwangs/login","/member/signup").permitAll()
														.requestMatchers("/css/**" , "/layout/**","/images/**").permitAll()
														.anyRequest().authenticated()
														)
														.formLogin((form) -> form
																.loginPage("/Gwangs/login")
																.usernameParameter("user_id")
																.passwordParameter("user_pwd")
																.defaultSuccessUrl("/Gwangs/main" , true)
																   .failureUrl("/Gwangs/login?error")
																   .permitAll()
														)
														.csrf(csrf -> csrf.disable()
														)
														.logout((logout) -> logout
																.logoutUrl("/logout")
																.logoutSuccessUrl("/Gwangs/main")
																.invalidateHttpSession(true)
																.deleteCookies("JSESSIONID")
																.permitAll()
														);
		
		
		return http.build();
	}
}
