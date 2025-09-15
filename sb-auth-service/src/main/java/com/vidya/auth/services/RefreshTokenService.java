package com.vidya.auth.services;

import com.vidya.auth.entities.RefreshToken;
import com.vidya.auth.repositories.RefreshTokenRepository;
import com.vidya.auth.repositories.UserRepository;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RefreshTokenService {

  private final UserRepository userRepository;
  private final RefreshTokenRepository refreshTokenRepository;

  public RefreshToken createRefreshToken(String username) {
    var refreshToken =
        RefreshToken.builder()
            .userInfo(userRepository.findByUsername(username))
            .token(UUID.randomUUID().toString())
            .expiryDate(Instant.now().plusMillis(600000))
            .build();
    return refreshTokenRepository.save(refreshToken);
  }

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new RuntimeException(
          token.getToken() + " Refresh token is expired. Please make a new login..!");
    }
    return token;
  }
}
