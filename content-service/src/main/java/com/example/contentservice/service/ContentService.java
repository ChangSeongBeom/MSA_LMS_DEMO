package com.example.contentservice.service;

import com.example.contentservice.model.dto.ContentDto;
import com.example.contentservice.model.dto.RequestContentDto;
import com.example.contentservice.model.dto.RequestRatingDto;
import com.example.contentservice.model.entity.Content;
import com.example.contentservice.response.ResponseContent;
import com.example.contentservice.response.ResponseRating;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


public interface ContentService   {
    ContentDto joinContent(RequestContentDto requestContentDto);

    ResponseContent responseJoinContent(ContentDto contentDto);

    Iterable<Content> getContentsByAll();

    ResponseRating updateRating(RequestRatingDto requestRatingDto);
}
