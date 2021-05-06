package com.example.tpbook.service;

import com.example.tpbook.model.response.ReportResponse;
import com.example.tpbook.model.response.TeacherResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APITeacher {

    @GET("api/getAllTeacher")
    Call<TeacherResponse> getAllteacher();


}
