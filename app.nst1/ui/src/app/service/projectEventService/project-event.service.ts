import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ProjectEvent } from 'src/app/model/projectEvent';

const baseUrl = 'http://localhost:8080/projectEvent';

@Injectable({
  providedIn: 'root',
})
export class ProjectEventService {
  
  constructor(private http: HttpClient) {}

  // CREATE
  saveProjectEvent(data: ProjectEvent): Observable<ProjectEvent> {
    return this.http.post<ProjectEvent>(`${baseUrl}/save`, data);
  }

  // READ
  getProjectEvent(id: string): Observable<ProjectEvent> {
    return this.http.get(`${baseUrl}/get/${id}`);
  }

  // REAL ALL
  getAllProjectEvents(): Observable<ProjectEvent[]> {
    return this.http.get<ProjectEvent[]>(`${baseUrl}/get/all`);
  }

  // UPDATE
  updateProjectEvent(data: ProjectEvent): Observable<ProjectEvent> {
    // send id in order to update
    return this.http.post<ProjectEvent>(`${baseUrl}/update`, data);
  }

  // DELETE
  // returns a string message
  deleteProjectEvent(id: string): Observable<any> {
    return this.http.get<any>(`${baseUrl}/delete/${id}`);
  }

  deleteAll(): Observable<ProjectEvent> {
    // TODO:
    return of();
  }

  findByTitleProjectEvent(title: string): Observable<ProjectEvent[]> {
    // TODO:
    return of();
  }
}
