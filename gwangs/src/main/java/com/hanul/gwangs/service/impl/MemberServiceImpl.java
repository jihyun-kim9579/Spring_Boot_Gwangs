package com.hanul.gwangs.service.impl;

import org.springframework.stereotype.Service;

import com.hanul.gwangs.dto.MemberDTO;
import com.hanul.gwangs.entity.MemberEntity;
import com.hanul.gwangs.repository.MemberRepository;
import com.hanul.gwangs.service.IMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements IMemberService{
	
	private final MemberRepository memberRepository;

	@Override
	public MemberDTO register(MemberDTO memberDTO) {
		
		log.info("MemberDTO : {}" , memberDTO);
		
		MemberEntity entity = dtoToEntity(memberDTO);
		
		log.info("entity : {}" , entity);
		
		memberRepository.save(entity);
		
		
		return memberDTO;
	}
	
	
}
