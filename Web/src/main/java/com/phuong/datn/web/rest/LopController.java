package com.phuong.datn.web.rest;


import com.phuong.datn.domain.Topic;
import com.phuong.datn.domain.User;
import com.phuong.datn.repository.LopRepository;
import com.phuong.datn.service.TopicService;
import com.phuong.datn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.phuong.datn.domain.Lop;

@RestController
@RequestMapping("/api")
public class LopController {

    @Autowired
    LopRepository lopRepository;

    @GetMapping("/getAllLop")
    public List<Lop> getAllTopic() {
        return lopRepository.findAll();
    }


    @PostMapping("/saveLop")
    public void saveTopic(@RequestBody Lop lop) {
        lopRepository.save(lop);
    }

    @PostMapping("/deleteLop")
    public void deleteTopic(@RequestBody List<Lop> lops) {
        for (Lop lop : lops) {
            lopRepository.delete(lop);
        }
    }

}
