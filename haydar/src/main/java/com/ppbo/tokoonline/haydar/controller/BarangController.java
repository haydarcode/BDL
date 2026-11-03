package com.ppbo.tokoonline.haydar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ppbo.tokoonline.haydar.entity.Barang;
import com.ppbo.tokoonline.haydar.repo.RepoBarang;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping ("/api/barang")
public class BarangController {
    @Autowired
    private RepoBarang barangRepository;

    @GetMapping
    public List<Barang> getAllBarang() {
        return barangRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barang> getBarangById(@PathVariable(value = "id") String id) {
        return barangRepository.findById(id)
                .map(barang -> ResponseEntity.ok().body(barang))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Barang createBarang(@RequestBody Barang barang) {
        return barangRepository.save(barang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barang> updateBarang(@PathVariable(value = "id") String id, @RequestBody Barang barangDetails) {
        return barangRepository.findById(id)
                .map(barang -> {
                    barang.setNamaBarang(barangDetails.getNamaBarang());
                    barang.setHargaBarang(barangDetails.getHargaBarang());
                    Barang updatedBarang = barangRepository.save(barang);
                    return ResponseEntity.ok().body(updatedBarang);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarang(@PathVariable(value = "id") String id) {
        return barangRepository.findById(id)
                .map(barang -> {
                    barangRepository.delete(barang);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
