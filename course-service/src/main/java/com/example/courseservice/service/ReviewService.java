package com.example.courseservice.service;


import com.example.courseservice.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(ReviewDTO reviewDTO);
    ReviewDTO updateReview(Long id, ReviewDTO reviewDTO);
    List<ReviewDTO> getAllReviews();
}
