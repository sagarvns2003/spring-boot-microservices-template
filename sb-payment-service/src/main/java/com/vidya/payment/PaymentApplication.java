package com.vidya.payment;

import com.vidya.payment.feignclient.JSONPlaceholderClient;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class PaymentApplication {

  //@Autowired
  //private JSONPlaceholderClient jsonPlaceholderClient;

  public static void main(String[] args) {
    SpringApplication.run(PaymentApplication.class, args);
  }

  /*@PostConstruct
  public void init() {
    jsonPlaceholderClient.getPosts().stream().forEach(post -> {
      log.info(post.toString());
    });
  }*/
}
