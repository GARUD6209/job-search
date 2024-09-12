package com.garud.job.service;

import com.garud.job.pojo.Job;
import org.springframework.stereotype.Service;

import java.util.List;


public interface JobService {

    List<Job> findAll();

    String create(Job job);

    Job findJobById(Long id) ;
}
