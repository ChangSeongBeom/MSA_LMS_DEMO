package com.example.reviewservice.controller;

import com.example.reviewservice.CustomMapper;
import com.example.reviewservice.model.dto.ReviewDto;
import com.example.reviewservice.model.entity.Review;
import com.example.reviewservice.model.repository.ReviewRepository;
import com.example.reviewservice.request.RequestReviewDto;
import com.example.reviewservice.response.ResponseReviewDto;
import com.example.reviewservice.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
@AllArgsConstructor
@CrossOrigin(origins ="*")
public class ReviewController {
    CustomMapper mapper=new CustomMapper();
    private ReviewRepository reviewRepository;
    private ReviewService reviewService;

    @GetMapping("/welcome")
    public String welcomeTest(){
        return " review welcome";
    }

    @PostMapping("/joinReview")
    public ResponseEntity<ResponseReviewDto> joinUser(@RequestBody RequestReviewDto requestReviewDto){
        ReviewDto reviewDto= reviewService.joinReview(requestReviewDto);

        ResponseReviewDto responseReviewDto =reviewService.responseReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseReviewDto);
    }

    @GetMapping("/getOneContentReview/{contentId}")
    public ResponseEntity<List<ResponseReviewDto>> getOneContentReview(@PathVariable("contentId") Long contentId){
       Iterable<Review> reviewList =  reviewService.getReviewById(contentId);
       List<ResponseReviewDto> result=new ArrayList<>();
       reviewList.forEach(r->{
           result.add(mapper.strictModelMapper().map(r,ResponseReviewDto.class));
       });
       return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
