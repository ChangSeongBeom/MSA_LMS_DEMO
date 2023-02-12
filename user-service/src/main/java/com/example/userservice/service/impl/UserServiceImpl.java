package com.example.userservice.service.impl;

import com.example.userservice.model.entity.User;
import com.example.userservice.model.dto.UserDto;
import com.example.userservice.model.repository.UserRepository;
import com.example.userservice.model.dto.RequestJoinUserDto;
import com.example.userservice.model.dto.ResponseJoinUserDto;
import com.example.userservice.model.dto.ResponseUserDto;
import com.example.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;

@Service
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDto joinUser(RequestJoinUserDto requestJoinUserDto) {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //Dto->Entity전환
        //아래 코드였지만 코드리팩토링을 위해 builder패턴으로 변경
        // User user=mapper.map(requestJoinUserDto,User.class);
        String encryptPassword=bCryptPasswordEncoder.encode(requestJoinUserDto.getPassword());
        requestJoinUserDto.setPassword(encryptPassword);
        User user=User.builder().requestJoinUserDto(requestJoinUserDto).build();


        //회원정보 저장
        userRepository.save(user);

        //저장결과 리턴을 위한 전환
        UserDto userDto=mapper.map(user,UserDto.class);
        return userDto;
    }



    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findById(userId);
        if( user ==null){
            throw new UsernameNotFoundException(userId);
        }
        return new org.springframework.security.core.userdetails.User(user.getLoginId(),user.getPassword(),
                true,true,true,true,
                new ArrayList<>());
    }

    @Override
    public Iterable<User> getUserByAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto getUser(String loginId) {
        //사용자 정보 하나 조회
        User user=userRepository.findById(loginId);

        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //entity->Dto로 변환
        UserDto userDto=mapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public UserDto getUserDetailsById(String userId) {
        User user=userRepository.findById(userId);
        System.out.println("진짜 로그 결과"+user);
        if( user== null){
            throw  new UsernameNotFoundException(userId);
        }
        UserDto userDto=new ModelMapper().map(user,UserDto.class);

        return userDto;
    }

    @Override
    public ResponseJoinUserDto responseJoinUser(UserDto userDto) {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseJoinUserDto responseJoinUserDto=mapper.map(userDto,ResponseJoinUserDto.class);
        return responseJoinUserDto;
    }

    @Override
    public ResponseUserDto findOneUser(UserDto userDto) {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseUserDto responseUser = mapper.map(userDto,ResponseUserDto.class);
        return responseUser;
    }



}
