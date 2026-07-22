import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Festivity } from '../models/festivity';

@Injectable({
  providedIn: 'root'
})
export class FestivityService {
  private apiUrl = '/festivity';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Festivity[]> {
    return this.http.get<Festivity[]>(`${this.apiUrl}/findAll`);
  }

  getOne(id: string): Observable<Festivity> {
    return this.http.get<Festivity>(`${this.apiUrl}/${id}`);
  }

  create(festivity: Festivity): Observable<any> {
    return this.http.post<any>(this.apiUrl, festivity);
  }

  update(festivity: Festivity): Observable<any> {
    return this.http.put<any>(this.apiUrl, festivity);
  }
}
