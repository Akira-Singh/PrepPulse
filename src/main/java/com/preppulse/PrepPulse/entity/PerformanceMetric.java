package com.preppulse.PrepPulse.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity 
@Table(name = "performance_metrics")
public class PerformanceMetric {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "track_name",nullable = false)
    private String trackName; 

    @Column (name = "score")
    private Integer score; //Track percentage or numerical score metrics

    @Column(name = "total_questions")
    private Integer totalQuestions;

    @Column (name = "minutes_studied")
    private Integer minutesStudied;

    @Column (name = "log_date", nullable = false)
    private LocalDate logDate;

    //Default Constructor
    public PerformanceMetric() {}

    //Parameterized Constructor
    public PerformanceMetric(String trackName, Integer score, Integer totalQuestions, Integer minutesStudied, LocalDate logDate){
        this.trackName=trackName;
        this.score=score;
        this.totalQuestions=totalQuestions;
        this.minutesStudied=minutesStudied;
        this.logDate=logDate;
    }

    //Getters and Setters
    public Long getId() { return id; }
    public String getTrackName() { return trackName; }
    public void setTrackName(String trackName) { this.trackName = trackName; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public Integer getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(Integer totalQuestions) { this.totalQuestions = totalQuestions; }
    public Integer getMinutesStudied() { return minutesStudied; }
    public void setMinutesStudied(Integer minutesStudied) { this.minutesStudied = minutesStudied; }
    public LocalDate getLogDate() { return logDate; }
    public void setLogDate(LocalDate logDate) { this.logDate = logDate; }
}
