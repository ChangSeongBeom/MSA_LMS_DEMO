package com.example.contentservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestRatingDto {
    private Long id;
    private double rating;
}
