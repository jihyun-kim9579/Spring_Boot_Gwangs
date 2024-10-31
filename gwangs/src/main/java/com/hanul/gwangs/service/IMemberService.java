package com.hanul.gwangs.service;


import java.util.List;

import com.hanul.gwangs.dto.MemberDTO;
import com.hanul.gwangs.entity.MemberEntity;

public interface IMemberService {
	
	MemberDTO register(MemberDTO memberDTO);
	
	//헤더 상단에 유저의 이름 보여주기
	MemberDTO getUserName(String userId);
	
	//모델에 유저의 정보를 담아서 전달하기 위함
	MemberDTO getUserByUserId(String userId);
	
	// 패스워드 일치시 정보수정으로 넘어가기 위함
	boolean checkPassword(String userId , String passowrd);
	
	// 유저의 정보를 업데이트
	void updateUser(MemberDTO memberDTO);
	
	// 매니저 변경을 위해 member id를 체크
	boolean checkUserId(String userId);
	
	// 매니저로 변경하기 위함
	void addRoleManager(String userId);
	
	// admin 이 회원 목록 리스트를 보기 위함
	List<MemberDTO> findAllMembers();
	
	// reserve 할때 userId 값 가져와서 같이 넘겨주기 
	MemberEntity findByUserId(String userId);
	
	// reserve 하고 mid 기준으로 리스트 가져오기 위해서 Long 아이디 필요
	Long findByReserveUserId(String userId);
	
	// board 등록을 위한 userId 가져오기.
	Long findByBoardUserId(String userId);
	
	//소셜로 들어온 사람들 등록
	MemberDTO registerOAuth2User(MemberDTO memberDTO);
	
	// 회원 탈퇴 하면 , Mstatus 가 0으로 변경
	void deleteMember(Long memberId);
	
	// 아이디 중복확인 메서드
	boolean checkUserIdExists(String userId);
	// 닉네임 중복확인 메서드
	boolean checkUserNickExists(String user_nickname);
	
	
	
	default MemberEntity dtoToEntity(MemberDTO memberDTO) {
		
		MemberEntity member = MemberEntity.builder()
									.id(memberDTO.getId())
									.userId(memberDTO.getUser_id())
									.user_pwd(memberDTO.getUser_pwd())
									.user_name(memberDTO.getUser_name())
									.userNickname(memberDTO.getUser_nickname())
									.user_email(memberDTO.getUser_email())
									.user_phone(memberDTO.getUser_phone())
									.mstatus(memberDTO.isMstatus())
									.fromSocial(memberDTO.isFromSocial())
									.build();
		return member;
	}
	
	default MemberDTO EntityToDto(MemberEntity memberEntity) {
		
		
		MemberDTO memberDTO = MemberDTO.builder()
							.id(memberEntity.getId())
							.user_id(memberEntity.getUserId())
							.user_pwd(memberEntity.getUser_pwd())
							.user_name(memberEntity.getUser_name())
							.user_nickname(memberEntity.getUserNickname())
							.user_email(memberEntity.getUser_email())
							.user_phone(memberEntity.getUser_phone())
							.mstatus(memberEntity.isMstatus())
							.regDate(memberEntity.getRegDate())
							.modDate(memberEntity.getModDate())
							.roleSet(memberEntity.getRoleSet())
							.fromSocial(memberEntity.isFromSocial())
							.build();
		
		
		
		
		return memberDTO;
	}
}
