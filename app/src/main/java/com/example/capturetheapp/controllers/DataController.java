package com.example.capturetheapp.controllers;

import android.util.Log;

import com.example.capturetheapp.cecAPI;
import com.example.capturetheapp.model.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class DataController implements Callback<Data> {
    static final String BASE_URL = "http://10.0.0.104:3000/";

    public void start(Data data) {
        try {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            cecAPI cecAPI = retrofit.create(cecAPI.class);

            Call<Data> call = cecAPI.sendData(data);
            call.enqueue(this);
        } catch (Exception e) {
            //Log.e(TAG, "DataController, start(), Retrofit library", e);
        }

    }

    @Override
    public void onResponse(Call<Data> call, Response<Data> response) {
        if (response.isSuccessful()) {
            Data changesList = response.body();

        } else {
            //Log.d(TAG, "onResponse: " + response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Data> call, Throwable t) {
        Log.e(TAG, "onFailure: ", t);
    }
}
