package com.phuong.datn.web.rest;


import com.phuong.datn.config.Commons;
import com.phuong.datn.domain.Report;
import com.phuong.datn.domain.Response.BaseResponse;
import com.phuong.datn.domain.Teacher;
import com.phuong.datn.repository.FileRepository;
import com.phuong.datn.repository.ReportRepository;
import com.phuong.datn.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    ReportRepository reportRepository;

    @GetMapping("/getAllReport")
    public BaseResponse getAllReport() {

        BaseResponse baseResponse = new BaseResponse(Commons.SUCCESS, Commons.SUCCESS, reportRepository.findAll());
        return baseResponse;
    }

    @GetMapping("/getAllTeacher")
    public BaseResponse getAllTeacher() {
        BaseResponse baseResponse = new BaseResponse(Commons.SUCCESS, Commons.SUCCESS, teacherRepository.findAll());
        return baseResponse;
    }
}
