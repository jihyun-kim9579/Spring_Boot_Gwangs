package com.hanul.gwangs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hanul.gwangs.dto.CalendarDTO;
import com.hanul.gwangs.entity.CalendarEntity;
import com.hanul.gwangs.entity.MemberEntity;
import com.hanul.gwangs.entity.ReserveEntity;
import com.hanul.gwangs.repository.CalendarRepository;
import com.hanul.gwangs.repository.MemberRepository;
import com.hanul.gwangs.repository.ReserveRepository;
import com.hanul.gwangs.service.ICalendarService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CalendarServiceImpl implements ICalendarService{
	
	private final CalendarRepository calendarRepository;
	private final ReserveRepository reserveRepository;
	private final MemberRepository memberRepository;
	
	
	@Override
	public List<CalendarEntity> getCalendarEventByMemberId(Long memberId) {
		return calendarRepository.findByMemberId(memberId);
	}

	@Override
	public CalendarEntity addCalendarEvent(CalendarDTO calendarDTO) {
		String userId = calendarDTO.getUserId();
		
		MemberEntity member = memberRepository.findByUserId(userId).orElseThrow();
		
		
		CalendarEntity calendarEntity = CalendarEntity.builder()
				.title(calendarDTO.getTitle())
				.startDate(calendarDTO.getStartDate())
				.endDate(calendarDTO.getEndDate())
				.member(member)
				.build();
		return calendarRepository.save(calendarEntity);
	}
	
	
	@Override
	public CalendarEntity updateCalendarEvent(Long cId, CalendarDTO calendarDTO) {
		CalendarEntity entity = calendarRepository.findById(cId).orElseThrow();
		
		entity.setCId(cId);
		entity.setTitle(calendarDTO.getTitle());
		entity.setStartDate(calendarDTO.getStartDate());
		entity.setEndDate(calendarDTO.getEndDate());
		
		return calendarRepository.save(entity);
	}

	@Override
	public void deleteCalendarEvent(Long cId) {
		CalendarEntity entity = calendarRepository.findById(cId).orElseThrow();
		
		calendarRepository.delete(entity);
	}
	
	
	



}
