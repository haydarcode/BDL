package com.ppbo.tokoonline.haydar.repo;

import com.ppbo.tokoonline.haydar.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoTransaksi extends JpaRepository<Transaksi, String> {
}
