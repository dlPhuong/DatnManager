package com.phuong.datn.web.rest;


import com.phuong.datn.domain.*;
import com.phuong.datn.repository.*;
import com.phuong.datn.security.AuthoritiesConstants;
import com.phuong.datn.service.TopicService;
import com.phuong.datn.service.UserService;
import com.phuong.datn.service.dto.ReportDTO;
import com.phuong.datn.service.fileStorageService;
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
    TeacherRepository teacherRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserService userService;

    @Autowired
    fileStorageService fileStorageService;



    @GetMapping("/getAllReport")
    public List<ReportDTO> getAllReport() {
        Optional<User> userOption = userService.getUserWithAuthorities();
        List<ReportDTO> reportDTOS = new ArrayList<>();

        if(userOption.get().getAuthorities().iterator().next().getName().equalsIgnoreCase(AuthoritiesConstants.ADMIN)){
             List<Report> reportDTOS1 = reportRepository.findAll();
            for(Report report : reportDTOS1){
                Student student = studentRepository.findFirstById(Long.parseLong(report.getIdStudent()));
                File file = fileRepository.findFirstById(Long.parseLong(report.getIdFile()));
                reportDTOS.add(new ReportDTO(report,student,file));
            }
            return  reportDTOS;
        }

        Teacher teacher = teacherRepository.findFirstByIdUserAuth(userOption.get().getId());
        List<Report> reportList = reportRepository.findAllByIdTeacher(teacher.getId()+"");
        for(Report report:reportList){
            Student student = studentRepository.findFirstById(Long.parseLong(report.getIdStudent()));
            File file = fileRepository.findFirstById(Long.parseLong(report.getIdFile()));
            reportDTOS.add(new ReportDTO(report,student,file));
        }

        return reportDTOS;
    }

    @PostMapping("/saveReport")
    public Report saveReport(@RequestBody Report report) {
        Topic topic = topicRepository.findFirstByIdStudent(report.getIdStudent());
        Optional<User> userOption = userService.getUserWithAuthorities();
        Teacher teacher = teacherRepository.findFirstByIdUserAuth(userOption.get().getId());

        report.setIdTeacher(teacher.getId()+"");
        report.setIdTopic(topic.getId()+"");
        reportRepository.save(report);
        return report;
    }

    @PostMapping("/updateReport")
    public Report updateReport(@RequestBody Report report) {
        reportRepository.save(report);
        return report;
    }

    @PostMapping("/deleteReport")
    public void deleteReport(@RequestBody List<Report> reports) {
        for (Report report : reports) {
            reportRepository.delete(report);
            fileRepository.deleteById(Long.parseLong(report.getIdFile()));
        }
    }

    @GetMapping("/getListStudentbyTeacher")
    public List<Student> getListStudentbyTeacher() {
        Optional<User> userOption = userService.getUserWithAuthorities();
        Teacher teacher = teacherRepository.findFirstByIdUserAuth(userOption.get().getId());
        List<Student> studentList = studentRepository.findAllByIdTeacher(teacher.getId()+"");
        return studentList;
    }


    @GetMapping("/getAllReportStudent")
    public List<ReportDTO> getAllReportStudent() {
        Optional<User> userOption = userService.getUserWithAuthorities();
        Student student = studentRepository.findFirstByIdUserAuth(userOption.get().getId());
        List<Report> reportList = reportRepository.findAllByIdStudent(student.getId()+"");
        List<ReportDTO> reportDTOS = new ArrayList<>();
        for(Report report:reportList){
            File file = fileRepository.findFirstById(Long.parseLong(report.getIdFile()));
            reportDTOS.add(new ReportDTO(report,student,file));
        }

        return reportDTOS;
    }

}
