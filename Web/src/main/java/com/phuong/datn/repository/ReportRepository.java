package com.phuong.datn.repository;

import com.phuong.datn.domain.Lop;
import com.phuong.datn.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
