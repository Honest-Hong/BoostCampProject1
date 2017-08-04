package com.project.boostcamp.publiclibrary.api;

import android.util.Log;

import com.google.gson.Gson;
import com.project.boostcamp.publiclibrary.data.Estimate;
import com.project.boostcamp.publiclibrary.domain.ClientEstimateDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hong Tae Joon on 2017-08-01.
 * 고객용 리트로핏 클래스
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

    public void getEstimateList(String applicationId, final DataReceiver<ArrayList<ClientEstimateDTO>> dataReceiver) {
        clientService.getEstimateList(applicationId).enqueue(new Callback<ArrayList<ClientEstimateDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<ClientEstimateDTO>> call, Response<ArrayList<ClientEstimateDTO>> response) {
                Log.d("HTJ", "getEstimateList: " + new Gson().toJson(response.body()));
                dataReceiver.onReceive(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ClientEstimateDTO>> call, Throwable t) {
                Log.e("HTJ", "Fail to client get estimate list: " + t.getMessage());
                dataReceiver.onFail();
            }
        });
    }
}
