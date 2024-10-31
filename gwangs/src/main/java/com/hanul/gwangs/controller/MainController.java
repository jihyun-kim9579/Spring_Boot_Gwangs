package com.hanul.gwangs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/Gwangs")
@Log4j2
@RequiredArgsConstructor
public class MainController {
	
	
	@GetMapping("/main")
	public String showMain(Model model) {
		
		return "/Gwangs/main";
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
	
	@GetMapping("/login")
	public void showLogin() {
		
	}
	
	@GetMapping("/loginMain")
	public void showLoginMain() {
		
	}
	
}
