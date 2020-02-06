package com.example.capturetheapp.controllers;

import android.util.Log;

import com.example.capturetheapp.cecAPI;
import com.example.capturetheapp.model.PhoneInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class PhoneController implements Callback<PhoneInfo> {
    static final String BASE_URL = "http://10.0.0.104:3000/";

    public void start(PhoneInfo data) {
        try {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            cecAPI cecAPI = retrofit.create(cecAPI.class);

            Call<PhoneInfo> call = cecAPI.createPhone(data);
            call.enqueue(this);
        } catch (Exception e) {
            //Log.e(TAG, "DataController, start(), Retrofit library", e);
        }

    }

    @Override
    public void onResponse(Call<PhoneInfo> call, Response<PhoneInfo> response) {
        if (response.isSuccessful()) {
            PhoneInfo changesList = response.body();

        } else {
            //Log.d(TAG, "onResponse: " + response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<PhoneInfo> call, Throwable t) {
        Log.e(TAG, "onFailure: ", t);
    }
}
