package com.example.tpbook.service;

import com.example.tpbook.model.DTO.ManagedUserVM;
import com.example.tpbook.model.data.Report;
import com.example.tpbook.model.data.Student;
import com.example.tpbook.model.data.Teacher;
import com.example.tpbook.model.data.User;
import com.example.tpbook.model.request.LoginRequest;
import com.example.tpbook.model.response.ReportResponse;
import com.example.tpbook.model.response.TokenResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIReport {

    @GET("api/getAllReportStudent")
    Call<List<Report>> getAllReport();

    @POST("api/updateReport")
    Call<Report> saveReport(@Body Report student);


}
