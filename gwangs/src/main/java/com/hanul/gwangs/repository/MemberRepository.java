package com.hanul.gwangs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanul.gwangs.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{

}
