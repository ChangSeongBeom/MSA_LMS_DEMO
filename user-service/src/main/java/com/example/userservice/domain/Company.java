package com.example.userservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="company_id")
    private Long id;

    private String companyCode;

    private String companyName;

    @OneToOne(mappedBy = "company", fetch = FetchType.LAZY)
    private User user;
}
