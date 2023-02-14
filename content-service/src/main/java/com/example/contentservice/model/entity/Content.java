package com.example.contentservice.model.entity;

import com.example.contentservice.model.dto.RequestContentDto;
import com.example.contentservice.service.ContentService;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="content_id")
    private Long id;

    private String contentName;
    //사용자 로그인 아이디
    private String loginId;

    //설명
    private String description;

    //콘텐츠 길이
    private Long contentLength;

    //조회수
    private Long hitNum;

    //좋아요 수
    private Long likeNum;

    //별점수
    private double rating;

    private Long ratingPerson;
    //수용인원
    private Long capaNum;

    private Date creDtm;

    @OneToMany(mappedBy = "content")
    private List<Rating> ratingList=new ArrayList<>();

    @Builder
    public Content(String loginId,String contentName,String description,Long contentLength, Long hitNum
    ,Long likeNum,double rating, Long ratingPerson, Long capaNum,Date creDtm){
        this.loginId=loginId;
        this.contentName=contentName;
        this.description=description;
        this.contentLength=contentLength;
        this.capaNum=capaNum;
        this.hitNum=hitNum;
        this.likeNum=likeNum;
        this.rating=rating;
        this.creDtm=creDtm;
        this.ratingPerson=ratingPerson;
    }


    public Content setRating(Long ratingPerson, double rating){
        this.ratingPerson=ratingPerson;
        this.rating=rating;
        return this;
    }


}
