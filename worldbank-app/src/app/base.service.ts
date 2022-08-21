import {  Observable, of } from 'rxjs';

export abstract class BaseService{

    protected handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
         console.error(error); // log to console instead
      
          // Let the app keep running by returning an empty result.
          return of(result as T);
        };
      }
}