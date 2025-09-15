package com.vidya.auth.helpers;

import com.vidya.auth.entities.UserInfo;
import com.vidya.auth.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserInfo user = userRepository.findByUsername(username);
    if (user == null) {
      log.error("Username not found: {}", username);
      throw new UsernameNotFoundException("could not found user..!!");
    }
    log.info("User Authenticated Successfully..!!!");
    return new CustomUserDetails(user);
  }
}
