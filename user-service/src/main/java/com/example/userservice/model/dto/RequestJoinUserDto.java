package com.example.userservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestJoinUserDto implements Serializable {

    private String loginId;
    private String name;
    private String password;
    private String email;

}
