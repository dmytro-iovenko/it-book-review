package com.example.review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.review.entity.Review;
import com.example.review.repository.ReviewRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    
    ReviewRepository reviewRepository;

    @Override
    public List<Review> getReviews() {
        return (List<Review>)reviewRepository.findAll();
    }
    
}
