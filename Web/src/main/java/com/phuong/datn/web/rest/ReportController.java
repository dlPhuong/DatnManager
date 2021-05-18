package com.phuong.datn.web.rest;


import com.phuong.datn.domain.*;
import com.phuong.datn.repository.FileRepository;
import com.phuong.datn.repository.ReportRepository;
import com.phuong.datn.repository.StudentRepository;
import com.phuong.datn.repository.TopicRepository;
import com.phuong.datn.service.TopicService;
import com.phuong.datn.service.UserService;
import com.phuong.datn.service.dto.ReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserService userService;


    @GetMapping("/getAllReport/")
    public List<ReportDTO> getAllTopic(@RequestParam(value = "idStudent", required = false) String idStudent) {
        Optional<User> userOption = userService.getUserWithAuthorities();
        List<Report> reportList = reportRepository.findAllByIdTeacherAndIdStudent(userOption.get().getId() + "", idStudent);
        List<ReportDTO> reportDTOS = new ArrayList<>();
            for(Report report : reportList){
              File file = fileRepository.findFirstById(Long.parseLong(report.getIdFile()));
              Student student = studentRepository.findFirstByIdUserAuth(Long.parseLong(idStudent));
                reportDTOS.add(new ReportDTO(report,student,file));
            }
        return reportDTOS;
    }

//    @PostMapping("/saveTopic")
//    public void saveTopic(@RequestBody Report topic) {
//        Optional<User> userOption = userService.getUserWithAuthorities();
//        reportRepository.save(topic);
//    }
//
//    @PostMapping("/deleteTopic")
//    public void deleteTopic(@RequestBody List<Report> topic) {
//        for (Report topic1 : topic) {
//            reportRepository.delete(topic1);
//        }
//    }

}
