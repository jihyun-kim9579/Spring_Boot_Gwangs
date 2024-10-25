package com.hanul.gwangs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@EnableJpaAuditing
@Log4j2
public class GwangsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GwangsApplication.class, args);
	}

}
