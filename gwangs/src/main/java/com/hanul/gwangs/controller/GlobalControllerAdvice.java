package com.hanul.gwangs.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hanul.gwangs.dto.MemberDTO;
import com.hanul.gwangs.service.IMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


//@ControllerAdvice 는 모든 컨트롤러에 대해 공통된 설정을 적용할 수 있도록 하는 전역 컨트롤러이다.
@ControllerAdvice
@Log4j2
@RequiredArgsConstructor
public class GlobalControllerAdvice {
		
	 private final IMemberService memberService;
	 
	 @ModelAttribute("userName")
		public String addUserNameToModel(Principal principal) {
			
			if (principal != null) {
				String userId = principal.getName();
	            MemberDTO memberDTO = memberService.getUserName(userId);
	            return memberDTO.getUser_name();
			}
			
			return null;
		}
}
