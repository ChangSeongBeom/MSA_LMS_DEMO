package com.example.userservice.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private String loginId;

    private String name;

    private String password;
    private String email;

    private Date creDtm;

    //private List<ResponseRegister> registers;


}
