package com.vidya.auth.repositories;

import com.vidya.auth.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {
  public UserInfo findFirstById(Long id);

  public UserInfo findByUsername(String username);
}
