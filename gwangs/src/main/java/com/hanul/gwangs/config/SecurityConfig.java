package com.hanul.gwangs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;

import com.hanul.gwangs.security.service.CustomOAuth2UserService;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {
	
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	
	
	
	@Bean
	SecurityFilterChain filter(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((request) -> request.requestMatchers("/Gwangs/main" , "/Gwangs/terms", "/Gwangs/signup",
																		"/Gwangs/signupSuccess", "/Gwangs/login","/member/signup",
																		"/member/checkUserId","/member/checkUserNick","/api/calendar/events/**").permitAll()
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
														.oauth2Login(oauth2 -> oauth2
																.loginPage("/Gwangs/login")
																.defaultSuccessUrl("/Gwangs/main" , true)
																.failureUrl("/Gwangs/login?error")
																.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
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
