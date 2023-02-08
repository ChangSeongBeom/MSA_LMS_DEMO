package com.example.userservice.domain;

import com.example.userservice.dto.RequestJoinUserDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;


    private String loginId;
    private String password;

    private String name;

    private String email;


    @Builder
    public User(RequestJoinUserDto requestJoinUserDto){
        this.loginId=requestJoinUserDto.getLoginId();
        this.password=requestJoinUserDto.getPassword();
        this.name=requestJoinUserDto.getName();
        this.email=requestJoinUserDto.getEmail();

    }
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Company> companies = new ArrayList<>();


}
