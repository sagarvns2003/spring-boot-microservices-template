package com.vidya.admin.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import de.codecentric.boot.admin.server.config.EnableAdminServer;


@EnableAdminServer
@SpringBootApplication
public class AdminDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminDashboardApplication.class, args);
	}
	
	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurer() {
	 * 
	 * @SuppressWarnings("unused") public void addCorsMappings(CorsRegistry
	 * registry) { registry.addMapping("/instances/").allowedOrigins("*"); } }; }
	 */

}