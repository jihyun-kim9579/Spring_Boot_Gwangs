package com.hanul.gwangs.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanul.gwangs.dto.MemberDTO;
import com.hanul.gwangs.dto.ReserveDTO;
import com.hanul.gwangs.service.IMemberService;
import com.hanul.gwangs.service.IReserveService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
	
	private final IMemberService memberService;
	private final PasswordEncoder passwordEncoder;
	private final IReserveService reserveService;
	
	@PostMapping("/signup")
	public String registerMember(@ModelAttribute MemberDTO memberDTO) {
		log.info("회원가입 수신 : {}" , memberDTO);
		
		memberService.register(memberDTO);
		log.info("MemberDTO : {}", memberDTO);
		
		return "redirect:/Gwangs/signupSuccess";
	}
	
	@GetMapping("/myinfo")
	public String showMyInfo(Model model , Authentication authentication) {
		String userId = authentication.getName();
	    MemberDTO memberDTO = memberService.getUserByUserId(userId);
	    
	    boolean isSocialUser = memberDTO.isFromSocial();
	    
	    if (!isSocialUser) {
			model.addAttribute("notSocial" , memberDTO);
		} else {
			model.addAttribute("notSocial" , null);
		}
	    return "/member/myinfo";
	}
	
	@GetMapping("/updateMyinfo")
	public String showMyUpdate(Model model, Principal principal) {
		
		if (principal != null) {
			String userId = principal.getName();
			MemberDTO loginUser = memberService.getUserByUserId(userId);
			model.addAttribute("loginUser" , loginUser);
		}
		
		return "/member/updateMyinfo";
	}
	
	@GetMapping("/socialUpdate")
	public String showSocialUser(Model model , Principal principal) {
		
		if (principal != null) {
			String userId = principal.getName();
			MemberDTO loginUser = memberService.getUserByUserId(userId);
			model.addAttribute("socialUser" , loginUser);
		}
		
		return "/member/socialUpdate";
	}
	
	@PostMapping("/updateMyinfo")
	public String updateMyinfo(@ModelAttribute MemberDTO memberDTO) {
		
		if (memberDTO.getNew_password() == null || memberDTO.getNew_password().isEmpty()) {
			memberDTO.setUser_pwd(memberDTO.getCurrent_password());
		} else {
			String encodePassword = passwordEncoder.encode(memberDTO.getNew_password());
			memberDTO.setUser_pwd(encodePassword);
		}
		
		memberService.updateUser(memberDTO);
		
		return "redirect:/Gwangs/main";
	}
	
	@PostMapping("/updateSocial")
	public String updateSocial(@ModelAttribute MemberDTO memberDTO) {
		log.info("memberDTO : {}" , memberDTO);
		
		memberService.updateUser(memberDTO);
		
		return "redirect:/Gwangs/main";
	}
	
	@PostMapping("checkPassword")
	@ResponseBody
	public String checkPassowrd(@RequestParam("password") String password , Principal principal) {
		String userId = principal.getName();
		boolean check = memberService.checkPassword(userId, password);
		
		log.info("CHECK : {}", check);
		
		return check ? "success" : "fail";
	}
	
	@GetMapping("/myReserveList")
	public String showMyReserveList(Principal principal , Model model , Authentication authentication) {
		String userId = principal.getName();
		Long m_id = memberService.findByReserveUserId(userId);
		
		List<ReserveDTO> reservations = reserveService.findReserveByUserId(m_id);
		log.info("reservations" , reservations);
		model.addAttribute("reservations" , reservations);
		
		String userId2 = authentication.getName();
	    MemberDTO memberDTO = memberService.getUserByUserId(userId2);
	    boolean isSocialUser = memberDTO.isFromSocial();
	    if (!isSocialUser) {
			model.addAttribute("notSocial" , memberDTO);
		} else {
			model.addAttribute("notSocial" , null);
		}
	    
		return "/member/myReserveList";
	}
	
}












