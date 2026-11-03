package com.ppbo.tokoonline.haydar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "barang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Barang {
    @Id
    @Column(name = "kode_barang", length = 20)
    private String kodeBarang;
    @Column(name = "nama_barang", length = 100)
    private String namaBarang;
    @Column(name = "harga_barang")
    private Float hargaBarang;
}
