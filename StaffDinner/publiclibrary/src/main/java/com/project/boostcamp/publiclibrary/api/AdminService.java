package com.project.boostcamp.publiclibrary.api;

import com.project.boostcamp.publiclibrary.data.Admin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Hong Tae Joon on 2017-08-01.
 */

public interface AdminService {
    @POST("/admin/login")
    Call<Admin> login(@Body Admin admin);

    @POST("/admin/join")
    Call<Admin> join(@Body Admin admin);
}
