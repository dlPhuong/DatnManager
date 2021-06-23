package com.phuong.datn.web.rest;

import com.phuong.datn.domain.Reviewer;
import com.phuong.datn.domain.Student;
import com.phuong.datn.domain.Teacher;
import com.phuong.datn.domain.User;
import com.phuong.datn.repository.ReviewerRepository;
import com.phuong.datn.repository.StudentRepository;
import com.phuong.datn.repository.TeacherRepository;
import com.phuong.datn.repository.TopicRepository;
import com.phuong.datn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ReviewerRepository reviewerRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserService userService;


    @GetMapping("/getReviewerStudent")
    public Reviewer getReviewerStudent() {
        Optional<User> userOption = userService.getUserWithAuthorities();
        Long id = userOption.get().getId();
        Student student = studentRepository.findFirstByIdUserAuth(id);
        Reviewer reviewer = reviewerRepository.findFirstById(Long.parseLong(student.getIdPb()));
        Teacher teacher1 = teacherRepository.findFirstById(Long.parseLong(reviewer.getIdGv1()));
        Teacher teacher2 = teacherRepository.findFirstById(Long.parseLong(reviewer.getIdGv2()));
            return reviewer;
    }


    @PostMapping("/saveTeacher1")
    public void saveStudent1(@RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
    }

}
