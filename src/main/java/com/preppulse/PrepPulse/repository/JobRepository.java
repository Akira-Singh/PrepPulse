package com.preppulse.PrepPulse.repository;

import com.preppulse.PrepPulse.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface JobRepository extends JpaRepository<JobApplication, Long>{
    
}
