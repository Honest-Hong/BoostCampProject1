package com.project.boostcamp.staffdinnerrestraurant.service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.project.boostcamp.publiclibrary.api.RetrofitClient;
import com.project.boostcamp.publiclibrary.data.Admin;
import com.project.boostcamp.publiclibrary.data.Client;
import com.project.boostcamp.publiclibrary.util.SharedPreperenceHelper;

/**
 * Created by Hong Tae Joon on 2017-07-27.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        Admin admin = SharedPreperenceHelper.getInstance(getApplicationContext()).getAdmin();
        if(admin != null && admin.getToken() != null) {
            RetrofitClient.getInstance().clientService.updateToken(
                    admin.getId(),
                    admin.getType(),
                    FirebaseInstanceId.getInstance().getToken());
        }
    }
}
