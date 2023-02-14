package com.example.contentservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseContent {
    private Long id;
    private String loginId;

    private String contentName;
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
    //수용인원
    private Long capaNum;

    //등록날짜
    private Date creDtm;
}
