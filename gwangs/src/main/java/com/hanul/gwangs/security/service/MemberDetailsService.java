package com.hanul.gwangs.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hanul.gwangs.entity.MemberEntity;
import com.hanul.gwangs.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor
@Log4j2
public class MemberDetailsService implements UserDetailsService{
	
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		log.info("USER_ID : {}" , userId);
		
		MemberEntity entity = memberRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("회원정보를 찾을 수 없습니다." + userId));
		
		return org.springframework.security.core.userdetails.User.builder()
				.username(entity.getUserId())
				.password(entity.getUser_pwd())
				.build();
	}

}








