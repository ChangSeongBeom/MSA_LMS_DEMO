package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.UserDto;
import com.example.userservice.vo.RequestJoinUserDto;
import com.example.userservice.vo.ResponseJoinUserDto;
import com.example.userservice.vo.ResponseUserDto;
import com.example.userservice.vo.ResponseUserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto joinUser(RequestJoinUserDto requestJoinUserDto);


    Iterable<User> getUserByAll();

    UserDto getUser(String loginId);

    UserDto getUserDetailsByEmail(String userName);

    ResponseJoinUserDto responseJoinUser(UserDto joinUserDto);

    ResponseUserDto findOneUser(UserDto userDto);
}
