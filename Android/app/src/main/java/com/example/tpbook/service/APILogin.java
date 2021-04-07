package com.example.tpbook.service;

import com.example.tpbook.model.DTO.ManagedUserVM;
import com.example.tpbook.model.data.User;
import com.example.tpbook.model.request.LoginRequest;
import com.example.tpbook.model.response.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APILogin {
    @POST("api/authenticate")
    Call<TokenResponse> authentication(@Body LoginRequest loginRequest);

    @GET("api/account")
    Call<User> account();

    @POST("api/register")
    Call<String> register(@Body ManagedUserVM managedUserVM);

}
