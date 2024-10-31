package com.hanul.gwangs.security.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		
		if (!entity.isMstatus()) {
			log.warn("비활성 계정입니다." , userId);
			throw new DisabledException("계정이 비활성화 되었습니다.");
		}
		
		
		List<GrantedAuthority> authorities = entity.getRoleSet().stream()
												.map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
												.collect(Collectors.toList());
		
		return org.springframework.security.core.userdetails.User.builder()
				.username(entity.getUserId())
				.password(entity.getUser_pwd())
				.authorities(authorities)
				.build();
	}

}




