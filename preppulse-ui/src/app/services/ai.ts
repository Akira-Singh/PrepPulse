import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AiService {

  //Connects directly to your running Java Spring Boot AI post endpoint
  private apiURL ='http://localhost:8080/api/v1/ai/tailor';
  
  constructor(private http: HttpClient) { }

   /**
   * Post both the job description and raw resume text parameters straight to the backend
   * @param jobDescription Target role requirements string block
   * @param resumeText Dynamic user resume text input profile
   */
  getResumeModifications(jobDescription: string, resumeText: string): Observable<any> {
    const payload = {jobDescription, resumeText};
    return this.http.post<any>(this.apiURL,payload);
  }
}
