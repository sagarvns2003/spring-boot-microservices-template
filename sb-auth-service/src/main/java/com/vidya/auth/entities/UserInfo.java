package com.vidya.auth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private long id;

  private String username;

  @JsonIgnore private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<UserRole> roles = new HashSet<>();
}
