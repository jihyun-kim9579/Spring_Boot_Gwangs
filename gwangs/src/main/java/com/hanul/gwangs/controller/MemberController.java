package com.hanul.gwangs.controller;

import java.security.Principal;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanul.gwangs.dto.MemberDTO;
import com.hanul.gwangs.service.IMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
	
	private final IMemberService memberService;
	
	@PostMapping("/signup")
	public String registerMember(@ModelAttribute MemberDTO memberDTO) {
		log.info("회원가입 수신 : {}" , memberDTO);
		
		memberService.register(memberDTO);
		log.info("MemberDTO : {}", memberDTO);
		
		return "redirect:/Gwangs/signupSuccess";
	}
	
	@GetMapping("/myinfo")
	public void showMyInfo() {
		
	}
	
	@GetMapping("/updateMyinfo")
	public String showMyUpdate(Model model, Principal principal) {
		
		if (principal != null) {
			String userId = principal.getName();
			MemberDTO loginUser = memberService.getUserByUserId(userId);
			model.addAttribute("loginUser" , loginUser);
			log.info("loginUser : {}" , loginUser);
		}
		
		return "/member/updateMyinfo";
	}
	
	@PostMapping("/updateMyinfo")
	public void updateMyinfo() {
		
	}
}












