package com.phuong.datn.repository;

import com.phuong.datn.domain.File;
import com.phuong.datn.domain.Student;
import com.phuong.datn.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findFirstByIdUserAuth(Long id);

    List<Student> findAllByIdTeacher(String id);

    void deleteByIdUserAuth(Long id);
}
