package com.example.tpbook.service;


import com.example.tpbook.model.data.TypeProduct;
import com.example.tpbook.model.response.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIproduct {

    @GET("product/getAll")
    Call<ProductResponse> getAllProduct();

    @GET("product/getTopProduct")
    Call<ProductResponse> getTopProduct();

    @GET("product/getProductForU")
    Call<ProductResponse> getProductForU();

    @GET("productType/getProductType")
    Call<List<TypeProduct>> getProductType();

    @FormUrlEncoded
    @POST("product/getAllWithType")
    Call<ProductResponse> getProductWithType(@Field("id_type") int id_type);

    @FormUrlEncoded
    @POST("product/searchProduct")
    Call<ProductResponse> searchProduct(@Field("name") String name);

}
