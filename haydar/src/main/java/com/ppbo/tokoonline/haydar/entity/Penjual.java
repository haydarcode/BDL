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
@Table(name = "penjual")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Penjual {
    @Id
    @Column(name = "kode_penjual", length = 20)
    private String kodePenjual;
    @Column(name = "nama_penjual", length = 50)
    private String namaPenjual;
}
