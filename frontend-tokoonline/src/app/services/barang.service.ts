import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Barang } from '../models/shop.model';

@Injectable({
  providedIn: 'root'
})
export class BarangService {
  private baseUrl = 'http://localhost:8081/api/barang';

  constructor(private http: HttpClient) {}

  getAllBarang(): Observable<Barang[]> {
    return this.http.get<Barang[]>(this.baseUrl);
  }

  getBarangById(id: string): Observable<Barang> {
    return this.http.get<Barang>(`${this.baseUrl}/${id}`);
  }

  createBarang(barang: Barang): Observable<Barang> {
    return this.http.post<Barang>(this.baseUrl, barang);
  }

  updateBarang(id: string, barang: Barang): Observable<Barang> {
    return this.http.put<Barang>(`${this.baseUrl}/${id}`, barang);
  }

  deleteBarang(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}