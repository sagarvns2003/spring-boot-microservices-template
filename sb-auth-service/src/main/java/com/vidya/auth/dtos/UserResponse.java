package com.vidya.auth.dtos;

import com.vidya.auth.entities.UserRole;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {
  private Long id;
  private String username;
  private Set<UserRole> roles;
}
