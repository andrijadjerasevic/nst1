import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { ProjectEvent } from '../model/projectEvent';

@Injectable({
  providedIn: 'root',
})
export class ProjectEventService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  private projectEventBaseUrl = 'http://localhost:8080/projectEvent';

  constructor(private http: HttpClient) {}

  // CREATE ...

  // FIND BY ID ...

  // FIND ALL ...
  getAllProjectEvents(): Observable<ProjectEvent[]> {
    return this.http
      .get<ProjectEvent[]>(this.projectEventBaseUrl + '/get/all')
      .pipe(
        tap((_) => this.log('fetched projectEvents')),
        catchError(this.handleError<ProjectEvent[]>('getAllProjectEvents', []))
      );
  }

  // UPDATE ...

  // DELETE ...

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
