package com.example.courseservice.service.implementation;


import com.example.courseservice.dto.ReviewDTO;
import com.example.courseservice.entity.Review;
import com.example.courseservice.exception.ResourceNotFoundException;
import com.example.courseservice.repository.ReviewRepo;
import com.example.courseservice.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepo reviewRepo;
    private final ModelMapper modelMapper;

    public ReviewServiceImpl(ReviewRepo reviewRepo, ModelMapper modelMapper) {
        this.reviewRepo = reviewRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        review = reviewRepo.save(review);
        return modelMapper.map(review, ReviewDTO.class);
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Review review = reviewRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
        modelMapper.map(reviewDTO, review);
        review = reviewRepo.save(review);
        return modelMapper.map(review, ReviewDTO.class);
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
            List<Review> reviews = reviewRepo.findAll();
            return reviews.stream().map(review -> modelMapper.map(review, ReviewDTO.class)).collect(Collectors.toList());
    }


}
