package com.ppbo.tokoonline.haydar.repo;

import com.ppbo.tokoonline.haydar.entity.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoBarang extends JpaRepository<Barang, String> {
}
