package com.phuong.datn.repository;

import com.phuong.datn.domain.Lop;
import com.phuong.datn.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByIdTeacherAndIdStudent(String idteach,String idStudent);

    List<Report> findAllByIdTeacher(String idTeach);
}
