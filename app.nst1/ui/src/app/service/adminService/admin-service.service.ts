import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Admin } from 'src/app/model/admin';
import { MessageService } from '../messageService/message.service';

@Injectable({
  providedIn: 'root',
})
export class AdminServiceService {
  adminBaseURL = 'http://localhost:8080/admin/';

  constructor(
    private httpClient: HttpClient,
    private messageService: MessageService
  ) {}

  login(admin: Admin): Observable<Admin> {
    return this.httpClient
      .post<Admin>(this.adminBaseURL + 'login', admin)
      .pipe(
        tap((_) => this.log(`login admin email=${admin.adminEmail}`)),
        catchError(this.handleError<Admin>('login'))
      );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
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
    // TODO DELETE MESSAGE SERVICE
    // this.messageService.add(`HeroService: ${message}`);
    console.log('Message -> ', message);
  }
}
