import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaksi, Pembeli, Penjual, Barang } from '../models/shop.model';

@Injectable({
  providedIn: 'root'
})
export class TransaksiService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // Master Data
  getPembeli(): Observable<Pembeli[]> {
    return this.http.get<Pembeli[]>(`${this.baseUrl}/pembeli`);
  }

  getPenjual(): Observable<Penjual[]> {
    return this.http.get<Penjual[]>(`${this.baseUrl}/penjual`);
  }

  getBarang(): Observable<Barang[]> {
    return this.http.get<Barang[]>(`${this.baseUrl}/barang`);
  }

  // Transaksi
  getAllTransaksi(): Observable<Transaksi[]> {
    return this.http.get<Transaksi[]>(`${this.baseUrl}/transaksi`);
  }

  createTransaksi(transaksi: Transaksi): Observable<Transaksi> {
    return this.http.post<Transaksi>(`${this.baseUrl}/transaksi`, transaksi);
  }

  deleteTransaksi(kodeTransaksi: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/transaksi/${kodeTransaksi}`);
  }
}