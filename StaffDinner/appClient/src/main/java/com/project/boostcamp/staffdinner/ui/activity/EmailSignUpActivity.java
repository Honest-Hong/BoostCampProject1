package com.project.boostcamp.staffdinner.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.boostcamp.publiclibrary.api.RetrofitClient;
import com.project.boostcamp.publiclibrary.data.AccountType;
import com.project.boostcamp.publiclibrary.data.ExtraType;
import com.project.boostcamp.publiclibrary.domain.LoginDTO;
import com.project.boostcamp.publiclibrary.util.EditTextHelper;
import com.project.boostcamp.publiclibrary.util.SharedPreperenceHelper;
import com.project.boostcamp.publiclibrary.util.StringHelper;
import com.project.boostcamp.staffdinner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 이메일로 회원가입을 시작하기 위해서 이메일과 비밀번호를 입력받는 액티비티
 */
public class EmailSignUpActivity extends AppCompatActivity {
    @BindView(R.id.edit_email) EditText editEmail;
    @BindView(R.id.edit_password) EditText editPassword;
    @BindView(R.id.edit_password2) EditText editPassword2;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private String id;
    private final int type = AccountType.TYPE_EMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sign_up);
        ButterKnife.bind(this);
        initFirebaseAuth();
    }

    private void initFirebaseAuth() {
        auth = FirebaseAuth.getInstance();
    }

    @OnClick({R.id.button_prev, R.id.button_next})
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_prev:
                finish();
                break;
            case R.id.button_next:
                doEmailSignUp();
                break;
        }
    }

    private void doEmailSignUp() {
        if(checkValidate()) {
            final String email = editEmail.getText().toString();
            final String password = editPassword.getText().toString();
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        id = task.getResult().getUser().getUid();
                        checkAlreadyJoined();
                    } else {
                        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("HTJ", "doEmailSignUp-onComplete!");
                                if(task.isSuccessful()) {
                                    id = task.getResult().getUser().getUid();
                                    checkAlreadyJoined();
                                } else {
                                    Log.d("HTJ", "doEmailSignUp warning: " + task.getException());
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    private boolean checkValidate() {
        if(!editPassword.getText().toString().equals(editPassword2.getText().toString())) {
            Toast.makeText(this, "비밀번호 불일치", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!StringHelper.isValidEmail(editEmail.getText().toString())) {
            Toast.makeText(this, "올바르지 않은 이메일 형식", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

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
                    Intent intent = new Intent(EmailSignUpActivity.this, JoinActivity.class);
                    intent.putExtra(ExtraType.EXTRA_ID, id);
                    intent.putExtra(ExtraType.EXTRA_TYPE, type);
                    startActivity(intent);
                    finish();
                } else {
                    SharedPreperenceHelper.getInstance(EmailSignUpActivity.this).saveLogin(dto);
                    startActivity(new Intent(EmailSignUpActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginDTO> call, Throwable t) {
                Log.e("HTJ", "login onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null) {
            auth.addAuthStateListener(authStateListener);
        }
    }
}
