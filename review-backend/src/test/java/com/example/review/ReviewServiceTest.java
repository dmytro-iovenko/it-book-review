package com.example.review;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
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

    @Test
    public void updateReviewTest() {
        Review review =  new Review(1001643027241L, "Sophia Williams", "Confusing structure, outdated examples.", 1640917758000L, 2);
        when(reviewRepository.findById(0L)).thenReturn(Optional.of(review));

        String newName = "Alex Rodriguez";
        review.setName(newName);
        reviewService.updateReview(0L, review);
        verify(reviewRepository, times(1)).save(review);

        Review updatedReview = reviewService.getReviewById(0L);
        assertEquals(newName, updatedReview.getName());
    }

    @Test
    public void deleteReviewTest() {
        when(reviewRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(reviewRepository).deleteById(anyLong());

        reviewService.deleteReview(anyLong());
        verify(reviewRepository, times(1)).deleteById(anyLong());
    }
}