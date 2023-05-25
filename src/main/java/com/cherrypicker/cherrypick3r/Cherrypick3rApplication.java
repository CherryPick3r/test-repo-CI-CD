package com.cherrypicker.cherrypick3r;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class) // 스프링 시큐리티 꺼두기
@EnableJpaAuditing
public class Cherrypick3rApplication {

	public static void main(String[] args) {
		System.setProperty("aws.accessKeyId", "${}");
		System.setProperty("aws.secretKey", "${secretKey}");
		System.setProperty("aws.region", "ap-northeast-2");
		SpringApplication.run(Cherrypick3rApplication.class, args);
	}

}
