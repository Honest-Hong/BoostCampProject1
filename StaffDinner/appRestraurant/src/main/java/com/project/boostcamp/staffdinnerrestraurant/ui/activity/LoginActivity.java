package com.project.boostcamp.staffdinnerrestraurant.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.project.boostcamp.staffdinnerrestraurant.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.button_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // TODO 구글 로그인을 실행한 후에 처음 로그인 하는 사람은 회원가입으로 넘어간다.

        boolean needJoin = true;
        if(needJoin) {
            startActivity(new Intent(this, JoinActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
