package com.project.boostcamp.staffdinner.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.project.boostcamp.staffdinner.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_TOKEN = "token";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.button_login).setOnClickListener(this);

        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    @Override
    public void onClick(View view) {
        // TODO: 2017-07-31 카카오톡 로그임
        // TODO 구글 로그인을 실행한 후에 처음 로그인 하는 사람은 회원가입으로 넘어간다.

        boolean needJoin = true;
        if(needJoin) {
            startActivity(new Intent(this, JoinActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private ISessionCallback callback = new ISessionCallback() {
        @Override
        public void onSessionOpened() {
            String token = Session.getCurrentSession().getTokenInfo().getAccessToken();
            redirectJoinActivity(token);
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Log.e("HTJ", exception.toString());
            }
        }
    };

    private void redirectJoinActivity(String token) {
        Intent intent = new Intent(this, JoinActivity.class);
        intent.putExtra(EXTRA_TOKEN, token);
        startActivity(intent);
        finish();
    }
}
