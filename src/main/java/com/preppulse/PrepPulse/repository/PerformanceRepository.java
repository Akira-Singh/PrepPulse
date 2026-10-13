package com.preppulse.PrepPulse.repository;

import com.preppulse.PrepPulse.entity.PerformanceMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository 
public interface PerformanceRepository extends JpaRepository<PerformanceMetric, Long>{
    //Custom database search query to track scores sorted by date automatically
    List<PerformanceMetric> findAllByOrderByLogDateDesc();
}
