import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from '../models/admin.model';

@Injectable({
  providedIn: 'root',
})
export class AdminApiService {
  constructor(private httpClient: HttpClient) {}

  login(admin: Admin): Observable<Admin> {
    return this.httpClient.post<Admin>(
      'http://localhost:8080/admin/login',
      admin
    );
  }
}
