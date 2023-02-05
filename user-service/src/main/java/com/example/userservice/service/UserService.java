package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto joinUser(UserDto userDto);

    Iterable<User> getUserByAll();

    UserDto getUser(String loginId);

    UserDto getUserDetailsByEmail(String userName);
}
