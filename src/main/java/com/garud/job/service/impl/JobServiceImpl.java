package com.garud.job.service.impl;

import com.garud.job.pojo.Job;
import com.garud.job.service.JobService;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class JobServiceImpl implements JobService {
    List<Job> jobs = new ArrayList<>();

    private Long nextId= 1L;

    @Override
    public List<Job> findAll() {

        return jobs;
    }

    @Override
    public String create(Job job) {
        job.setId(nextId++);
jobs.add(job);
        return "Job created successfully with id : " + job.getId();
    }

    @Override
    public Job findJobById(Long id)  {

        for (Job j : jobs) {
            if (j.getId().equals(id)) {
              return j;
            }
        }


      return null;

    }
}
