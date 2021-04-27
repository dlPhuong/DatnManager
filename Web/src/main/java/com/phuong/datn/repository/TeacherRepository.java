package com.phuong.datn.repository;

import com.phuong.datn.domain.File;
import com.phuong.datn.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
