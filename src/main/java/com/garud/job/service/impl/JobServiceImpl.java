package com.garud.job.service.impl;

import com.garud.job.pojo.Job;
import com.garud.job.service.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public String deleteJobById(Long id) {

        for (Job j : jobs) {
            if (j.getId().equals(id)) {
                jobs.remove(j);
                return "job deleted successfully";
            }
        }

        return null;
    }

    @Override
    public boolean updateJob(Long id, Job updateJob) {
        updateJob.setId(id);

        for (Job job : jobs) {
            if (job.getId().equals(id)) {

                job.setDescription(updateJob.getDescription());
                job.setTitle(updateJob.getTitle());
                job.setMinSalary(updateJob.getMinSalary());
                job.setMaxSalary(updateJob.getMaxSalary());
                job.setLocation(updateJob.getLocation());
                return true;
            }
        }

        return false;
    }
}
