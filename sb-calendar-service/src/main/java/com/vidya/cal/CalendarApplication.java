package com.vidya.cal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.vidya")
@EnableAsync
@EnableScheduling
public class CalendarApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CalendarApplication.class, args);
	}
}