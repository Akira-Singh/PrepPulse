import { Component , OnInit, signal} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

interface PerformanceMetric {
  id: number;
  trackName: string;
  score: number;
  totalQuestions: number;
  minutesStudied: number;
  logDate: string;
}

@Component({
  selector: 'app-analytics',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './analytics.html',
  styleUrl: './analytics.css',
})
export class AnalyticsComponent implements OnInit{
  private apiUrl = 'http://localhost:8080/api/v1/performance';

  //State Management using Modern Angular Signals
  metricsHistory = signal<PerformanceMetric[]>([]);
  trackName = '';
  score: number | null = null;
  totalQuestions: number | null = null;
  minutesStudied: number | null = null;

  isLoading = false;
  successMessage = '';

  constructor(private http: HttpClient){}

  ngOnInit(): void {
    this.fetchMetricsHistory();
  }

  fetchMetricsHistory(): void{
    this.http.get<PerformanceMetric[]>(`${this.apiUrl}/history`).subscribe({
      next: (data) => this.metricsHistory.set(data),
      error: (err) => console.error('Failed to fetch tracking history:',err)
    });
  }

  saveMetric(): void{
    if(!this.trackName.trim() || this.score===null || this.minutesStudied===null){
      alert('Please fill out all metric entry values first.');
      return;
    }

    this.isLoading=true;
    const payload = {
      trackName: this.trackName.trim(),
      score: this.score,
      totalQuestions: this.totalQuestions || 0,
      minutesStudied: this.minutesStudied
    };

    this.http.post(`${this.apiUrl}/log`,payload).subscribe({
      next: () => {
        this.successMessage = '📈 Milestone metrics logged into PostgreSQL successfully!';
        this.trackName = '';
        this.score = null;
        this.totalQuestions = null;
        this.minutesStudied = null;
        this.isLoading = false;
        this.fetchMetricsHistory(); //Auto- refresh table view
        setTimeout(() => this.successMessage = '', 4000);
      },
      error: (err) => {
        console.error('Falied to save metrics: ',err);
        this.isLoading=false;
      }
    });
  }
}
