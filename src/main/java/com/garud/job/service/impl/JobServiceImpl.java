package com.garud.job.service.impl;

import com.garud.job.pojo.Job;
import com.garud.job.repository.JobRepository;
import com.garud.job.service.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
//    List<Job> jobs = new ArrayList<>();

    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

//    private Long nextId= 1L;

    @Override
    public List<Job> findAll() {

        return jobRepository.findAll();
    }

    @Override
    public String create(Job job) {

      try {

          jobRepository.save(job);
          return "Job created successfully." ;
      }catch (Exception ex){
          return "job not created.";
      }
    }

    @Override
    public Job findJobById(Long id)  {

       return jobRepository.findById(id).orElse(null);

    }

    @Override
    public Boolean deleteJobById(Long id) {

       try {
           jobRepository.deleteById(id);
           return true;
       }catch (Exception ex){
           return false;
       }
    }

    @Override
    public boolean updateJob(Long id, Job updateJob) {

        Optional<Job> jobOptional = jobRepository.findById(id);


            if (jobOptional.isPresent()) {
                Job job = jobOptional.get();

                job.setDescription(updateJob.getDescription());
                job.setTitle(updateJob.getTitle());
                job.setMinSalary(updateJob.getMinSalary());
                job.setMaxSalary(updateJob.getMaxSalary());
                job.setLocation(updateJob.getLocation());
                jobRepository.save(job);
                return true;
            }


        return false;
    }
}
