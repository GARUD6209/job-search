package com.garud.job.controller;

import com.garud.job.pojo.Job;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class JobController {
    List<Job> jobs = new ArrayList<>();

    @GetMapping("/jobs")
    public List<Job> findAll(){

        return jobs;
    }

    @PostMapping("/add" )
    public String createJob(@RequestBody Job job){

        jobs.add(job);

        return "job created successfully";
    }
}
