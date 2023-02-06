package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.vo.RequestJoinUserDto;
import com.example.userservice.vo.ResponseJoinUserDto;
import com.example.userservice.vo.ResponseUserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto joinUser(RequestJoinUserDto requestJoinUserDto) {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //Dto->Entity전환
        //아래 코드였지만 코드리팩토링을 위해 builder패턴으로 변경
        // User user=mapper.map(requestJoinUserDto,User.class);
        User user=User.builder().requestJoinUserDto(requestJoinUserDto).build();
        user.setPassword(bCryptPasswordEncoder.encode(requestJoinUserDto.getPassword()));

        //회원정보 저장
        userRepository.save(user);

        //저장결과 리턴을 위한 전환
        UserDto userDto=mapper.map(user,UserDto.class);
        return userDto;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if( user ==null){
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),
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
    public UserDto getUserDetailsByEmail(String userName) {
        User user=userRepository.findByEmail(userName);
        System.out.println("진짜 로그 결과"+user);
        if( user== null){
            throw  new UsernameNotFoundException(userName);
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
