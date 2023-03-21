package com.example.review.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.review.entity.Review;
import com.example.review.exception.ResourceNotFoundException;
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

    @Override
    public List<Review> getReviewsByIsbn(Long isbn) {
		return reviewRepository.findByIsbnOrderByDateDesc(isbn);
    }

    @Override
    public Review getReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        return unwrapReview(review, id);
    }
    
    static Review unwrapReview(Optional<Review> review, Long id) {
        if (review.isPresent()) return review.get();
        else throw new ResourceNotFoundException("The review id '" + id + "' does not existis");
    }

}
