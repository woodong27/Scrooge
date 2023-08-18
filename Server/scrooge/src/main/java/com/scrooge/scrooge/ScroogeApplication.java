package com.scrooge.scrooge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) // 로그인 창 안뜨게
@ComponentScan(basePackages = "com.scrooge")
@EnableScheduling
public class ScroogeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScroogeApplication.class, args);
	}

}
