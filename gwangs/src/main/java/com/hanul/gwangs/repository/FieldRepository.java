package com.hanul.gwangs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hanul.gwangs.entity.FieldEntity;

public interface FieldRepository extends JpaRepository<FieldEntity, Long>{
	
	// fstatus 가 true인 구장만 가져오기 
	Page<FieldEntity> findAllByFstatusTrue(Pageable pageable);
}
