package com.phuong.datn.repository;

import com.phuong.datn.domain.File;
import com.phuong.datn.domain.Lop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LopRepository extends JpaRepository<Lop, Long> {
}
