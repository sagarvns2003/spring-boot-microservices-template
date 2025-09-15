package com.vidya.auth.services;

import com.vidya.auth.dtos.UserRequest;
import com.vidya.auth.dtos.UserResponse;
import java.util.List;

public interface UserService {
  UserResponse saveUser(UserRequest userRequest);

  UserResponse getUser();

  List<UserResponse> getAllUser();
}
