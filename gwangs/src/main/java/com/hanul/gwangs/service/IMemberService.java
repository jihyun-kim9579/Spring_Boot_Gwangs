package com.hanul.gwangs.service;

import java.time.LocalDate;

import com.hanul.gwangs.dto.MemberDTO;
import com.hanul.gwangs.entity.BaseEntity;
import com.hanul.gwangs.entity.MemberEntity;

public interface IMemberService {
	
	MemberDTO register(MemberDTO memberDTO);
	
	default MemberEntity dtoToEntity(MemberDTO memberDTO) {
		
		MemberEntity member = MemberEntity.builder()
									.id(memberDTO.getId())
									.user_id(memberDTO.getUser_id())
									.user_pwd(memberDTO.getUser_pwd())
									.user_name(memberDTO.getUser_name())
									.user_nickname(memberDTO.getUser_nickname())
									.user_email(memberDTO.getUser_email())
									.user_phone(memberDTO.getUser_phone())
									.mstatus(memberDTO.isMstatus())
									.build();
		return member;
	}
	
	default MemberDTO EntityToDto(MemberEntity memberEntity) {
		
		
		MemberDTO memberDTO = MemberDTO.builder()
							.id(memberEntity.getId())
							.user_id(memberEntity.getUser_id())
							.user_pwd(memberEntity.getUser_pwd())
							.user_name(memberEntity.getUser_name())
							.user_nickname(memberEntity.getUser_nickname())
							.user_email(memberEntity.getUser_email())
							.user_phone(memberEntity.getUser_phone())
							.mstatus(memberEntity.isMstatus())
							.build();
		
		
		
		
		return memberDTO;
	}
}
