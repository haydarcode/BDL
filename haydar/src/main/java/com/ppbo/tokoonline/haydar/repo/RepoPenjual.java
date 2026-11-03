package com.ppbo.tokoonline.haydar.repo;

import com.ppbo.tokoonline.haydar.entity.Penjual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoPenjual extends JpaRepository<Penjual, String> {
}
