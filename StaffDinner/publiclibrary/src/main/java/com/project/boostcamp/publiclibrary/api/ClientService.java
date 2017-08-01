package com.project.boostcamp.publiclibrary.api;

import com.project.boostcamp.publiclibrary.data.Apply;
import com.project.boostcamp.publiclibrary.data.Client;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Hong Tae Joon on 2017-08-01.
 */

public interface ClientService {
    @POST("/client/login")
    Call<Client> login(@Body Client client);

    @POST("/client/join")
    Call<Client> join(@Body Client client);

    @GET("/client/{id}/application")
    Call<Apply> getApplication(@Path("id") String id);

    @POST("/client/{id}/application")
    Call<String> setApplication(@Path("id") Apply apply);
}
