package com.phuong.datn.web.rest;

import com.phuong.datn.domain.File;
import com.phuong.datn.repository.FileRepository;
import com.phuong.datn.service.ResponseMessage1;
import com.phuong.datn.service.fileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import springfox.documentation.service.ResponseMessage;


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

    @GetMapping("/files")
    public ResponseEntity<List<File>> getListFiles() {
        List<File> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new File(url, filename);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
