package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto joinUser(UserDto userDto) {

        ModelMapper mapper=new ModelMapper();
        //DTO-> Entity변환 , 비밀번호 암호화
        User user=mapper.map(userDto,User.class);
        //아이디는 차후에 암호화 할 필요있음.
      //  user.setLoginId(UUID.randomUUID().toString());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        //delpoy될 값의 id를 구현할떄 detach persist에러가 나오므로 널처리
        user.setId(null);
        //회원 정보 저장

        userRepository.save(user);

        //리턴 타입 변환
        UserDto responseUserDto=mapper.map(user,UserDto.class);

        return responseUserDto;
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
        User findUser=userRepository.findById(loginId);

        //entity->Dto로 변환
        UserDto findUserDto=new ModelMapper().map(findUser,UserDto.class);
        return findUserDto;
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


}
