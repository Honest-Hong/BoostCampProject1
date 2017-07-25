package com.project.boostcamp.testproject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Hong Tae Joon on 2017-07-24.
 */

public interface ServerService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://220.230.122.122:3000/")
            .build();

    ServerService service = retrofit.create(ServerService.class);

    @FormUrlEncoded
    @PUT("/users/{email}/token")
    Call<Void> putToken(@Path("email") String email, @Field("token") String token);
}
