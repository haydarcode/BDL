import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // Untuk *ngFor & *ngIf
import { FormsModule } from '@angular/forms';   // Untuk [(ngModel)]
import { TransaksiService } from '../../services/transaksi.service';
import { Transaksi, Pembeli, Penjual, Barang } from '../../models/shop.model';

@Component({
selector: 'app-transaksi',
  standalone: true,
  imports: [CommonModule, FormsModule], // Import modul di sini
  templateUrl: './transaksi.component.html',
  styleUrl: './transaksi.component.css'
})
export class TransaksiComponent implements OnInit {
  listTransaksi: Transaksi[] = [];
  listPembeli: Pembeli[] = [];
  listPenjual: Penjual[] = [];
  listBarang: Barang[] = [];

  // Model Form
  kodeTransaksi: string = '';
  tanggalTransaksi: string = new Date().toISOString().split('T')[0];
  selectedPembeliKode: string = '';
  selectedPenjualKode: string = '';
  selectedBarangKode: string = '';
  jumlahBarang: number = 1;
  totalHarga: number = 0;

  constructor(private transaksiService: TransaksiService) {}

  ngOnInit(): void {
    this.loadMasterData();
    this.loadTransaksi();
  }

  loadMasterData(): void {
    this.transaksiService.getPembeli().subscribe(data => this.listPembeli = data);
    this.transaksiService.getPenjual().subscribe(data => this.listPenjual = data);
    this.transaksiService.getBarang().subscribe(data => this.listBarang = data);
  }

  loadTransaksi(): void {
    this.transaksiService.getAllTransaksi().subscribe(data => this.listTransaksi = data);
  }

  hitungTotal(): void {
    const barang = this.listBarang.find(b => b.kodeBarang === this.selectedBarangKode);
    if (barang && this.jumlahBarang > 0) {
      this.totalHarga = barang.hargaBarang * this.jumlahBarang;
    } else {
      this.totalHarga = 0;
    }
  }

  simpanTransaksi(): void {
    const pembeliObj = this.listPembeli.find(p => p.kodePembeli === this.selectedPembeliKode);
    const penjualObj = this.listPenjual.find(pj => pj.kodePenjual === this.selectedPenjualKode);
    const barangObj = this.listBarang.find(b => b.kodeBarang === this.selectedBarangKode);

    if (!pembeliObj || !penjualObj || !barangObj || !this.kodeTransaksi) {
      alert('Lengkapi seluruh data transaksi!');
      return;
    }

    const payload: Transaksi = {
      kodeTransaksi: this.kodeTransaksi,
      tanggalTransaksi: this.tanggalTransaksi,
      jumlahBarang: this.jumlahBarang,
      totalHarga: this.totalHarga,
      pembeli: pembeliObj,
      penjual: penjualObj,
      barang: barangObj
    };

    this.transaksiService.createTransaksi(payload).subscribe(() => {
      this.loadTransaksi();
      this.resetForm();
    });
  }

  hapusTransaksi(kode: string): void {
    if (confirm('Yakin ingin menghapus transaksi ini?')) {
      this.transaksiService.deleteTransaksi(kode).subscribe(() => {
        this.loadTransaksi();
      });
    }
  }

  resetForm(): void {
    this.kodeTransaksi = '';
    this.selectedPembeliKode = '';
    this.selectedPenjualKode = '';
    this.selectedBarangKode = '';
    this.jumlahBarang = 1;
    this.totalHarga = 0;
  }
}