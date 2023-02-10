package com.example.userservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestJoinUserDto {

    private String loginId;
    private String name;
    private String password;
    private String email;

}
