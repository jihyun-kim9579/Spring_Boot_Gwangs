package com.hanul.gwangs.security.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.hanul.gwangs.dto.MemberDTO;
import com.hanul.gwangs.service.IMemberService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
	
	@Autowired
	private IMemberService memberService;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("CustomOAuth2UserService invoked.");
		
		DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		
//		 log.info("카카오에서 받아온 사용자 정보: {}", oAuth2User.getAttributes());
		
		
		Map<String, Object> attributes = oAuth2User.getAttributes();
		log.info("Attributes : {}" , attributes);
		
		Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
		
		Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
		
		String nickname = (String) profile.get("nickname");
		String email = (String) kakaoAccount.get("email");
		String kakaoId = String.valueOf(attributes.get("id"));
		
		log.info("닉네임: {}", nickname); // 카카오에서는 nick 네임이 진짜 이름이다.
        log.info("이메일: {}", email);
        log.info("kakao Id: {}", kakaoId);
        
        MemberDTO memberDTO;
        
        if (memberService.checkUserId(kakaoId)) {
			memberDTO = memberService.getUserByUserId(kakaoId);
			log.info("memberDTO : {}" , memberDTO);
		} else {
			MemberDTO newMember = MemberDTO.builder()
									.user_id(kakaoId)
									.user_name(nickname)
									.user_email(email)
									.fromSocial(true)
									.build();
			memberDTO = memberService.registerOAuth2User(newMember);
			log.info("New user registered: {}", memberDTO);
		}
        
        Map<String, Object> customAttributes = new HashMap<>(attributes);
		customAttributes.put("nickname", memberDTO.getUser_name());
		customAttributes.put("email", memberDTO.getUser_email());
        
		
		return new DefaultOAuth2User(oAuth2User.getAuthorities(), customAttributes, "id");
	}

}
