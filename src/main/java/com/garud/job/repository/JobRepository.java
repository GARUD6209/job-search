package com.garud.job.repository;

import com.garud.job.pojo.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
