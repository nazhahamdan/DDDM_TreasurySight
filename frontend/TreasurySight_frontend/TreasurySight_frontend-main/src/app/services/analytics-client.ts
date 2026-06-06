import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ClientRisk } from '../models/client-risk.model';

@Injectable({
  providedIn: 'root'
})
export class AnalyticsClientService {

  private apiUrl = 'http://localhost:8080/analytics/clients';

  constructor(private http: HttpClient) {}

  getAnalysis(entrepriseId: number): Observable<ClientRisk[]> {
    return this.http.get<ClientRisk[]>(
      `${this.apiUrl}/${entrepriseId}`
    );
  }
}