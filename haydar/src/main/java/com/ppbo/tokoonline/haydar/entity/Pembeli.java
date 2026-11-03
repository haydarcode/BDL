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
@Table(name = "pembeli")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pembeli {
    @Id
    @Column(name = "kode_pembeli", length = 20)
    private String kodePembeli;
    @Column(name = "nama_pembeli", length = 50)
    private String namaPembeli;
}
