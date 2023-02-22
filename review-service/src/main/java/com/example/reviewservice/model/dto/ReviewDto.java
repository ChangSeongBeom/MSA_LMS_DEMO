package com.example.reviewservice.model.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class ReviewDto {

    private String loginId;

    private Long contentId;
    private String review;

    private Date creDtm;
}
