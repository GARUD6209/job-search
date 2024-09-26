package com.garud.job.review.impl;

import com.garud.job.company.Company;
import com.garud.job.company.CompanyService;
import com.garud.job.review.Review;
import com.garud.job.review.ReviewRepository;
import com.garud.job.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        // Find the company first to ensure it exists, then retrieve reviews

        return reviewRepository.findByCompanyId(companyId);

    }


    @Override
    public boolean create(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);

        if (company != null) {
            review.setCompany(company); // Set the company in the review
            reviewRepository.save(review);
            return true;// Save and return the created review
        }
        return false; // Return null if company not found
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updateReview) {

        if (companyService.getCompanyById(companyId) != null) {
            updateReview.setCompany(companyService.getCompanyById(companyId));
            updateReview.setId(reviewId);
            reviewRepository.save(updateReview);
            return true;
        }
        return false; // Return false if review not found
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId) !=null
                && reviewRepository.existsById(reviewId)) {

            Review review = reviewRepository.findById(reviewId).orElse(null);


            Company  company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);

            companyService.updateCompany(companyId,company);
            reviewRepository.deleteById(reviewId);
            return true;

        }
        return false; // Return false if review not found
    }
}
