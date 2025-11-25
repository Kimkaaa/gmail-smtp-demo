package com.example.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GmailSmtpDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmailSmtpDemoApplication.class, args);
	}

}
