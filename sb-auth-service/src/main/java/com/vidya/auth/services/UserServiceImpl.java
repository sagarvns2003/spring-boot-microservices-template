package com.vidya.auth.services;

import com.vidya.auth.dtos.UserRequest;
import com.vidya.auth.dtos.UserResponse;
import com.vidya.auth.entities.UserInfo;
import com.vidya.auth.repositories.UserRepository;
import java.lang.reflect.Type;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final ModelMapper modelMapper = new ModelMapper();

  @Override
  public UserResponse saveUser(UserRequest userRequest) {
    if (userRequest.getUsername() == null) {
      throw new RuntimeException("Parameter username is not found in request..!!");
    } else if (userRequest.getPassword() == null) {
      throw new RuntimeException("Parameter password is not found in request..!!");
    }

    UserInfo savedUser = null;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String rawPassword = userRequest.getPassword();
    String encodedPassword = encoder.encode(rawPassword);

    UserInfo user = modelMapper.map(userRequest, UserInfo.class);
    user.setPassword(encodedPassword);
    if (userRequest.getId() != null) {
      UserInfo oldUser = userRepository.findFirstById(userRequest.getId());
      if (oldUser != null) {
        oldUser.setId(user.getId());
        oldUser.setPassword(user.getPassword());
        oldUser.setUsername(user.getUsername());
        oldUser.setRoles(user.getRoles());

        savedUser = userRepository.save(oldUser);
      } else {
        throw new RuntimeException("Can't find record with identifier: " + userRequest.getId());
      }
    } else {
      savedUser = userRepository.save(user);
    }
    return modelMapper.map(savedUser, UserResponse.class);
  }

  @Override
  public UserResponse getUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetail = (UserDetails) authentication.getPrincipal();
    String usernameFromAccessToken = userDetail.getUsername();
    UserInfo user = userRepository.findByUsername(usernameFromAccessToken);
    return modelMapper.map(user, UserResponse.class);
  }

  @Override
  public List<UserResponse> getAllUser() {
    List<UserInfo> users = (List<UserInfo>) userRepository.findAll();
    Type setOfDTOsType = new TypeToken<List<UserResponse>>() {}.getType();
    return modelMapper.map(users, setOfDTOsType);
  }
}
