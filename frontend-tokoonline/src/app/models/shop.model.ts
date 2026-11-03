export interface Pembeli {
  kodePembeli: string;
  namaPembeli: string;
}

export interface Penjual {
  kodePenjual: string;
  namaPenjual: string;
}

export interface Barang {
  kodeBarang: string;
  namaBarang: string;
  hargaBarang: number;
}

export interface Transaksi {
  kodeTransaksi: string;
  tanggalTransaksi: string; // Format ISO YYYY-MM-DD
  jumlahBarang: number;
  totalHarga: number;
  pembeli: Pembeli;
  penjual: Penjual;
  barang: Barang;
}