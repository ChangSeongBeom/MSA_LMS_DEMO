package com.example.userservice.controller;

import com.example.userservice.domain.User;
import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.RequestJoinUser;
import com.example.userservice.vo.RequestLoginUser;
import com.example.userservice.vo.ResponseLoginUser;
import com.example.userservice.vo.ResponseUser;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/test")
    private String test(){
        return "testtes";
    }

    @PostMapping("/join")
    public ResponseEntity<ResponseLoginUser> joinUser(@RequestBody RequestJoinUser requestJoinUser){
        System.out.println("===requestLoginUser==="+requestJoinUser);
        ModelMapper mapper=new ModelMapper();
        // 컨트롤러 온 요청 현 번환 VO->DTO
        UserDto userDto= mapper.map(requestJoinUser,UserDto.class);
        System.out.println("===userDto==="+userDto);
        //회원 저장
        userService.joinUser(userDto);

        //요청 결과 변환 DTO->Entity
        ResponseLoginUser responseLoginUser=mapper.map(userDto,ResponseLoginUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseLoginUser);

    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<ResponseUser>> getAllUsers(){
        //전체 사용자 Iterable 타입으로 조회
        Iterable<User> userList=userService.getUserByAll();

        //List<ResponseUser>타입으로 리턴해야 하기 떄문에 여기 담아줄 거임.
        List<ResponseUser> result=new ArrayList<>();

        userList.forEach(u->{
            result.add(new ModelMapper().map(u,ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String loginId){
        UserDto userDto=userService.getUser(loginId);
        ResponseUser findOneUser=new ModelMapper().map(userDto,ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(findOneUser);
    }


}
