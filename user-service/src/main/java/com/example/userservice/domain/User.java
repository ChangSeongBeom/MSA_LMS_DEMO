package com.example.userservice.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
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

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    private Company company;


}
