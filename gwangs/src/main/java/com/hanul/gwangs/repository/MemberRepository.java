package com.hanul.gwangs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanul.gwangs.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{

	// Optional 은 사용자 정보가 존재하지 않을 수 있기 때문에 사용.
	Optional<MemberEntity> findByUserId(String userId);
	
	// 화면에 user_name 을 찾아서 보여주기 위함
	
}
