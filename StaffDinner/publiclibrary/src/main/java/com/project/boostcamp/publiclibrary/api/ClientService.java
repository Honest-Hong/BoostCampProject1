package com.project.boostcamp.publiclibrary.api;

import com.project.boostcamp.publiclibrary.data.Application;
import com.project.boostcamp.publiclibrary.domain.ClientApplicationDTO;
import com.project.boostcamp.publiclibrary.domain.ClientDTO;
import com.project.boostcamp.publiclibrary.domain.ClientJoinDTO;
import com.project.boostcamp.publiclibrary.domain.LoginDTO;
import com.project.boostcamp.publiclibrary.domain.ResultIntDTO;
import com.project.boostcamp.publiclibrary.domain.ResultStringDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Hong Tae Joon on 2017-08-01.
 */

public interface ClientService {
    @POST("/client/login")
    Call<LoginDTO> login(@Body LoginDTO loginDTO);

    @POST("/client/join")
    Call<LoginDTO> join(@Body ClientJoinDTO clientJoinDTO);

    @GET("/client/{id}/application")
    Call<ClientApplicationDTO> getApplication(@Path("id") String id);

    @POST("/client/{id}/application")
    Call<ResultStringDTO> setApplication(@Path("id") String id, @Body ClientApplicationDTO ClientApplicationDTO);

    @POST("/application/{id}/cancel")
    Call<ResultIntDTO> cancelApplication(@Path("id") String id);

    @FormUrlEncoded
    @PUT("/client/{id}/token")
    Call<ResultIntDTO> updateToken(@Path("id") String id, @Field("type") int type, @Field("token") String token);
}
