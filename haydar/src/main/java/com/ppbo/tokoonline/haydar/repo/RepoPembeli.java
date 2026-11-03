package com.ppbo.tokoonline.haydar.repo;

import com.ppbo.tokoonline.haydar.entity.Pembeli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoPembeli extends JpaRepository<Pembeli, String> {
}
