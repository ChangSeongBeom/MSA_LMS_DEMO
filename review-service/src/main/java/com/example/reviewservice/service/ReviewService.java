package com.example.reviewservice.service;

import com.example.reviewservice.model.dto.ReviewDto;
import com.example.reviewservice.model.entity.Review;
import com.example.reviewservice.request.RequestReviewDto;
import com.example.reviewservice.response.ResponseReviewDto;

public interface ReviewService {

    ReviewDto joinReview(RequestReviewDto requestReviewDto);

    Iterable<Review> getReviewById(Long id);

    ResponseReviewDto responseReview(ReviewDto reviewDto);
}
