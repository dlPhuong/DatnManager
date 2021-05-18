package com.phuong.datn.web.rest;


import com.phuong.datn.domain.Teacher;
import com.phuong.datn.domain.Topic;
import com.phuong.datn.domain.User;
import com.phuong.datn.repository.TopicRepository;
import com.phuong.datn.service.TeacherService;
import com.phuong.datn.service.TopicService;
import com.phuong.datn.service.UserService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TopicController {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicService topicService;

    @Autowired
    UserService userService;


    @GetMapping("/getAllTopic")
    public List<Topic> getAllTopic() {
        Optional<User> userOption = userService.getUserWithAuthorities();
        return topicRepository.findByIdTeacher(userOption.get().getId() + "");
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @PostMapping("/uploadExcelFileTopic")
    public void uploadFile(MultipartFile file) throws IOException {
        List<Topic> topicList = topicService.getListFromExcel(file);
        Optional<User> userOption = userService.getUserWithAuthorities();
        for(Topic topic : topicList){
            topic.setIdTeacher(userOption.get().getId() + "");
            topic.setNameTeacher(userOption.get().getFirstName() + " " + userOption.get().getLastName() + "");
        }
        topicRepository.saveAll(topicList);
    }

    @PostMapping("/saveTopic")
    public void saveTopic(@RequestBody Topic topic) {
        Optional<User> userOption = userService.getUserWithAuthorities();
        topic.setIdTeacher(userOption.get().getId() + "");
        topic.setNameTeacher(userOption.get().getFirstName() + " " + userOption.get().getLastName() + "");
        topicRepository.save(topic);
    }

    @PostMapping("/deleteTopic")
    public void deleteTopic(@RequestBody List<Topic> topic) {
        for (Topic topic1 : topic) {
            topicRepository.delete(topic1);
        }
    }

}
