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

import com.ppbo.tokoonline.haydar.entity.Penjual;
import com.ppbo.tokoonline.haydar.repo.RepoPenjual;

@RestController
@RequestMapping ("/api/penjual")
public class PenjualController {
    @Autowired
    private RepoPenjual penjualRepository;

    @GetMapping
    public List<Penjual> getAllPenjual() {
        return penjualRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Penjual> getPenjualById(@PathVariable(value = "id") String id) {
        return penjualRepository.findById(id)
                .map(penjual -> ResponseEntity.ok().body(penjual))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Penjual createPenjual(@RequestBody Penjual penjual) {
        return penjualRepository.save(penjual);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Penjual> updatePenjual(@PathVariable(value = "id") String id, @RequestBody Penjual penjualDetails) {
        return penjualRepository.findById(id)
                .map(penjual -> {
                    penjual.setNamaPenjual(penjualDetails.getNamaPenjual());
                    Penjual updatedPenjual = penjualRepository.save(penjual);
                    return ResponseEntity.ok().body(updatedPenjual);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePenjual(@PathVariable(value = "id") String id) {
        return penjualRepository.findById(id)
                .map(penjual -> {
                    penjualRepository.delete(penjual);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
