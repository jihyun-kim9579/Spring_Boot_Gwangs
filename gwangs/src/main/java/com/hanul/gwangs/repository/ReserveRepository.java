package com.hanul.gwangs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanul.gwangs.entity.ReserveEntity;

public interface ReserveRepository extends JpaRepository<ReserveEntity, Long>{
	
	List<ReserveEntity> findByMemberId(Long memberId);
}
