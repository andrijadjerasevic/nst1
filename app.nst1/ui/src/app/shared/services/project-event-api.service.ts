import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProjectEvent } from '../models/projectEvent.model';

@Injectable({
  providedIn: 'root',
})
export class ProjectEventApiService {
  baseUrl = 'http://localhost:8080/projectEvent';
  constructor(private httpClient: HttpClient) {}

  saveProjectEvent(projecEvent: ProjectEvent) {
    return this.httpClient.post<ProjectEvent>(
      `${this.baseUrl}/save`,
      projecEvent
    );
  }
  getAll(): Observable<ProjectEvent[]> {
    return this.httpClient.get<ProjectEvent[]>(`${this.baseUrl}/get/all`);
  }
}
