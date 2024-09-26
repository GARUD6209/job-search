package com.garud.job.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    Review getReview(Long companyId, Long reviewId);

    boolean create(Long companyId, Review review);

    boolean updateReview(Long companyId, Long reviewId, Review updateReview);

    boolean deleteReviewById(Long companyId, Long reviewId);
}
