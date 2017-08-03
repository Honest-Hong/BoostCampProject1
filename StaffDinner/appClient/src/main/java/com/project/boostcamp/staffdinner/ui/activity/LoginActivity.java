package com.project.boostcamp.staffdinner.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.project.boostcamp.publiclibrary.api.RetrofitClient;
import com.project.boostcamp.publiclibrary.data.AccountType;
import com.project.boostcamp.publiclibrary.data.Client;
import com.project.boostcamp.publiclibrary.domain.ClientDTO;
import com.project.boostcamp.publiclibrary.domain.LoginDTO;
import com.project.boostcamp.publiclibrary.util.SharedPreperenceHelper;
import com.project.boostcamp.staffdinner.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private String id;
    private int type;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        Session.getCurrentSession().addCallback(callbackKaKao);
        Session.getCurrentSession().checkAndImplicitOpen();
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, callbackFacebook);
    }

    @OnClick({R.id.text_kakao, R.id.text_facebook})
    public void onLoginClick(TextView v) {
        if(v.getId() == R.id.text_kakao) {
            Session.getCurrentSession().open(AuthType.KAKAO_LOGIN_ALL, this);
        } else if(v.getId() == R.id.text_facebook) {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        } else {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callbackKaKao);
    }

    // 카카오톡 로그인 부분
    private ISessionCallback callbackKaKao = new ISessionCallback() {
        @Override
        public void onSessionOpened() {
            List<String> propertyKeys = new ArrayList<String>();
            propertyKeys.add("kaccount_email");
            propertyKeys.add("nickname");
            propertyKeys.add("profile_image");
            propertyKeys.add("thumbnail_image");
            UserManagement.requestMe(new MeResponseCallback() {
                @Override
                public void onSuccess(UserProfile result) {
                    id = Long.toString(result.getId());
                    name = result.getNickname();
                    type = AccountType.TYPE_KAKAO;
                    checkAlreadyJoined();
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Log.e("HTJ", errorResult.getErrorMessage());
                }

                @Override
                public void onNotSignedUp() {
                    Log.e("HTJ", "onNotSignedUp");
                }
            }, propertyKeys, false);
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Log.e("HTJ", exception.toString());
            }
        }
    };

    // 페이스북 로그인 부분
    private FacebookCallback<LoginResult> callbackFacebook = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.d("HTJ", "facebook onSuccess");
            Profile.fetchProfileForCurrentAccessToken();
            id = loginResult.getAccessToken().getUserId();
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            Log.d("HTJ", "graph onComplete");
                            try {
                                name = object.getString("name");
                                type = AccountType.TYPE_FACEBOOK;
                                checkAlreadyJoined();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "name");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {
            Log.d("HTJ", "facebook onCancel");
        }

        @Override
        public void onError(FacebookException error) {
            Log.e("HTJ", "facebook onError: " + error.toString());
        }
    };

    private void checkAlreadyJoined() {
        LoginDTO dto = new LoginDTO();
        dto.setId(id);
        dto.setType(type);
        RetrofitClient.getInstance().clientService.login(dto).enqueue(new Callback<LoginDTO>() {
            @Override
            public void onResponse(Call<LoginDTO> call, Response<LoginDTO> response) {
                Log.d("HTJ", "login onResponse: " + response.body());
                LoginDTO dto = response.body();
                if(dto.getId() == null) {
                    Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                    intent.putExtra(JoinActivity.EXTRA_ID, id);
                    intent.putExtra(JoinActivity.EXTRA_TYPE, type);
                    intent.putExtra(JoinActivity.EXTRA_NAME, name);
                    startActivity(intent);
                    finish();
                } else {
                    SharedPreperenceHelper.getInstance(LoginActivity.this).saveLogin(dto);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginDTO> call, Throwable t) {
                Log.e("HTJ", "login onFailure: " + t.getMessage());
            }
        });
    }
}
