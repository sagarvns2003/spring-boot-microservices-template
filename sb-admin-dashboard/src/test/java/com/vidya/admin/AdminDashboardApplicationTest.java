package com.vidya.admin;

import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootTest
class AdminDashboardApplicationTest {

  @Test
  void test_main() {
    String[] args = new String[] {"Hi", "Hello"};
    try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
      mocked
          .when(
              () -> {
                SpringApplication.run(AdminDashboardApplication.class, args);
              })
          .thenReturn(Mockito.mock(ConfigurableApplicationContext.class));
      AdminDashboardApplication.main(args);
      mocked.verify(
          () ->
              SpringApplication.run(AdminDashboardApplication.class, new String[] {"Hi", "Hello"}));
    }
  }
}
