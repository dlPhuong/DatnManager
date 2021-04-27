package com.phuong.datn.repository;

import com.phuong.datn.domain.File;
import com.phuong.datn.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
