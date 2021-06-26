package com.phuong.datn.web.rest;


import com.phuong.datn.config.Commons;
import com.phuong.datn.domain.Report;
import com.phuong.datn.domain.Response.BaseResponse;
import com.phuong.datn.domain.Student;
import com.phuong.datn.domain.Teacher;
import com.phuong.datn.domain.User;
import com.phuong.datn.repository.FileRepository;
import com.phuong.datn.repository.ReportRepository;
import com.phuong.datn.repository.TeacherRepository;
import com.phuong.datn.repository.TopicRepository;
import com.phuong.datn.security.AuthoritiesConstants;
import com.phuong.datn.service.TeacherService;
import com.phuong.datn.service.UserService;
import com.phuong.datn.service.dto.UserDTO;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TeacherService teacherService;

    @Autowired
    UserService userService;


    @GetMapping("/getInfoTeacher")
    public Teacher getInfoTeacher() {
        Optional<User> userOption = userService.getUserWithAuthorities();
        Long id = userOption.get().getId();
        return teacherRepository.findFirstByIdUserAuth(id);
    }

    @GetMapping("/getAllTeacher")
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }


    @CrossOrigin(origins = "http://localhost:9000")
    @PostMapping("/uploadExcelFileTeacher")
    public void uploadFile(MultipartFile file) throws IOException {
        List<Teacher> studentList = teacherService.getListFromExcel(file);
        teacherRepository.saveAll(studentList);
    }

    @PostMapping("/saveTeacher")
    public void saveStudent(@RequestBody Teacher teacher) {

        teacherRepository.save(teacher);
    }

    @PostMapping("/removeTeacher")
    public void removeStudent(@RequestBody List<Teacher> teachers) {
        teacherService.removeStudent(teachers);
    }

}
