package com.example.review.service;

import java.util.List;

import com.example.review.entity.Review;

public interface ReviewService {
    List<Review> getReviews();
    List<Review> getReviewsByIsbn(Long isbn);
    Review getReviewById(Long id);
    Review createReview(Review review);
    Review updateReview(Long id, Review review);
    void deleteReview(Long id);
}
