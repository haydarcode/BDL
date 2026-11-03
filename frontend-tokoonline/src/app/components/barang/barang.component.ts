import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BarangService } from '../../services/barang.service';
import { Barang } from '../../models/shop.model';

@Component({
  selector: 'app-barang',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './barang.component.html',
  styleUrls: ['./barang.component.css']
})
export class BarangComponent implements OnInit {
  listBarang: Barang[] = [];

  // Model Form Input
  kodeBarang: string = '';
  namaBarang: string = '';
  hargaBarang: number | null = null;
  isEditMode: boolean = false;

  constructor(private barangService: BarangService) {}

  ngOnInit(): void {
    // Dipanggil otomatis saat komponen ini tampil
    this.loadBarang();
  }

  loadBarang(): void {
    this.barangService.getAllBarang().subscribe({
      next: (data) => {
        console.log('Data dari database:', data); // Cek data di Console F12
        this.listBarang = data;
      },
      error: (err) => {
        console.error('Gagal mengambil data dari database:', err);
      }
    });
  }
simpanBarang(): void {
  if (!this.kodeBarang || !this.namaBarang || this.hargaBarang === null || this.hargaBarang <= 0) {
    alert('Harap isi semua kolom dengan benar!');
    return;
  }

  const payload: Barang = {
    kodeBarang: this.kodeBarang,
    namaBarang: this.namaBarang,
    hargaBarang: this.hargaBarang
  };

  if (this.isEditMode) {
    this.barangService.updateBarang(this.kodeBarang, payload).subscribe({
      next: () => {
        alert('Data barang berhasil diperbarui!');
        this.loadBarang();
        this.resetForm();
      },
      error: (err) => {
        console.error('Error Update:', err);
        alert('Gagal memperbarui data: ' + (err.message || 'Cek konsol browser'));
      }
    });
  } else {
    this.barangService.createBarang(payload).subscribe({
      next: () => {
        alert('Data barang berhasil ditambahkan!');
        this.loadBarang();
        this.resetForm();
      },
      error: (err) => {
        console.error('Error Create:', err);
        alert('Gagal menambah data: ' + (err.statusText || 'Cek koneksi ke backend/Spring Boot'));
      }
    });
  }
}

  // Isi form dengan data yang dipilih untuk diedit
  editBarang(item: Barang): void {
    this.isEditMode = true;
    this.kodeBarang = item.kodeBarang;
    this.namaBarang = item.namaBarang;
    this.hargaBarang = item.hargaBarang;
  }

  // Hapus data barang
  hapusBarang(kodeBarang: string): void {
    if (confirm(`Yakin ingin menghapus barang dengan kode "${kodeBarang}"?`)) {
      this.barangService.deleteBarang(kodeBarang).subscribe({
        next: () => {
          alert('Data barang berhasil dihapus!');
          this.loadBarang();
        },
        error: (err) => alert('Gagal menghapus data barang.')
      });
    }
  }

  // Reset form ke kondisi awal
  resetForm(): void {
    this.isEditMode = false;
    this.kodeBarang = '';
    this.namaBarang = '';
    this.hargaBarang = null;
  }
}