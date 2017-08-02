package com.project.boostcamp.staffdinner.service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.project.boostcamp.publiclibrary.api.RetrofitClient;
import com.project.boostcamp.publiclibrary.data.Client;
import com.project.boostcamp.publiclibrary.util.SharedPreperenceHelper;

/**
 * Created by Hong Tae Joon on 2017-07-27.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        Client client = SharedPreperenceHelper.getInstance(getApplicationContext()).getClient();
        if(client.getToken() != null) {
            RetrofitClient.getInstance().clientService.updateToken(
                    client.getId(),
                    client.getType(),
                    FirebaseInstanceId.getInstance().getToken());
        }
    }
}
