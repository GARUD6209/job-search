package com.garud.job.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByCompanyId(Long companyId);

    Optional<Review> findByIdAndCompanyId(Long reviewId, Long companyId);

    boolean existsByIdAndCompanyId(Long reviewId, Long companyId);


}
