package com.hanul.gwangs.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hanul.gwangs.dto.MemberDTO;
import com.hanul.gwangs.service.IMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/admin")
@Log4j2
@RequiredArgsConstructor
public class AdminController {
	
	private final IMemberService memberService;
	
	@GetMapping("/list")
	public void showAdmin() {
		
	}
	
	@GetMapping("/memberList")
	public String showMemberList(Model model) {
		List<MemberDTO> members = memberService.findAllMembers();
		
		log.info("members : {}" , members);
		model.addAttribute("members" , members);
		
		return "/admin/memberList";
	}
	
	@GetMapping("/managerRegister")
	public void showManagerRegister() {
		
	}
	
	@PostMapping("/addManagerRole")
	public String addManagerRole(@RequestParam("userId") String userId, RedirectAttributes rttr) {
		boolean checkId = memberService.checkUserId(userId);
		
		if (!checkId) {
			rttr.addFlashAttribute("error" , "해당아이디가 없습니다.");
			return "redirect:/admin/managerRegister";
		}
		
		memberService.addRoleManager(userId);
		rttr.addFlashAttribute("message" , "등록되었습니다.");
		return "redirect:/Gwangs/main";
	}
	
	
}