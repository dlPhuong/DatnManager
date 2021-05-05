package com.phuong.datn.web.rest;


import com.phuong.datn.domain.File;
import com.phuong.datn.domain.Response.BaseResponse;
import com.phuong.datn.repository.FileRepository;
import com.phuong.datn.security.jwt.JWTFilter;
import com.phuong.datn.web.rest.vm.LoginVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hihi")
public class Testcontroller {
    @Autowired
    FileRepository repository;

    @GetMapping("/hihi1")
    public BaseResponse authorize() {
        BaseResponse baseResponse = new BaseResponse("stt", "mess", repository.findAll());
        return baseResponse;
    }
}
