package com.hanul.gwangs.service.impl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public MemberDTO register(MemberDTO memberDTO) {
		
		String encodedPassword = passwordEncoder.encode(memberDTO.getUser_pwd());
		memberDTO.setUser_pwd(encodedPassword);
		log.info("MemberDTO : {}" , memberDTO);
		
		MemberEntity entity = dtoToEntity(memberDTO);
		
		log.info("entity : {}" , entity);
		
		memberRepository.save(entity);
		
		
		return memberDTO;
	}

	@Override
	public MemberDTO getUserName(String userId) {
		MemberEntity entity = memberRepository.findByUserId(userId).orElseThrow();
		
		
		return MemberDTO.builder()
                .user_id(entity.getUserId())
                .user_name(entity.getUser_name())
                .build();
	}

	@Override
	public MemberDTO getUserByUserId(String userId) {
		MemberEntity entity = memberRepository.findByUserId(userId).orElseThrow();
		
		
		return EntityToDto(entity);
	}

	
	
	
}
