package com.phuong.datn.web.rest;


import com.phuong.datn.config.Commons;
import com.phuong.datn.domain.Authority;
import com.phuong.datn.domain.Response.BaseResponse;
import com.phuong.datn.domain.Student;
import com.phuong.datn.domain.Teacher;
import com.phuong.datn.domain.User;
import com.phuong.datn.repository.StudentRepository;
import com.phuong.datn.repository.TeacherRepository;
import com.phuong.datn.repository.TopicRepository;
import com.phuong.datn.repository.UserRepository;
import com.phuong.datn.security.AuthoritiesConstants;
import com.phuong.datn.service.StudentService;
import com.phuong.datn.service.UserService;
import com.phuong.datn.service.dto.UserDTO;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    @GetMapping("/getAllStudent")
    public List<Student> getALlStudent() {
        Optional<User> userOption = userService.getUserWithAuthorities();
        Authority auth = userOption.get().getAuthorities().iterator().next();
        if (auth.getName().equalsIgnoreCase(AuthoritiesConstants.ADMIN)) {
            return studentRepository.findAll();
        } else {
            Teacher teacher = teacherRepository.findFirstByIdUserAuth(userOption.get().getId());
            List<Student> students = studentRepository.findAllByIdTeacher(teacher.getId()+"");
            return students;
        }
    }

    @GetMapping("/getInfoStudent")
    public Student getInfoStudent() {
        Optional<User> userOption = userService.getUserWithAuthorities();
        Long id = userOption.get().getId();
        Student student = studentRepository.findFirstByIdUserAuth(id);
        return student;
    }

    private String fileLocation;

    @Autowired
    private StudentService studentService;

    @CrossOrigin(origins = "http://localhost:9000")
    @PostMapping("/uploadExcelFile")
    public void uploadFile(MultipartFile file) throws IOException {
        List<Student> studentList = studentService.getListFromExcel(file);
        studentRepository.saveAll(studentList);
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value = "/download/{fileName}", method = RequestMethod.GET)
    public void dowloadTemplate(HttpServletResponse response, @PathVariable(name = "fileName") String filename) throws IOException {
        try {
            File file = ResourceUtils.getFile("classpath:templates/" + filename + ".xlsx");
            byte[] data = Files.readAllBytes(file.toPath());
            // Thiết lập thông tin trả về
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
            response.setContentLength(data.length);
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception ex) {
        }
    }

    @PostMapping("/saveStudent")
    public Student saveStudent(@RequestBody Student student) {
        Optional<User> userOption = userService.getUserWithAuthorities();

        if(userOption.get().getAuthorities().iterator().next().getName().equalsIgnoreCase(AuthoritiesConstants.ADMIN)){
            studentRepository.save(student);
            return student;
        }
        Long id = userOption.get().getId();
        Teacher teacher = teacherRepository.findFirstByIdUserAuth(id);
        student.setIdTeacher(teacher.getId()+"");
        studentRepository.save(student);
        return student;
    }

    @PostMapping("/removeStudent")
    public void removeStudent(@RequestBody List<Student> student) {
        studentService.removeStudent(student);
    }

    @GetMapping("/getAllStudentWithOutTeacher")
    public List<Student> getAllStudentWithOutTeacher() {
        List<Student> studentList = studentRepository.findAll();
        for (Student student : studentList){
            if(student.getIdTeacher()!=null){
                studentList.remove(student);
            }
        }
        return studentList;
    }


    @PostMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return student;
    }





}
