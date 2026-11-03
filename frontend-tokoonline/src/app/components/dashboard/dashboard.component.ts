import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BarangComponent } from '../barang/barang.component'; // Import BarangComponent

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, BarangComponent], // Daftarkan BarangComponent di sini
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  // Menu default saat aplikasi pertama kali dibuka
  activeMenu: string = 'dashboard';

  // Method untuk mengubah menu aktif saat diklik
  selectMenu(menu: string): void {
    this.activeMenu = menu;
  }
}