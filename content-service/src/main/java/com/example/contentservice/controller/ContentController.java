package com.example.contentservice.controller;

import com.example.contentservice.CustomMapper;
import com.example.contentservice.model.dto.ContentDto;
import com.example.contentservice.model.dto.RequestContentDto;
import com.example.contentservice.model.dto.RequestRatingDto;
import com.example.contentservice.model.entity.Content;
import com.example.contentservice.model.repository.ContentRepository;
import com.example.contentservice.response.ResponseContent;
import com.example.contentservice.response.ResponseRating;
import com.example.contentservice.service.ContentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080" })
public class ContentController {

    private ContentRepository contentRepository;
    private ContentService contentService;
    CustomMapper mapper=new CustomMapper();

    @GetMapping("/welcome")
    public String welcomeTest(){
        return " content welcome";
    }

    //콘텐츠 추가
    @PostMapping("/joinContent")
    public ResponseEntity<ResponseContent> joinContent (@RequestBody RequestContentDto requestContentDto){

        //콘텐츠 추가
        ContentDto contentDto=contentService.joinContent(requestContentDto);

        //결과 반환dto->response
        ResponseContent responseContent=contentService.responseJoinContent(contentDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseContent);
    }

    //모든 콘텐츠 조회
    @GetMapping("/getAllContents")
    public ResponseEntity<List<ResponseContent>> getAllContents (){
        //전체 리스트
        Iterable<Content> contentsList = contentService.getContentsByAll();

        List<ResponseContent> responseContentList=new ArrayList<>();
        contentsList.forEach(c->{
            responseContentList.add(mapper.strictModelMapper().map(c, ResponseContent.class));
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(responseContentList);

    }

    //평가 점수 업데이트
    @PostMapping("/updateRating")
    public ResponseEntity<ResponseRating> updateRating(@RequestBody RequestRatingDto requestRatingDto){
        ResponseRating resonseRating = contentService.updateRating(requestRatingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resonseRating);
    }



}
