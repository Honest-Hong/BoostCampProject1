package com.project.boostcamp.staffdinner.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.project.boostcamp.publiclibrary.data.Client;
import com.project.boostcamp.publiclibrary.util.SharedPreperenceHelper;
import com.project.boostcamp.staffdinner.R;

public class SplashActivity extends AppCompatActivity {
    boolean isLogined = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Client client = SharedPreperenceHelper.getInstance(this).getClient();
        isLogined = client != null && client.getPhone() != null;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isLogined) {
                    handleNotification();
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, 1000);
    }

    private void handleNotification() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        if(getIntent().getExtras() != null) {
            String type = getIntent().getStringExtra(MainActivity.EXTRA_NOTIFICATION_TYPE);
            if(type != null) {
                intent.putExtra(MainActivity.EXTRA_NOTIFICATION_TYPE, Integer.valueOf(type));
            }
        }
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }
}