package com.project.boostcamp.serverexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Hong Tae Joon on 2017-07-20.
 */

public interface ServerService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://220.230.122.122:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ServerService service = ServerService.retrofit.create(ServerService.class);

    @GET("users/{id}")
    Call<List<User>> getUser(@Path("id") String id);

    @GET("users/list")
    Call<List<User>> getUserList();

    @POST("users/create")
    Call<User> createUser(@Body User user);
}
