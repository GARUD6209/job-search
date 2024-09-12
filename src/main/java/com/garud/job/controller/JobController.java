package com.garud.job.controller;

import com.garud.job.pojo.Job;
import com.garud.job.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class JobController {
    private  JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public List<Job> findAll(){

        return jobService.findAll();
    }

    @GetMapping("/jobs/{id}")
    public Job findJobById(@PathVariable Long id)  {
        Job job = jobService.findJobById(id);

        if(job !=null){
        return job;}
        return new Job(1L,"testTitle","testDes", "2000"
                ,"2000","testLocation");
    }

    @PostMapping("/add" )
    public String createJob(@RequestBody Job job){



        return jobService.create(job);
    }
}
