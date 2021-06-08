package com.example.tpbook.service;

import com.example.tpbook.model.data.Student;
import com.example.tpbook.model.data.Teacher;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIStudent {

    @GET("api/getInfoStudent")
    Call<Student> getInfoStudent();

    @POST("api/saveStudent")
    Call<Student> saveStudent();




}
