package com.hanul.gwangs.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hanul.gwangs.dto.MemberDTO;
import com.hanul.gwangs.entity.MemberEntity;
import com.hanul.gwangs.entity.MemberRole;
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
		
		if (memberDTO.getUser_pwd() != null && !memberDTO.getUser_pwd().isEmpty()) {
			String encodedPassword = passwordEncoder.encode(memberDTO.getUser_pwd());
			memberDTO.setUser_pwd(encodedPassword);
		} else {
			String defualtPassword = passwordEncoder.encode("기본 비밀번호");
			memberDTO.setUser_pwd(defualtPassword);
		}
		
		
		MemberEntity entity = dtoToEntity(memberDTO);
		entity.addMemberRole(MemberRole.USER);
		
		MemberEntity returnEntity = memberRepository.save(entity);
		
		
		return EntityToDto(returnEntity);
	}

	@Override
	public MemberDTO getUserName(String userId) {
		MemberEntity entity = memberRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with userId : " + userId));
		
		
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

	@Override
	public boolean checkPassword(String userId, String passowrd) {
		MemberEntity entity = memberRepository.findByUserId(userId).orElseThrow();
		
		return passwordEncoder.matches(passowrd, entity.getUser_pwd());
	}

	@Override
	public void updateUser(MemberDTO memberDTO) {
		 MemberEntity existingUser = memberRepository.findByUserId(memberDTO.getUser_id())
			        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
			    
	     existingUser.setUser_name(memberDTO.getUser_name());
	     existingUser.setUser_phone(memberDTO.getUser_phone());
	     existingUser.setUser_email(memberDTO.getUser_email());
	     existingUser.setUserNickname(memberDTO.getUser_nickname());
	    
	     if (memberDTO.getNew_password() != null && !memberDTO.getNew_password().isEmpty()) {
	        existingUser.setUser_pwd(passwordEncoder.encode(memberDTO.getNew_password()));
	     }

	     memberRepository.save(existingUser);
	}

	@Override
	public void addRoleManager(String userId) {
		MemberEntity entity = memberRepository.findByUserId(userId).orElseThrow();
		
		entity.addMemberRole(MemberRole.MANAGER);
		
		memberRepository.save(entity);
	}

	@Override
	public boolean checkUserId(String userId) {
		
		return memberRepository.findByUserId(userId).isPresent();
	}

	@Override
	public List<MemberDTO> findAllMembers() {
		
		
		return memberRepository.findAll().stream()
									.map(this::EntityToDto)
									.collect(Collectors.toList());
	}

	@Override
	public MemberEntity findByUserId(String userId) {
		
		return memberRepository.findByUserId(userId).orElseThrow();
	}

	@Override
	public Long findByReserveUserId(String userId) {
		return memberRepository.findByUserId(userId)
								.map(MemberEntity::getId)
								.orElseThrow();
	}

	@Override
	public Long findByBoardUserId(String userId) {
		return memberRepository.findByUserId(userId)
								.map(MemberEntity::getId)
								.orElseThrow();
	}

	@Override
	public MemberDTO registerOAuth2User(MemberDTO memberDTO) {
		memberDTO.setUser_pwd(passwordEncoder.encode("기본비밀번호123!"));
		
		String kakaoId = memberDTO.getUser_id().replace("kakao_", "");
		memberDTO.setUser_nickname("유저1" + kakaoId);
		memberDTO.setMstatus(true);
		memberDTO.setFromSocial(true);
		
		MemberEntity entity = dtoToEntity(memberDTO);
		entity.addMemberRole(MemberRole.USER);
		
		MemberEntity returnEntity = memberRepository.save(entity);
		
		return EntityToDto(returnEntity);
	}

	@Override
	public void deleteMember(Long memberId) {
		Optional<MemberEntity> optionMember = memberRepository.findById(memberId);
		
		if (optionMember.isPresent()) {
			MemberEntity member = optionMember.get();
			member.setMstatus(false);
			memberRepository.save(member);
		} 
		
		
	}

	@Override
	public boolean checkUserIdExists(String userId) {
		Optional<MemberEntity> member = memberRepository.findByUserId(userId);
		return member.isPresent();
	}

	@Override
	public boolean checkUserNickExists(String user_nickname) {
		Optional<MemberEntity> member = memberRepository.findByUserNickname(user_nickname);
		
		return member.isPresent();
	}

	

	

	
	
	
}
