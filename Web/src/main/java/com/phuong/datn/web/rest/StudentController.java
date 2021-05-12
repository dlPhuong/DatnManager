package com.phuong.datn.web.rest;


import com.phuong.datn.config.Commons;
import com.phuong.datn.domain.Response.BaseResponse;
import com.phuong.datn.domain.Student;
import com.phuong.datn.repository.StudentRepository;
import com.phuong.datn.repository.TeacherRepository;
import com.phuong.datn.repository.TopicRepository;
import com.phuong.datn.service.StudentService;
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

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
//    @GetMapping("/getAllStudent")
//    public BaseResponse getALlStudent() {
//        BaseResponse baseResponse = new BaseResponse(Commons.SUCCESS, Commons.SUCCESS, studentRepository.findAll());
//        return baseResponse;
//    }

    @GetMapping("/getAllStudent")
    public List<Student> getALlStudent() {
        //BaseResponse baseResponse = new BaseResponse(Commons.SUCCESS, Commons.SUCCESS, studentRepository.findAll());
        return studentRepository.findAll();
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
            File file = ResourceUtils.getFile("classpath:templates/" + filename);
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
    public void saveStudent(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @PostMapping("/removeStudent")
    public void removeStudent(@RequestBody List<Student> student) {
        studentService.removeStudent(student);
    }

}
