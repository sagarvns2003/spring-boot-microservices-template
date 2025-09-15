package com.vidya.auth.entities;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "REFRESH_TOKENS")
public class RefreshToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String token;

  private Instant expiryDate;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserInfo userInfo;
}
