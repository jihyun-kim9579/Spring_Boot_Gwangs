package com.hanul.gwangs.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanul.gwangs.dto.FieldDTO;
import com.hanul.gwangs.service.IFieldService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
	
	private final IFieldService fieldService;
	
	@GetMapping("/mList")
	public void ManagerList() {
		
	}
	@GetMapping("/register")
//		 authentication 은 spring Security 에서 로그인 한 사용자의 아이디와 권한을 확인 가능	
	public String ManagerRegister(Model model, Authentication authentication) {
		String username = authentication.getName();
		
		if ("manager1".equals(username)) {
			model.addAttribute("fieldName" , "상무 챔피언스 필드");
			model.addAttribute("fieldAddr" , "서구 유덕로 148");
			model.addAttribute("fieldCost" , "120,000");
		} else if("manager2".equals(username)) {
			model.addAttribute("fieldName" , "신화 풋살장");
			model.addAttribute("fieldAddr" , "광산구 왕버들로 33");
			model.addAttribute("fieldCost" , "120,000");
		}
		
		
		return "/manager/register";
	}
	
	
	@PostMapping("/register")
	public String postRegister(FieldDTO fieldDTO) {
		log.info("fieldDTO : {}" , fieldDTO);
		
		fieldService.fieldRegister(fieldDTO);
		
		return "redirect:/manager/mList";
	}
}
