package com.phuong.datn.repository;

import com.phuong.datn.domain.File;
import com.phuong.datn.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findFirstByIdUserAuth(Long id);

    Teacher findFirstById(Long id);

    void deleteByIdUserAuth(Long id);

}
