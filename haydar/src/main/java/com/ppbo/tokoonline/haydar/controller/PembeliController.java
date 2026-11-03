package com.ppbo.tokoonline.haydar.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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

import com.ppbo.tokoonline.haydar.entity.Pembeli;
import com.ppbo.tokoonline.haydar.repo.RepoPembeli;

@RestController
@RequestMapping("/api/pembeli")
public class PembeliController {
    @Autowired
    private RepoPembeli pembeliRepoPembeli;

    @GetMapping
    public List<Pembeli> getAllPembeli(){
        return pembeliRepoPembeli.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pembeli> getPembeliById(@PathVariable(value = "id") String id){
        return pembeliRepoPembeli.findById(id)
        .map(pembeli -> ResponseEntity.ok().body(pembeli))
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pembeli CreatePembeli(@RequestBody Pembeli pembeli){
        return pembeliRepoPembeli.save(pembeli);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pembeli> updatePembeli(@PathVariable(value = "id") String id, @RequestBody Pembeli pembeliDetails){
        return pembeliRepoPembeli.findById(id)
                .map(pembeli -> {
                    pembeli.setNamaPembeli(pembeliDetails.getNamaPembeli());
                    Pembeli updatedPembeli = pembeliRepoPembeli.save(pembeli);
                    return ResponseEntity.ok().body(updatedPembeli);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePembeli(@PathVariable(value = "id") String id) {
        return pembeliRepoPembeli.findById(id)
                .map(pembeli -> {
                    pembeliRepoPembeli.delete(pembeli);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
