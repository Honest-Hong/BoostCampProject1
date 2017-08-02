package com.project.boostcamp.publiclibrary.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hong Tae Joon on 2017-08-01.
 */

public class RetrofitClient {
    private static RetrofitClient instance;
    private Retrofit retrofit;
    public ClientService clientService;
    public static RetrofitClient getInstance() {
        if(instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://52.78.76.86:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        clientService = retrofit.create(ClientService.class);
    }
}
