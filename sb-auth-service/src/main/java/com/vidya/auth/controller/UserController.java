package com.vidya.auth.controller;

import com.vidya.auth.dtos.*;
import com.vidya.auth.entities.RefreshToken;
import com.vidya.auth.services.JwtService;
import com.vidya.auth.services.RefreshTokenService;
import com.vidya.auth.services.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

  private final JwtService jwtService;
  private final UserService userService;
  private final RefreshTokenService refreshTokenService;
  private final AuthenticationManager authenticationManager;

  @PostMapping(value = "/save")
  public ResponseEntity saveUser(@RequestBody UserRequest userRequest) {
    try {
      UserResponse userResponse = userService.saveUser(userRequest);
      return ResponseEntity.ok(userResponse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/users")
  public ResponseEntity getAllUsers() {
    try {
      List<UserResponse> userResponses = userService.getAllUser();
      return ResponseEntity.ok(userResponses);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @PostMapping("/profile")
  public ResponseEntity<UserResponse> getUserProfile() {
    try {
      UserResponse userResponse = userService.getUser();
      return ResponseEntity.ok().body(userResponse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @GetMapping("/test")
  public String test() {
    try {
      return "Welcome";
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @PostMapping("/login")
  public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequestDTO.getUsername(), authRequestDTO.getPassword()));
    if (authentication.isAuthenticated()) {
      RefreshToken refreshToken =
          refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
      return JwtResponseDTO.builder()
          .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
          .token(refreshToken.getToken())
          .build();

    } else {
      throw new UsernameNotFoundException("invalid user request..!!");
    }
  }

  @PostMapping("/refreshToken")
  public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
    return refreshTokenService
        .findByToken(refreshTokenRequestDTO.getToken())
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUserInfo)
        .map(
            userInfo -> {
              String accessToken = jwtService.GenerateToken(userInfo.getUsername());
              return JwtResponseDTO.builder()
                  .accessToken(accessToken)
                  .token(refreshTokenRequestDTO.getToken())
                  .build();
            })
        .orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));
  }
}
