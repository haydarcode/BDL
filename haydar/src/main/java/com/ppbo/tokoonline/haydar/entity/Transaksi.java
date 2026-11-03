package com.ppbo.tokoonline.haydar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaksi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaksi {
    @Id
    @Column(name = "kode_transaksi", length = 20)
    private String kodeTransaksi;

    @Column(name = "tanggal_transaksi")
    private LocalDate tanggalTransaksi;

    @Column(name = "jumlah_barang")
    private Integer jumlahBarang;

    @Column(name = "total_harga")
    private Float totalHarga;

    // Pembeli - Transaksi : one-to-one
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kode_pembeli")
    private Pembeli pembeli;

    // Penjual - Transaksi : one-to-one
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kode_penjual")
    private Penjual penjual;

    // Satu kode transaksi dapat berisi lebih dari satu barang
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "transaksi_barang",
            joinColumns = @JoinColumn(name = "kode_transaksi"),
            inverseJoinColumns = @JoinColumn(name = "kode_barang")
    )
    private List<Barang> barang = new ArrayList<>();

    // Total harga dari barang yang dibeli dalam satu transaksi
    @PrePersist
    @PreUpdate
    private void hitungTotalHarga() {
        if (barang != null && !barang.isEmpty()) {
            jumlahBarang = barang.size();
            totalHarga = barang.stream()
                    .map(Barang::getHargaBarang)
                    .filter(h -> h != null)
                    .reduce(0f, Float::sum);
        }
    }
}
