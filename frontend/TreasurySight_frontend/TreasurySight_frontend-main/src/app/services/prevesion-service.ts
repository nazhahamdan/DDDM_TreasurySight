import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PrevesionService {

  private apiUrl = 'http://localhost:8080/api/prediction';

  constructor(private http: HttpClient) {}

  predireRisque(donnees: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/faillite`, donnees);
  }
}
