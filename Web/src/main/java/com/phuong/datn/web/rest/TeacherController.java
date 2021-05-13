package com.phuong.datn.web.rest;


import com.phuong.datn.config.Commons;
import com.phuong.datn.domain.Report;
import com.phuong.datn.domain.Response.BaseResponse;
import com.phuong.datn.domain.Student;
import com.phuong.datn.domain.Teacher;
import com.phuong.datn.repository.FileRepository;
import com.phuong.datn.repository.ReportRepository;
import com.phuong.datn.repository.TeacherRepository;
import com.phuong.datn.repository.TopicRepository;
import com.phuong.datn.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TeacherService teacherService;

    @GetMapping("/getAllTopic")
    public BaseResponse getAllReport() {

        BaseResponse baseResponse = new BaseResponse(Commons.SUCCESS, Commons.SUCCESS, topicRepository.findAll());
        return baseResponse;
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
