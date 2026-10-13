package com.preppulse.PrepPulse.entity;

import jakarta.persistence.*;
import java.time.LocalDate;;

@Entity 
@Table (name="job_applications")
public class JobApplication {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;
    
    @Column(nullable = false)
    private String jobTitle;

    private String portalUsed; //LinkedIn, Unstop, Naukri
    private String status ="APPLIED"; //APPLIED, INTERVIEW, REJECTED
    private LocalDate applicationDate = LocalDate.now();

    // Mandatory default constructor for JPA
    public JobApplication(){}

    //Constructor to quickly insert data rows
    public JobApplication(String companyName, String jobTitle, String portalUsed, String status){
        this.companyName=companyName;
        this.jobTitle=jobTitle;
        this.portalUsed=portalUsed;
        this.status=status;
    }

    //Getters and Setters
    public Long getId(){ return id; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName=companyName; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getPortalUsed() { return portalUsed; }
    public void setPortalUsed(String portalUsed) { this.portalUsed = portalUsed; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getApplicationDate() { return applicationDate; }
}
