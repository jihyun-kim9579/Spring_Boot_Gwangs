package com.hanul.gwangs.controller;


import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hanul.gwangs.dto.FieldDTO;
import com.hanul.gwangs.dto.ReserveDTO;
import com.hanul.gwangs.entity.MemberEntity;
import com.hanul.gwangs.service.IFieldService;
import com.hanul.gwangs.service.IMemberService;
import com.hanul.gwangs.service.IReserveService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/reservation")
@Log4j2
@RequiredArgsConstructor
public class ReservationController {
	
	private final IFieldService fieldService;
	private final IReserveService reserveService;
	private final IMemberService memberService;
	
	@GetMapping("/list")
	public String showList(@RequestParam(name = "page", defaultValue = "1") int page,
						   @RequestParam(name = "size" ,defaultValue = "10") int size,
						   @RequestParam(name = "date", required = false) String dateStr ,Model model) {
		
		Page<FieldDTO> fieldPage;
        if (dateStr != null && !dateStr.isEmpty()) {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
            fieldPage = fieldService.findFieldsByDate(page -1, size, date);
            model.addAttribute("selectedDate", dateStr);
        } else {
            fieldPage = fieldService.findAllFields(page -1, size);
        }
		
		
		model.addAttribute("fieldPage" , fieldPage);
		model.addAttribute("currentPage" , page);
		model.addAttribute("totalPages" , fieldPage.getTotalElements());
		
		return "/reservation/list";
	}
	
	@GetMapping("/detail/{id}")
	public String showDetail(Model model , @PathVariable("id") Long id) {
		FieldDTO field = fieldService.fidnById(id);
		model.addAttribute("field" , field);
		
		return "/reservation/detail";
	}
	
	@PostMapping("/confirm")
	public String postConfirm(Principal principal, ReserveDTO reserveDTO) {
		
		String userId = principal.getName();
		MemberEntity member = memberService.findByUserId(userId);
		reserveDTO.setMemberId(member.getId());
		
		fieldService.deactivateField(reserveDTO.getFieldId());
		reserveService.reserveConfirm(reserveDTO);
		
		
		return "redirect:/Gwangs/main";
	}
	
	@GetMapping("/search")
	public void showSearch() {
		
	}
	
}
