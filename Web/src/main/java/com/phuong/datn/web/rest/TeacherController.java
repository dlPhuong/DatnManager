package com.phuong.datn.web.rest;


import com.phuong.datn.config.Commons;
import com.phuong.datn.domain.Response.BaseResponse;
import com.phuong.datn.repository.FileRepository;
import com.phuong.datn.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TeacherController {
    @Autowired
    TeacherRepository repository;

    @GetMapping("/getAllTeacher")
    public BaseResponse getAllTeacher() {
        BaseResponse baseResponse = new BaseResponse(Commons.SUCCESS, Commons.SUCCESS, repository.findAll());
        return baseResponse;
    }
}
