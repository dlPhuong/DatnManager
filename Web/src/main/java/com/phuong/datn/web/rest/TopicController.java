package com.phuong.datn.web.rest;


import com.phuong.datn.domain.Topic;
import com.phuong.datn.domain.User;
import com.phuong.datn.repository.TopicRepository;
import com.phuong.datn.service.TeacherService;
import com.phuong.datn.service.UserService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TopicController {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TeacherService teacherService;

    @Autowired
    UserService userService;



    @GetMapping("/getAllTopic")
    public List<Topic> getAllTopic() {
       Optional<User> userOption =  userService.getUserWithAuthorities();
        return topicRepository.findByIdTeacher(userOption.get().getId()+"");
    }

}
