package com.phuong.datn.web.rest;


import com.phuong.datn.config.Commons;
import com.phuong.datn.domain.Response.BaseResponse;
import com.phuong.datn.repository.StudentRepository;
import com.phuong.datn.repository.TeacherRepository;
import com.phuong.datn.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @GetMapping("/getAllStudent")
    public BaseResponse getALlStudent() {
        BaseResponse baseResponse = new BaseResponse(Commons.SUCCESS, Commons.SUCCESS, studentRepository.findAll());
        return baseResponse;
    }
}
