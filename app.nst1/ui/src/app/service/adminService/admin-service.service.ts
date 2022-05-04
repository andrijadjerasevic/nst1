import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Admin } from 'src/app/model/admin';

@Injectable({
  providedIn: 'root',
})
export class AdminServiceService {
  adminBaseURL = 'http://localhost:8080/admin/';

  constructor(private httpClient: HttpClient) {}

  login(admin: Admin): Observable<Admin> {
    return this.httpClient.post<Admin>(this.adminBaseURL + 'login', admin);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string) {
    console.log('Message -> ', message);
  }
}
