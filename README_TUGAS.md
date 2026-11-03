# Tugas Spring IO Lanjutan - Toko Online

Project ini sudah disesuaikan dengan tugas pada jobsheet:
- Entity Pembeli
- Entity Penjual
- Entity Barang
- Entity Transaksi
- Pembeli <-> Transaksi: One-to-One
- Penjual <-> Transaksi: One-to-One
- Transaksi <-> Barang: Many-to-Many agar satu transaksi dapat berisi lebih dari satu barang
- `getTotalHarga()` menghitung total harga seluruh barang dalam transaksi

## Sebelum menjalankan
1. Buat database PostgreSQL:
   `CREATE DATABASE toko_online;`
2. Buka:
   `src/main/resources/application.properties`
3. Ganti `GANTI_DENGAN_PASSWORD_POSTGRES_KAMU` dengan password PostgreSQL milikmu.

## Jalankan di PowerShell
```powershell
cd "lokasi\project\haydar"
.\mvnw.cmd clean
.\mvnw.cmd spring-boot:run
```

## Endpoint pengujian
- GET http://localhost:8080/api/transaksi
- GET http://localhost:8080/api/transaksi/{kodeTransaksi}

Setelah aplikasi berhasil start, refresh database `toko_online` di DBeaver. Tabel entity dan tabel penghubung `transaksi_barang` akan dibuat oleh Hibernate.
