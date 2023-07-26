package com.scrooge.scrooge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) // 로그인 창 안뜨게
public class ScroogeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScroogeApplication.class, args);
	}

}
