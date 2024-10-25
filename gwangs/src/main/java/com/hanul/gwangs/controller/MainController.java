package com.hanul.gwangs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanul.gwangs.dto.MemberDTO;
import com.hanul.gwangs.service.IMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/Gwangs")
@Log4j2
@RequiredArgsConstructor
public class MainController {
	
	
	@GetMapping("/main")
	public void showMain() {
		
	}
	
	@GetMapping("/terms")
	public void showTerms() {
		
	}
	
	@GetMapping("/signup")
	public void showSignUp() {
		
	}
	
	@GetMapping("/signupSuccess")
	public void showSignupSuccess() {
		
	}
	
	
}
