package com.hanul.gwangs.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanul.gwangs.dto.CalendarDTO;
import com.hanul.gwangs.entity.CalendarEntity;
import com.hanul.gwangs.entity.MemberEntity;
import com.hanul.gwangs.repository.MemberRepository;
import com.hanul.gwangs.service.ICalendarService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {
	
	private final ICalendarService calendarService;
	private final MemberRepository memberRepository;
	
	
	@GetMapping("/events")
	public List<CalendarDTO> getCalendarEvents(Authentication authentication) {
		String userId = authentication.getName();
		MemberEntity member = memberRepository.findByUserId(userId).orElseThrow();
		Long memberId = member.getId();
		
		return calendarService.getCalendarEventByMemberId(memberId)
				.stream()
				.map(event -> CalendarDTO.builder()
								.cId(event.getCId())
								.title(event.getTitle())
								.startDate(event.getStartDate())
								.endDate(event.getEndDate())
								.build())
				.collect(Collectors.toList());
	}
	
	 @PostMapping("/events")
	    public CalendarDTO addCalendarEvent(@RequestBody CalendarDTO calendarDTO , Authentication authentication) {
		 	String userId = authentication.getName();
		 	calendarDTO.setUserId(userId);
	        CalendarEntity savedEvent = calendarService.addCalendarEvent(calendarDTO);
	        return CalendarDTO.builder()
	                .cId(savedEvent.getCId())
	                .title(savedEvent.getTitle())
	                .startDate(savedEvent.getStartDate())
	                .endDate(savedEvent.getEndDate())
	                .userId(savedEvent.getMember().getUserId())
	                .build();
	    }
}
