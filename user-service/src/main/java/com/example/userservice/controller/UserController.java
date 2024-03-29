package com.example.userservice.controller;

import com.example.userservice.model.entity.Company;
import com.example.userservice.model.entity.User;
import com.example.userservice.model.dto.UserDto;
import com.example.userservice.model.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.example.userservice.model.dto.RequestJoinUserDto;
import com.example.userservice.model.dto.ResponseJoinUserDto;
import com.example.userservice.model.dto.ResponseUserDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private UserRepository userRepository;
    private UserService userService;


    //회원가입
    @PostMapping("/join")
    @ApiOperation(value="회원가입", notes="회원가입")
    public ResponseEntity<ResponseJoinUserDto> joinUser(@RequestBody RequestJoinUserDto requestJoinUserDto){
        //회원 추가
        UserDto joinUserDto=userService.joinUser(requestJoinUserDto);

        //결과 반환용 Dto
        ResponseJoinUserDto responseLoginUser=userService.responseJoinUser(joinUserDto);


        //결과 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(responseLoginUser);
    }

    //전체회원 조회
    @GetMapping("/getAllUsers")
    @ApiOperation(value="유저 전체 조회", notes="전체 유저 조회")

    public ResponseEntity<List<ResponseUserDto>> getAllUsers(){
        //전체 사용자 Iterable 타입으로 조회
        Iterable<User> userList=userService.getUserByAll();

        //List<ResponseUser>타입으로 리턴해야 하기 떄문에 여기 담아줄 거임.
        List<ResponseUserDto> result=new ArrayList<>();
        userList.forEach(u->{
            result.add(new ModelMapper().map(u,ResponseUserDto.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    //특정회원조회
    @GetMapping("/getUser/{loginId}")
    @ApiOperation(value="유저 단건 조회", notes="유저 하나하나 조회")
    public ResponseEntity<ResponseUserDto> getUser(@PathVariable("loginId") String loginId){
        //loginId를 통한 사용자 한명 조회
        UserDto userDto=userService.getUser(loginId);
        //사용자를 결과물로 리턴
        ResponseUserDto responseUser= userService.findOneUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    @GetMapping("/test")
    public void test(){
        List<Company> test=userRepository.listUser();
        System.out.println("company company"+ test);

    }


}
