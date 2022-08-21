import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {  Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';  
import { BaseService } from './base.service';
import { AppConstants } from './common/app.constants';  

@Injectable({providedIn: 'root'})
export class IndicatorService extends BaseService{

  constructor(private httpClient: HttpClient) {
    super();
  }
     
	findIndicatorByCountry(id:string, request): Observable<any> {
		const params = request;

    return this.httpClient.get(AppConstants.API_BASE_URL + `indicators/${id}` , {params})
      .pipe(
        tap(_ => console.log('obtendo indicadores')),
        catchError(this.handleError('obter indicadores por país', []))
      );
	}
}
