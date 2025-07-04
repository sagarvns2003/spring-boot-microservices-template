package com.vidya.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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