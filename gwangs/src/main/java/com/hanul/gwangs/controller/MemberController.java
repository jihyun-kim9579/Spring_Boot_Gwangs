package com.hanul.gwangs.controller;

import org.springframework.stereotype.Controller;
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
		
		memberService.register(memberDTO);
		log.info("MemberDTO : {}", memberDTO);
		
		return "redirect:/Gwangs/signupSuccess";
	}
	
}












