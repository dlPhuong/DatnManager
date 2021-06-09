package com.phuong.datn.repository;

import com.phuong.datn.domain.File;
import com.phuong.datn.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByIdTeacher(String idTeacher);

    Topic findFirstByIdTeacher(String idTeacher);
    Topic findFirstByIdStudent(String idstudent);
}
