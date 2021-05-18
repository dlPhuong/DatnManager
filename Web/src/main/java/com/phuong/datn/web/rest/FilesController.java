package com.phuong.datn.web.rest;

import com.phuong.datn.domain.File;
import com.phuong.datn.repository.FileRepository;
import com.phuong.datn.service.ResponseMessage1;
import com.phuong.datn.service.fileStorageService;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.aspectj.weaver.ast.Var;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import springfox.documentation.service.ResponseMessage;


import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:9000")
@RestController
@RequestMapping("/api")
public class FilesController {

    @Autowired
    fileStorageService storageService;

    @Autowired
    FileRepository fileRepository;

    @PostMapping("/upload")
    public File uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            String filepath = storageService.save(file);
            File file1 = fileRepository.save(new File(filepath, filepath));
            return file1;
        } catch (Exception e) {

        }
        return null;
    }

    @RequestMapping(value = "/dowload", method = RequestMethod.GET)
    public void download1(HttpServletResponse response,@RequestParam(value = "filename", required = false) String filename) throws IOException {
        try {
            java.io.File file = new java.io.File("C:\\Users\\tungn\\Uploads\\", filename);
            byte[] data = FileUtils.readFileToByteArray(file);
            // Thiết lập thông tin trả về
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
            response.setContentLength(data.length);
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}

