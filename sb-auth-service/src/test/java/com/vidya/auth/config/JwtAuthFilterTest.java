package com.vidya.auth.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.vidya.auth.helpers.UserDetailsServiceImpl;
import com.vidya.auth.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

class JwtAuthFilterTest {

  @InjectMocks private JwtAuthFilter underTest;

  @Mock private JwtService jwtService;

  @Mock private UserDetailsServiceImpl userDetailsServiceImpl;

  @Mock private HttpServletRequest request;

  @Mock private HttpServletResponse response;

  @Mock private FilterChain filterChain;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    SecurityContextHolder.clearContext();
  }

  @Test
  void doFilterInternal_withValidToken_setsAuthentication() throws ServletException, IOException {
    String token = "valid-token";
    String username = "testuser";

    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(jwtService.extractUsername(token)).thenReturn(username);

    UserDetails userDetails = new User(username, "password", Collections.emptyList());
    when(userDetailsServiceImpl.loadUserByUsername(username)).thenReturn(userDetails);
    when(jwtService.validateToken(token, userDetails)).thenReturn(true);

    underTest.doFilterInternal(request, response, filterChain);

    assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    assertEquals(username, SecurityContextHolder.getContext().getAuthentication().getName());
    verify(filterChain).doFilter(request, response);
  }

  @Test
  void doFilterInternal_withNoAuthorizationHeader_doesNothing()
      throws ServletException, IOException {
    when(request.getHeader("Authorization")).thenReturn(null);

    underTest.doFilterInternal(request, response, filterChain);

    assertNull(SecurityContextHolder.getContext().getAuthentication());
    verify(filterChain).doFilter(request, response);
  }

  @Test
  void doFilterInternal_withInvalidPrefix_doesNothing() throws ServletException, IOException {
    when(request.getHeader("Authorization")).thenReturn("Basic token");

    underTest.doFilterInternal(request, response, filterChain);

    assertNull(SecurityContextHolder.getContext().getAuthentication());
    verify(filterChain).doFilter(request, response);
  }

  @Test
  void doFilterInternal_withNullUsername_doesNothing() throws ServletException, IOException {
    String token = "some-token";
    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(jwtService.extractUsername(token)).thenReturn(null);

    underTest.doFilterInternal(request, response, filterChain);

    assertNull(SecurityContextHolder.getContext().getAuthentication());
    verify(filterChain).doFilter(request, response);
  }

  @Test
  void doFilterInternal_withAlreadyAuthenticatedContext_doesNothing()
      throws ServletException, IOException {
    String token = "some-token";
    String username = "testuser";

    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(jwtService.extractUsername(token)).thenReturn(username);

    SecurityContextHolder.getContext()
        .setAuthentication(
            new UsernamePasswordAuthenticationToken("existingUser", null, Collections.emptyList()));

    underTest.doFilterInternal(request, response, filterChain);

    verify(filterChain).doFilter(request, response);
  }

  @Test
  void doFilterInternal_withInvalidToken_doesNotAuthenticate()
      throws ServletException, IOException {
    String token = "invalid-token";
    String username = "testuser";

    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(jwtService.extractUsername(token)).thenReturn(username);

    UserDetails userDetails = new User(username, "password", Collections.emptyList());
    when(userDetailsServiceImpl.loadUserByUsername(username)).thenReturn(userDetails);
    when(jwtService.validateToken(token, userDetails)).thenReturn(false);

    underTest.doFilterInternal(request, response, filterChain);

    assertNull(SecurityContextHolder.getContext().getAuthentication());
    verify(filterChain).doFilter(request, response);
  }
}
