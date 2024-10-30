package com.hanul.gwangs.controller;


import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanul.gwangs.dto.BoardDTO;
import com.hanul.gwangs.dto.MemberDTO;
import com.hanul.gwangs.dto.PageRequestDTO;
import com.hanul.gwangs.service.IBoardService;
import com.hanul.gwangs.service.IMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	private final IBoardService boardService;
	private final IMemberService memberService;
	
	@GetMapping("/list")
	public String showList(PageRequestDTO pageRequestDTO ,Model model) {
		
		model.addAttribute("result" , boardService.getList(pageRequestDTO));
		log.info("Model : {}" , model);
		
		return "/board/list";
	}
	
	@GetMapping("/register")
	public void showRegister() {
		
	}
	
	@PostMapping("/register")
	public String postRegister(Principal principal, BoardDTO boardDTO) {
		String userId = principal.getName();
		log.info("userId : {}" , userId);
		Long memberId = memberService.findByBoardUserId(userId);
		boardDTO.setMemberid(memberId);
		
		boardService.boardRegister(boardDTO);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/read/{bno}")
	public String showRead(Principal principal ,Model model , @PathVariable("bno") Long bno) {
		String userId = principal.getName();
		MemberDTO loginUserNick = memberService.getUserByUserId(userId);
		model.addAttribute("userNick" , loginUserNick.getUser_nickname());
		
		BoardDTO boardDTO = boardService.getBoradByBno(bno);
		
		model.addAttribute("board" , boardDTO);
		
		return "/board/read";
	}
	
	@GetMapping("/update/{bno}")
	public String showUpdate(Model model, @PathVariable("bno") Long bno) {
		BoardDTO boardDTO = boardService.getBoradByBno(bno);
		model.addAttribute("board" , boardDTO);
		
		return "/board/update";
	}
	
	@PostMapping("/update/{bno}")
	public String postUpdate(@PathVariable("bno") Long bno, BoardDTO boardDTO) {
		boardDTO.setBno(bno);
		boardService.updateBoardByBno(bno, boardDTO);
		return "redirect:/board/read/" + bno;
	}
	
	@PostMapping("/delete/{bno}")
	public String postDeleteBoard(@PathVariable("bno") Long bno) {
		boardService.deleteBoard(bno);
		return "redirect:/board/list";
	}
}
