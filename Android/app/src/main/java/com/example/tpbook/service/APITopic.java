package com.example.tpbook.service;

import com.example.tpbook.model.DTO.ManagedUserVM;
import com.example.tpbook.model.data.Topic;
import com.example.tpbook.model.response.ReportResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APITopic {

    @GET("api/getAllTopicStudent")
    Call<List<Topic>> getAllTopicStudent();

    @POST("api/saveTopicStudent")
    Call<Topic> saveTopicStudent(@Body Topic topic);


}
