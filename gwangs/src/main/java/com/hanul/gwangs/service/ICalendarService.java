package com.hanul.gwangs.service;

import java.util.List;

import com.hanul.gwangs.dto.CalendarDTO;
import com.hanul.gwangs.entity.CalendarEntity;
import com.hanul.gwangs.entity.ReserveEntity;

public interface ICalendarService {
	
	// id 기준으로 유저의 캘린더 기록 가져오기
	List<CalendarEntity> getCalendarEventByMemberId(Long memberId);
	
	// id 기준으로 구장 예약 기록 가져오기 
	List<ReserveEntity> getReserveEventByMemberId(Long memberId);
	
	// id 기준으로 캘린더 및 예약 기록 가져오기
	List<Object> getAllEventsByMemberId(Long memberId);
	
	// 캘린더 이벤트 추가 
	CalendarEntity addCalendarEvent(CalendarDTO calendarDTO);
	
}
