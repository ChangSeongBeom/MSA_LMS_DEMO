package com.example.reviewservice.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestReviewDto {
    private String loginId;

    private Long contentId;
    private String review;

}
