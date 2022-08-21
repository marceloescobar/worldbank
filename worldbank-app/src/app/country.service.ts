import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {  Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';   


@Injectable({providedIn: 'root'})
export class CountryService {

   API_URL="http://localhost:8080/v1/api/countries";

  constructor(private httpClient: HttpClient) {
	}
     
	getAll(request): Observable<any> {
		const params = request;
		
    return this.httpClient.get(this.API_URL, {params})
      .pipe(
        tap(_ => console.log('obtendo paises')),
        catchError(this.handleError('obter paises', []))
      );
	}

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
     console.error(error); // log to console instead
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
