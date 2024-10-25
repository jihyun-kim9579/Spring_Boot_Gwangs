package com.hanul.gwangs.service;


import com.hanul.gwangs.dto.MemberDTO;
import com.hanul.gwangs.entity.MemberEntity;

public interface IMemberService {
	
	MemberDTO register(MemberDTO memberDTO);
	
	//헤더 상단에 유저의 이름 보여주기
	MemberDTO getUserName(String userId);
	
	//모델에 유저의 정보를 담아서 전달하기 위함
	MemberDTO getUserByUserId(String userId);
	
	default MemberEntity dtoToEntity(MemberDTO memberDTO) {
		
		MemberEntity member = MemberEntity.builder()
									.id(memberDTO.getId())
									.userId(memberDTO.getUser_id())
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
							.user_id(memberEntity.getUserId())
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
