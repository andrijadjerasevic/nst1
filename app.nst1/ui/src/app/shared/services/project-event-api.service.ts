import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProjectEvent } from '../models/projectEvent.model';

@Injectable({
  providedIn: 'root',
})
export class ProjectEventApiService {
  constructor(private httpClient: HttpClient) {}

  getAll(): Observable<ProjectEvent[]> {
    return this.httpClient.get<ProjectEvent[]>(
      'http://localhost:8080/projectEvent/get/all'
    );
  }
}
