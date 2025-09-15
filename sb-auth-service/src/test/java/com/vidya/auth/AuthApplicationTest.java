package com.vidya.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootTest
class AuthApplicationTest {

  @Autowired
  private ApplicationContext context;

  @Test
  void contextLoads() {
    assertThat(context).isNotNull();
    assertThat(context.containsBean("authApplication")).isTrue();
  }

  @Test
  void main() {
    String[] args = new String[] {"Hi", "Hello"};
    try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
      mocked
          .when(
              () -> {
                SpringApplication.run(AuthApplication.class, args);
              })
          .thenReturn(Mockito.mock(ConfigurableApplicationContext.class));
      AuthApplication.main(args);
      mocked.verify(
          () -> SpringApplication.run(AuthApplication.class, new String[] {"Hi", "Hello"}));
    }
  }
}
