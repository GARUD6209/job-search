package com.garud.job.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/companies/{companyId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // GET /companies/{companyId}/reviews - Retrieve all reviews for a company
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if (reviews != null && !reviews.isEmpty()) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET /companies/{companyId}/reviews/{reviewId} - Retrieve a review by its ID for a specific company
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReview(companyId, reviewId);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // POST /companies/{companyId}/reviews - Create a new review for a company
    @PostMapping
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean reviewRes = reviewService.create(companyId, review);
        if (reviewRes) {
            return new ResponseEntity<>("Review Created Successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to create Review",HttpStatus.BAD_REQUEST);
    }

    // PUT /companies/{companyId}/reviews/{reviewId} - Update an existing review
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review updateReview) {
        boolean isUpdated = reviewService.updateReview(companyId, reviewId, updateReview);
        if (isUpdated) {
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update Review",HttpStatus.NOT_FOUND);
    }

    // DELETE /companies/{companyId}/reviews/{reviewId} - Delete a review by its ID
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReviewById(companyId, reviewId);
        if (isDeleted) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete Review" ,HttpStatus.NOT_FOUND);
    }
}