package com.project.boostcamp.publiclibrary.api;

import com.project.boostcamp.publiclibrary.data.Admin;
import com.project.boostcamp.publiclibrary.data.ApplyWithClient;
import com.project.boostcamp.publiclibrary.domain.AdminApplicationDTO;
import com.project.boostcamp.publiclibrary.domain.AdminDTO;
import com.project.boostcamp.publiclibrary.domain.AdminJoinDTO;
import com.project.boostcamp.publiclibrary.domain.LoginDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Hong Tae Joon on 2017-08-01.
 */

public interface AdminService {
    @POST("/admin/login")
    Call<LoginDTO> login(@Body LoginDTO loginDTO);

    @POST("/admin/join")
    Call<LoginDTO> join(@Body AdminJoinDTO adminJoinDTO);

    @GET("/application/location")
    Call<List<AdminApplicationDTO>> get(@Query("latitude") double latitude, @Query("longitude") double longitude, @Query("distance") float distance);

    @FormUrlEncoded
    @PUT("/admin/{id}/token")
    Call<String> updateToken(@Path("id") String id, @Field("type") int type, @Field("token") String token);
}
