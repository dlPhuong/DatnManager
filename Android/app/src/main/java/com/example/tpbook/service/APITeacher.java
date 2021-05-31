package com.example.tpbook.service;

import com.example.tpbook.model.data.Teacher;
import com.example.tpbook.model.response.ReportResponse;
import com.example.tpbook.model.response.TeacherResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APITeacher {

    @GET("api/getInfoTeacher")
    Call<Teacher> getInfoTeacher(Long id);


}
