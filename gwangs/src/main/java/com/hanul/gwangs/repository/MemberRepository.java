package com.hanul.gwangs.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hanul.gwangs.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{

	// Optional 은 사용자 정보가 존재하지 않을 수 있기 때문에 사용.
	// header 에 유저 이름을 가져오고 싶어서 사용
	Optional<MemberEntity> findByUserId(String userId);
	
	// admin 이 회원 목록을 보고 싶을때 사용하기 위함
	Page<MemberEntity> findAll(Pageable pageable);
	
	Optional<MemberEntity> findByUserNickname(String user_nickname);
	
	
	
}
