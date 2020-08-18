package com.mailstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MailstarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailstarterApplication.class, args);
	}

}
