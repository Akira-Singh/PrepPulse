package com.preppulse.PrepPulse.controller;

import com.preppulse.PrepPulse.entity.JobApplication;
import com.preppulse.PrepPulse.repository.JobRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController 
@RequestMapping ("/api/v1")
public class JobController {
    private final JobRepository jobRepository;

    //Spring automated dependency injection
    public JobController(JobRepository jobRepository){
        this.jobRepository=jobRepository;
    }
    
    @GetMapping("/jobs")
    public List<JobApplication> getAllTrackedJobs(){
        //Seeds intial mock rows if your PostgreSQL table is fresh
        if(jobRepository.count()==0) {
            jobRepository.save(new JobApplication("TCS","Software Engineer(Prime Tier)","NQT Portal","INTERVIEW SCHEDULED"));
            jobRepository.save(new JobApplication("Google", "Associate SWE via Challenge", "Unstop", "APPLIED"));
        }
        return jobRepository.findAll();
    }
}
