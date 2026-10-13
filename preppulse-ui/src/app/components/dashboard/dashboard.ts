import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AiService } from '../../services/ai'; //Links directly to your ai.ts client file

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class DashboardComponent{
  //Input Bindings State
  jobDescription: string = '';
  resumeText: string = '';
  aiFeedback: string = '';
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(private aiService: AiService) {}

  submitToAi(): void{
    if(!this.jobDescription.trim() || !this.resumeText.trim()){
      this.errorMessage = 'Please provide both a job description and your resume text data first.';
      return;
    }
    this.isLoading = true;
    this.errorMessage = '';
    this.aiFeedback = '';

    //Fire data payload over to ypur Spring Boot REST server
    this.aiService.getResumeModifications(this.jobDescription, this.resumeText).subscribe({
      next: (response) => {
        let rawText = response.resumeModifications;
        this.aiFeedback = this.cleanMarkdownText(rawText);
        this.isLoading = false;
      },
      error: (error) => {
        console.error('API Error details:', error);
        this.errorMessage = 'Failed to connect to the PrepPulse AI engine. Verify your Java server is running on port 8080.';
        this.isLoading = false;
      }
    });
  }

  //Helper method to strip markdown syntax and formatting characters
  private cleanMarkdownText(text: string): string{
    if(!text) return '';
    return text
      .replace(/\*\*(.*?)\*\//g, '$1') // Removes double asterisks ** for bold lines
      .replace(/\*(.*?)\*/g, '$1')     // Removes single asterisks * for emphasis
      .replace(/\*/g, '')              // ABSOLUTE CLEANUP: Wipes out any remaining single asterisks anywhere in the text
      .replace(/^\s*-\s+/gm, '• ');    // Replaces raw dashes with neat bullet points
  }
}
