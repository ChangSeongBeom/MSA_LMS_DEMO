package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.RequestJoinUserDto;
import com.example.userservice.dto.ResponseJoinUserDto;
import com.example.userservice.dto.ResponseUserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto joinUser(RequestJoinUserDto requestJoinUserDto);


    Iterable<User> getUserByAll();

    UserDto getUser(String loginId);

    UserDto getUserDetailsById(String userName);

    ResponseJoinUserDto responseJoinUser(UserDto joinUserDto);

    ResponseUserDto findOneUser(UserDto userDto);
}
