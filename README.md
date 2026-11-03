# Toko Online - HAYDAR

Struktur:
- `haydar/` : Back-End Spring Boot (port 8081)
- `frontend-tokoonline/` : Front-End Angular (port 4200)

## Back-End
1. Buat database `toko_online` (lihat `toko_online.sql`).
2. Sesuaikan username/password PostgreSQL di `src/main/resources/application.properties`.
3. Jalankan:

```powershell
cd haydar
.\mvnw.cmd spring-boot:run
```

Endpoint: `/api/barang`, `/api/pembeli`, `/api/penjual`, `/api/transaksi`

## Front-End
```powershell
cd frontend-tokoonline
npm install
ng serve
```
Buka `http://localhost:4200`
