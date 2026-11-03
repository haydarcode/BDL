package com.ppbo.tokoonline.haydar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ppbo.tokoonline.haydar.entity.Transaksi;
import com.ppbo.tokoonline.haydar.repo.RepoTransaksi;

@RestController
@RequestMapping ("/api/transaksi")
public class TransaksiController {
    @Autowired
    private RepoTransaksi transaksiRepository;

    @GetMapping
    public List<Transaksi> getAllTransaksi() {
        return transaksiRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaksi> getTransaksiById(@PathVariable(value = "id") String id) {
        return transaksiRepository.findById(id)
                .map(transaksi -> ResponseEntity.ok().body(transaksi))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transaksi createTransaksi(@RequestBody Transaksi transaksi) {
        return transaksiRepository.save(transaksi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaksi> updateTransaksi(@PathVariable(value = "id") String id, @RequestBody Transaksi transaksiDetails) {
        return transaksiRepository.findById(id)
                .map(transaksi -> {
                    transaksi.setTanggalTransaksi(transaksiDetails.getTanggalTransaksi());
                    transaksi.setJumlahBarang(transaksiDetails.getJumlahBarang());
                    transaksi.setTotalHarga(transaksiDetails.getTotalHarga());
                    transaksi.setPembeli(transaksiDetails.getPembeli());
                    transaksi.setPenjual(transaksiDetails.getPenjual());
                    transaksi.setBarang(transaksiDetails.getBarang());
                    Transaksi updatedTransaksi = transaksiRepository.save(transaksi);
                    return ResponseEntity.ok().body(updatedTransaksi);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaksi(@PathVariable(value = "id") String id) {
        return transaksiRepository.findById(id)
                .map(transaksi -> {
                    transaksiRepository.delete(transaksi);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
