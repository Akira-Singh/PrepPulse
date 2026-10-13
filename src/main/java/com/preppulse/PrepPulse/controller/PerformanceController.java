package com.preppulse.PrepPulse.controller;

import com.preppulse.PrepPulse.entity.PerformanceMetric;
import com.preppulse.PrepPulse.repository.PerformanceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController 
@RequestMapping ("/api/v1/performance")
@CrossOrigin (origins = "http://localhost:4200") //CLears the CORS security path for Angular
public class PerformanceController {
    
    private final PerformanceRepository performanceRepository;

    public PerformanceController(PerformanceRepository performanceRepository){
        this.performanceRepository=performanceRepository;
    }

     /**
     * SAVE METRIC ENDPOINT
     * Route: POST http://localhost:8080/api/v1/performance/log
     */
    @PostMapping ("/log")
    public ResponseEntity<PerformanceMetric> logDailyMetric(@RequestBody Map<String, Object> payload){
        String trackName = (String) payload.get("trackName");
        Integer score = (Integer) payload.get("score");
        Integer totalQuestions = (Integer) payload.get("totalQuestions");
        Integer minutesStudied = (Integer) payload.get("minutesStudied");

        PerformanceMetric metric = new PerformanceMetric(
            trackName,
            score,
            totalQuestions,
            minutesStudied,
            LocalDate.now() //Automatically stamps today's date
        );

        PerformanceMetric savedMetric = performanceRepository.save(metric);
        return ResponseEntity.ok(savedMetric);
    }

    /**
     * FETCH ALL METRICS ENDPOINT (For Analytics Charts)
     * Route: GET http://localhost:8080/api/v1/performance/history
     */
    @GetMapping ("/history")
    public ResponseEntity<List<PerformanceMetric>> getPetformanceHistory(){
        List<PerformanceMetric> history = performanceRepository.findAllByOrderByLogDateDesc();
        return ResponseEntity.ok(history);
    }
}
