package com.example.capturetheapp;

import com.example.capturetheapp.model.Data;
import com.example.capturetheapp.model.PhoneInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface cecAPI  {
    @POST("api/data")
    Call<Data> sendData(@Body Data text);

    @POST("api/info")
    Call<PhoneInfo> createPhone(@Body PhoneInfo info);
}
