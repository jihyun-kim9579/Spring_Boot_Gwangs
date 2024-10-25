package com.hanul.gwangs.common.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import lombok.extern.log4j.Log4j2;

//maven 에서 의존성에서 주입했던 것인데 , 암호화 생성시킨다.
@EnableEncryptableProperties
@Configuration
@Log4j2
public class JasyptConfig {
								//						환경변수 설정할때 썻던 키를 여기에 입력
	private static final String JASYPT_PASSWORD_ENV_VAR = "JASYPT_ENCRYPTOR_PASSWORD";
	
	private final String password;
	
	public JasyptConfig(Environment env) {
		this.password = env.getProperty(JASYPT_PASSWORD_ENV_VAR);
		
		if (this.password == null || this.password.isEmpty()) {
			log.error("JASYPT_ENCRYPTOR_PASSWORD is not set or empty");
			throw new IllegalStateException("JASYPT_ENCRYPTOR_PASSWORD must be set");
		}
		
	}
		@Bean("jasyptStringEncryptor")
		public StringEncryptor stringEncryptor() {
			PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
			
			SimpleStringPBEConfig config = new SimpleStringPBEConfig();
			
			config.setPassword(password);
			config.setPoolSize("1");
			config.setAlgorithm("PBEWithMD5AndDES");
			// encoding 되는 방식 ↓
			config.setStringOutputType("base64");
			config.setKeyObtentionIterations("1000");
			
			config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
			encryptor.setConfig(config);
			
			log.info("Jasypt StringEncryptor configured successfully" , encryptor);
			
			return encryptor;
		}
		
		
}







