package com.example.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseJoinUserDto {

    private String loginId;
    private String name;
    private String email;
    //private List<ResponseRegister> registers;

}