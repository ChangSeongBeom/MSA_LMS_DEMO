package com.example.reviewservice.service.impl;

import com.example.reviewservice.CustomMapper;
import com.example.reviewservice.model.dto.ReviewDto;
import com.example.reviewservice.model.entity.Review;
import com.example.reviewservice.model.repository.ReviewRepository;
import com.example.reviewservice.request.RequestReviewDto;
import com.example.reviewservice.response.ResponseReviewDto;
import com.example.reviewservice.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@AllArgsConstructor
public class ReviewImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    CustomMapper mapper=new CustomMapper();
    @Override
    public ReviewDto joinReview(RequestReviewDto requestReviewDto) {
        Date now= Calendar.getInstance().getTime();
        Review review=Review.builder()
                .loginId(requestReviewDto.getLoginId())
                .contentId(requestReviewDto.getContentId())
                .review(requestReviewDto.getReview())
                .creDtm(now)
                .build();
        reviewRepository.save(review);

        ReviewDto reviewDto=mapper.strictModelMapper().map(review,ReviewDto.class);
        return reviewDto;
    }

    @Override
    public Iterable<Review> getReviewById(Long id) {
        Iterable<Review> reviewList=reviewRepository.getReview(id);
        return reviewList;
    }

    @Override
    public ResponseReviewDto responseReview(ReviewDto reviewDto) {
        ResponseReviewDto responseReviewDto = mapper.strictModelMapper().map(reviewDto, ResponseReviewDto.class);
        return responseReviewDto;
    }
}
