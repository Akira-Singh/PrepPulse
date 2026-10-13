package com.preppulse.PrepPulse.controller;

import com.preppulse.PrepPulse.service.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/ai")
@CrossOrigin(origins = "*") // Prepares for a clean, cross-platform React interface connection later
public class AIController {

    private final AIService aiService;

    // Dependency Injection: Spring Boot wires our upgraded service instance automatically
    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    /**
     * DYNAMIC MODULE 1 AI ENGINES: Resume Parser & Keyword Tailor
     * Route: POST http://localhost:8080/api/v1/ai/tailor
     */
    @PostMapping("/tailor")
    public ResponseEntity<Map<String, String>> tailorResumeForJob(@RequestBody Map<String, String> payload) {
        String jobDescription = payload.get("jobDescription");
        String rawResumeText = payload.get("resumeText");
        
        // Validation boundary checks to prevent null transmission errors
        if (jobDescription == null || jobDescription.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Target Job description text cannot be empty."));
        }
        
        if (rawResumeText == null || rawResumeText.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Input Resume text profile cannot be empty."));
        }

        String aiSuggestions = aiService.generateResumeModifications(jobDescription, rawResumeText);
        return ResponseEntity.ok(Map.of("resumeModifications", aiSuggestions));
    }
}
