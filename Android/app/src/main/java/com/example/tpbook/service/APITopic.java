package com.example.tpbook.service;

import com.example.tpbook.model.response.ReportResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APITopic {

    @GET("api/getAllTopic")
    Call<ReportResponse> getAllReport();


}
