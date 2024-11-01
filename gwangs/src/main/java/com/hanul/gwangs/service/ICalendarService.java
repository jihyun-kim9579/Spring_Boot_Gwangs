package com.hanul.gwangs.service;

import java.util.List;

import com.hanul.gwangs.dto.CalendarDTO;
import com.hanul.gwangs.entity.CalendarEntity;
import com.hanul.gwangs.entity.ReserveEntity;

public interface ICalendarService {
	
	// id 기준으로 유저의 캘린더 기록 가져오기
	List<CalendarEntity> getCalendarEventByMemberId(Long memberId);
	
	// 캘린더 이벤트 추가 
	CalendarEntity addCalendarEvent(CalendarDTO calendarDTO);
	
	// 캘린더 업데이트
	CalendarEntity updateCalendarEvent(Long cId , CalendarDTO calendarDTO);
	
	// 캘린더 삭제
	void deleteCalendarEvent(Long cId);
	
	
}
