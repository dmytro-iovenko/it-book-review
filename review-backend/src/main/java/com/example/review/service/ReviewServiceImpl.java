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

    @Override
    public Review createReview(Review review) {
		long date = System.currentTimeMillis();
		review.setDate(date);
        return reviewRepository.save(review);
    }
    
    @Override
    public Review updateReview(Long id, Review updatedReview) {
        Review review = unwrapReview(reviewRepository.findById(id), id);
		review.updateFields(updatedReview);
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long id) {
		if (reviewRepository.existsById(id)) reviewRepository.deleteById(id);
        else throw new ResourceNotFoundException(id, Review.class);
    }
    
    static Review unwrapReview(Optional<Review> review, Long id) {
        if (review.isPresent()) return review.get();
        else throw new ResourceNotFoundException(id, Review.class);
    }

}
