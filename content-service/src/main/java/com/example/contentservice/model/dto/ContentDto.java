package com.example.contentservice.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ContentDto {

    private Long id;
    private String contentName;
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

    //등록날짜
    private Date creDtm;
}
