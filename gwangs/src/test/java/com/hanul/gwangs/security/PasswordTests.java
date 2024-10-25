package com.hanul.gwangs.security;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j2;


@SpringBootTest
@Log4j2
class PasswordTests {
	
	private final PasswordEncoder passwordEncoder;
	
	// Lombok꺼 생성자 주입 방식 말고 , 이와 같이 생성자 주입 방식으로도 할 수 있다.
	@Autowired
	public PasswordTests(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Test
	void testEncode() {
		String passowrd = "1111";
		
		String enPw = passwordEncoder.encode(passowrd);
		
		log.info("enPw : {}" , enPw);
		
		// 비밀번호 찾기 할때 이 방식을 써야 할까 ?
		boolean matchResult = passwordEncoder.matches(passowrd, enPw);
		
		log.info("matchResult : {}" , matchResult);
	}

}




