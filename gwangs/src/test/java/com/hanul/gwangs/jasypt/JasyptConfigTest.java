package com.hanul.gwangs.jasypt;


import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import lombok.extern.log4j.Log4j2;


// 테스트시 console 을 찍일 수 있다.
@DisplayName("암복호화 테스트")
@SpringBootTest
@Log4j2
class JasyptConfigTest {
	
	private static final String JASYPT_PASSWORD_ENV_VAR = "JASYPT_ENCRYPTOR_PASSWORD";
	private String password;
	
	@Autowired
	public JasyptConfigTest(Environment env) {
		this.password = env.getProperty(JASYPT_PASSWORD_ENV_VAR);
		
		if (this.password == null || this.password.isEmpty()) {
			log.error("JASYPT_ENCRYPTOR_PASSWORD is not set or empty");
			throw new IllegalStateException("JASYPT_ENCRYPTOR_PASSWORD mest be set");
		}
		
		log.info("JASYPT_ENCRYPTOR_PASSWORD is not set and not empty");
		
	}
	
	@Test
	void stringEncryptor() {
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		String dbUsername = "gwangs";
		String dbPassword = "0000";
		
		System.out.println("En_dbUrl : " + jasyptEncoding(dbUrl));
		System.out.println("En_dbdbUsername : " + jasyptEncoding(dbUsername));
		System.out.println("En_dbdbPassword : " + jasyptEncoding(dbPassword));
	}
	
	@Test
	void stringDecryptor() {
		String dbUrl = "nYIq2Sb5eh6DSWzfa+JaTmEonZ4GOrchKvyHP2Mma91YLRBkDGRfsdKJkKx3XDXC";
		String dbUsername = "i/c7qQ18ZJQq2MdIw4/MiQ==";
		String dbPassword = "+X+nA63mjXGsYZeYxv2yaA==";
		
		System.out.println("DE_dbUrl : " + jasyptDecoding(dbUrl));
		System.out.println("DE_dbdbUsername : " + jasyptDecoding(dbUsername));
		System.out.println("DE_dbdbPassword : " + jasyptDecoding(dbPassword));
	}
	
	private String jasyptEncoding(String value) {
		
		return jasyptProcessor(true , value);
	}
	
	private String jasyptDecoding(String value) {
		
		return jasyptProcessor(false , value);
	}
	
	private String jasyptProcessor(boolean encrypt , String value) {
		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
		pbeEnc.setAlgorithm("PBEWithMD5AndDES");
		pbeEnc.setPassword(password);
		
		return encrypt ? pbeEnc.encrypt(value) : pbeEnc.decrypt(value);
	}

}
