package com.example.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.review.entity.Review;
import com.example.review.exception.ResourceNotFoundException;
import com.example.review.repository.ReviewRepository;
import com.example.review.service.ReviewService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ReviewController {
	
	@Autowired
	private ReviewRepository reviewRepo;
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
		Review review = reviewRepo.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Review not found"));
		return ResponseEntity.ok(review);                 
	}
	
	//create new review
	@PostMapping("/review")
	public Review newReview(@RequestBody Review review) {
		long date = System.currentTimeMillis();
		review.setDate(date);
		return reviewRepo.save(review);
	}
	
	//update review by ID
	@PutMapping("/review/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
		Review r = reviewRepo.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Review not found"));
		r.updateFields(review);
		Review updatedReview = reviewRepo.save(r);
		return ResponseEntity.ok(updatedReview); 
	}
	
	//delete review by ID
	@DeleteMapping("/review/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteReview(@PathVariable Long id) {
		if (!reviewRepo.existsById(id)) {
			throw new ResourceNotFoundException("Review not found");
		}
		reviewRepo.deleteById(id);
	    return "The review with id: "+ id +" is removed from the database.";
	}
	
}
