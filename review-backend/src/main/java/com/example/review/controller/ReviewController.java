package com.example.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.review.entity.Review;
import com.example.review.service.ReviewService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;

	//get all reviews
	@GetMapping("/reviews")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Review>> getReviews() {
		return new ResponseEntity<>(reviewService.getReviews(), HttpStatus.OK);
	}
	
	//get reviews by ISBN
	@GetMapping("/reviews/{isbn}")
	public ResponseEntity<List<Review>> getReviewsByIsbn(@PathVariable Long isbn) {
		return new ResponseEntity<>(reviewService.getReviewsByIsbn(isbn), HttpStatus.OK);
	}

	//get review by ID
	@GetMapping("/review/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
		return new ResponseEntity<>(reviewService.getReviewById(id), HttpStatus.OK);
	}
	
	//create new review
	@PostMapping("/review")
	public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.createReview(review), HttpStatus.OK);
	}
	
	//update review by ID
	@PutMapping("/review/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        return new ResponseEntity<>(reviewService.updateReview(id, review), HttpStatus.CREATED);
	}
	
	//delete review by ID
	@DeleteMapping("/review/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
