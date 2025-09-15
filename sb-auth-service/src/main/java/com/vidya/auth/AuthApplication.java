package com.vidya.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Slf4j
@EnableWebSecurity
@EnableMethodSecurity
@EnableFeignClients
@SpringBootApplication
public class AuthApplication {
  public static void main(String[] args) {
    SpringApplication.run(AuthApplication.class, args);
  }
}
