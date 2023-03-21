package com.example.review.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.review.entity.Review;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
	List<Review> findByIsbnOrderByDateDesc(long isbn);
	Optional<Review> findById(Long id);
}
