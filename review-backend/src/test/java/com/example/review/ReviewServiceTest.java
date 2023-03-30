package com.example.review;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.review.entity.Review;
import com.example.review.repository.ReviewRepository;
import com.example.review.service.ReviewServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;
    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    public void getAllReviewsTest() {
        when(reviewRepository.findAll()).thenReturn(Arrays.asList(
            new Review(1001643027241L, "Emma Johnson", "Informative content, but the presentation could be clearer.", 1643700125000L, 3),
            new Review(9781718501089L, "Alex Rodriguez", "Great insights! Easy to follow, practical examples.", 1643247002000L, 5),
            new Review(1001643027241L, "Sophia Williams", "Confusing structure, outdated examples.", 1640917758000L, 2)
        ));

        List<Review> result = reviewService.getReviews();

        assertEquals("Emma Johnson", result.get(0).getName());
        assertEquals(Long.valueOf(9781718501089L), result.get(1).getIsbn());
        assertEquals("Confusing structure, outdated examples.", result.get(2).getReview());
    }

    @Test
    public void getReviewsByIsbnTest() {
        Review review = new Review(1001643027241L, "Emma Johnson", "Informative content, but the presentation could be clearer.", 1643700125000L, 3);
        when(reviewRepository.findAll()).thenReturn(Arrays.asList(review));
        when(reviewRepository.findByIsbnOrderByDateDesc(1001643027241L)).thenReturn(Arrays.asList(review));

        Long isbn = review.getIsbn();        
        List<Review> result = reviewService.getReviewsByIsbn(isbn);

        assertEquals(review, result.get(0));
    }

    @Test
    public void getReviewByIdTest() {
        Optional<Review> review = Optional.of(new Review(1001643027241L, "Emma Johnson", "Informative content, but the presentation could be clearer.", 1643700125000L, 3));
        when(reviewRepository.findById(0L)).thenReturn(review);

        Review result = reviewService.getReviewById(0L);

        assertEquals(review.get(), result);
    }

    @Test
    public void createReviewTest() {
        Review newReview =  new Review(1001643027241L, "Sophia Williams", "Confusing structure, outdated examples.", 1640917758000L, 2);
        reviewService.createReview(newReview);
        verify(reviewRepository, times(1)).save(newReview);

    }
}
/*
 *
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

 */