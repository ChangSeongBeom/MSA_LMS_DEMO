package com.example.reviewservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseReviewDto {
    private String loginId;

    private Long contentId;
    private String review;

    private Date creDtm;
}
