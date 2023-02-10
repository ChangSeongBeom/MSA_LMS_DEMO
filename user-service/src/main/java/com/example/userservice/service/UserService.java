package com.example.userservice.service;

import com.example.userservice.model.entity.User;
import com.example.userservice.model.dto.UserDto;
import com.example.userservice.model.dto.RequestJoinUserDto;
import com.example.userservice.model.dto.ResponseJoinUserDto;
import com.example.userservice.model.dto.ResponseUserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto joinUser(RequestJoinUserDto requestJoinUserDto);


    Iterable<User> getUserByAll();

    UserDto getUser(String loginId);

    UserDto getUserDetailsById(String userName);

    ResponseJoinUserDto responseJoinUser(UserDto joinUserDto);

    ResponseUserDto findOneUser(UserDto userDto);
}
