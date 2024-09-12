package com.garud.job.service;

import com.garud.job.pojo.Job;

import java.util.List;


public interface JobService {

    List<Job> findAll();

    String create(Job job);

    Job findJobById(Long id) ;

    String deleteJobById(Long id);

    boolean updateJob(Long id, Job updateJob);
}
