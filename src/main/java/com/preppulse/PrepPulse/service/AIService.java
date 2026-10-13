package com.preppulse.PrepPulse.service;

import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.prompt.Prompt;

@Service 
public class AIService {
    
    private final GoogleGenAiChatModel chatModel;

    //Spring automatically wires the Gemini connection engine using your properties API key
    public AIService(GoogleGenAiChatModel chatModel){
        this.chatModel=chatModel;
    }

    public String generateResumeModifications(String jobDescription, String rawResumeText){
       String prompt = String.format(
            "You are an expert ATS (Applicant Tracking System) recruiter. " +
            "I am providing my current resume text data:\n\"\"\"\n%s\n\"\"\"\n\n" +
            "And here is the target job description:\n\"\"\"\n%s\n\"\"\"\n\n" +
            "Read my resume, extract my current skills, and compare them against the job requirements. " +
            "Provide a punchy list of exactly 3 missing technical keywords, frameworks, or bullet point modifications " +
            "I should add to my resume to maximize my matching score for this specific role.",
            rawResumeText, 
            jobDescription
        );

        // ABSOLUTE BYPASS: Hardcoding the model options directly to avoid properties file bugs
        GoogleGenAiChatOptions options = GoogleGenAiChatOptions.builder()
                .model("gemini-3.8-flash")
                .build();
        
        // FIXED: Wrapping text and options inside a structured Prompt object
        Prompt unifiedPrompt = new Prompt(prompt, options);

        return chatModel.call(unifiedPrompt).getResult().getOutput().getText();
    }
}
