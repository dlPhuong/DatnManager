package com.phuong.datn.web.rest;


import com.phuong.datn.config.Constants;
import com.phuong.datn.domain.*;
import com.phuong.datn.repository.StudentRepository;
import com.phuong.datn.repository.TeacherRepository;
import com.phuong.datn.repository.TopicRepository;
import com.phuong.datn.security.AuthoritiesConstants;
import com.phuong.datn.service.TeacherService;
import com.phuong.datn.service.TopicService;
import com.phuong.datn.service.UserService;
import com.phuong.datn.service.dto.ReportDTO;
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
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TopicService topicService;

    @Autowired
    UserService userService;


    @GetMapping("/getAllTopicStudent")
    public List<Topic> getAllTopic() {
        Optional<User> userOption = userService.getUserWithAuthorities();
        Student student = studentRepository.findFirstByIdUserAuth(userOption.get().getId());
        return topicRepository.findByIdTeacher(student.getIdTeacher()+ "");
    }

    @GetMapping("/getAllTopicTeacher")
    public List<Topic> getAllTopicTeacher() {
        Optional<User> userOption = userService.getUserWithAuthorities();
        if(userOption.get().getAuthorities().iterator().next().getName().equalsIgnoreCase(AuthoritiesConstants.ADMIN)){
            return topicRepository.findAll();
        }
        Teacher student = teacherRepository.findFirstByIdUserAuth(userOption.get().getId());
        return topicRepository.findByIdTeacher(student.getId()+ "");
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @PostMapping("/uploadExcelFileTopic")
    public void uploadFile(MultipartFile file) throws IOException {
        List<Topic> topicList = topicService.getListFromExcel(file);
        Optional<User> userOption = userService.getUserWithAuthorities();
        for (Topic topic : topicList) {
            topic.setIdTeacher(userOption.get().getId() + "");
            topic.setNameTeacher(userOption.get().getFirstName() + " " + userOption.get().getLastName() + "");
        }
        topicRepository.saveAll(topicList);
    }

    @PostMapping("/saveTopic")
    public void saveTopic(@RequestBody Topic topic) {
        Optional<User> userOption = userService.getUserWithAuthorities();
        Teacher teacher = teacherRepository.findFirstByIdUserAuth(userOption.get().getId());
        topic.setIdTeacher( teacher.getId()+ "");
        topic.setNameTeacher(userOption.get().getFirstName() + " " + userOption.get().getLastName() + "");
        topicRepository.save(topic);
    }

    @PostMapping("/saveTopicStudent")
    public Topic saveTopicStudent(@RequestBody Topic topic) {
        if(topicRepository.findFirstByIdStudent(topic.getIdStudent()+"") !=null ){
            return null;
        }
        Teacher teacher = teacherRepository.findFirstById(Long.parseLong(topic.getIdTeacher()));
        topic.setNameTeacher(teacher.getNameTeacher());
        topicRepository.save(topic);
        return topic;
    }

    @PostMapping("/deleteTopic")
    public void deleteTopic(@RequestBody List<Topic> topic) {
        for (Topic topic1 : topic) {
            topicRepository.delete(topic1);
        }
    }

}
