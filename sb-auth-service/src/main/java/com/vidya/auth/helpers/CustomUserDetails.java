package com.vidya.auth.helpers;

import com.vidya.auth.entities.UserInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends UserInfo implements UserDetails {

  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public CustomUserDetails(UserInfo byUsername) {
    this.username = byUsername.getUsername();
    this.password = byUsername.getPassword();

    List<GrantedAuthority> auths = new ArrayList<>();
    /*for (UserRole role : byUsername.getRoles()) {
      auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
    }*/

    //Preparing authorities
    byUsername.getRoles().stream()
        .filter(Objects::nonNull)
        .map(userRole -> new SimpleGrantedAuthority(userRole.getName().toUpperCase()))
        .forEach(
            sga -> {
              auths.add(sga);
            });
    this.authorities = auths;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
