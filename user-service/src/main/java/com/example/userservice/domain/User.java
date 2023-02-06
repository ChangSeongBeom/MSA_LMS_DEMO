package com.example.userservice.domain;

import com.example.userservice.dto.UserDto;
import com.example.userservice.vo.RequestJoinUserDto;
import lombok.*;

import javax.persistence.*;



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

    public User setPassword( String password){
        this.password=password;
        return this;
    }
    @Builder
    public User(RequestJoinUserDto requestJoinUserDto){
        this.loginId=requestJoinUserDto.getLoginId();
        this.password=requestJoinUserDto.getPassword();
        this.name=requestJoinUserDto.getName();
        this.email=requestJoinUserDto.getEmail();

    }
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    private Company company;


}
