package com.hanul.gwangs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanul.gwangs.entity.CalendarEntity;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long>{
	
	List<CalendarEntity> findByMemberId(Long memberId);
}
