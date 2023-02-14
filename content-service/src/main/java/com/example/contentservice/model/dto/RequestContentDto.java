package com.example.contentservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestContentDto {

    private String loginId;

    private String contentName;
    //설명
    private String description;
    //콘텐츠 길이
    private Long contentLength;
    //수용인원
    private Long capaNum;
}
