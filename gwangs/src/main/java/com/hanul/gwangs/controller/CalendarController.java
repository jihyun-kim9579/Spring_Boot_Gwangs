package com.hanul.gwangs.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanul.gwangs.dto.CalendarDTO;
import com.hanul.gwangs.entity.CalendarEntity;
import com.hanul.gwangs.entity.MemberEntity;
import com.hanul.gwangs.repository.MemberRepository;
import com.hanul.gwangs.service.ICalendarService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
@Log4j2
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
	        log.info("savedEvent : {}" , savedEvent);
	        return CalendarDTO.builder()
	                .cId(savedEvent.getCId())
	                .title(savedEvent.getTitle())
	                .startDate(savedEvent.getStartDate())
	                .endDate(savedEvent.getEndDate())
	                .userId(savedEvent.getMember().getUserId())
	                .build();
	    }
	 
	 @PutMapping("/events/{cId}")
	 public CalendarDTO updateCalendarEvent(@PathVariable("cId") Long cId,
			 								@RequestBody CalendarDTO calendarDTO, Authentication authentication) {
		 log.info("Updating event with cId: {}", cId);
		    log.info("Received data: {}", calendarDTO);
		 
		 String userId = authentication.getName();
		 calendarDTO.setUserId(userId);
		 
		 CalendarEntity entity = calendarService.updateCalendarEvent(cId, calendarDTO);
		 
		 return CalendarDTO.builder()
				 .cId(entity.getCId())
				 .title(entity.getTitle())
				 .startDate(entity.getStartDate())
				 .endDate(entity.getEndDate())
				 .userId(entity.getMember().getUserId())
				 .build();
	 }
	 
	 @DeleteMapping("/events/{cId}")
	 public void deleteCalendarEvent(@PathVariable("cId") Long cId, Authentication authentication) {
		 String userId = authentication.getName();
		 MemberEntity member = memberRepository.findByUserId(userId).orElseThrow();
		 calendarService.deleteCalendarEvent(cId);
	 }
}


