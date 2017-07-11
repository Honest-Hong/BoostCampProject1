package com.project.boostcamp.boostcampsample1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.edit_email) EditText editEmail; // 이메일 입력 창
    @BindView(R.id.edit_password) EditText editPassword; // 비밀번호 입력 창
    @BindView(R.id.edit_name) EditText editName; // 이름 입력 창
    @BindView(R.id.button_sign_in) Button btnSignIn; // 로그인 버튼
    @BindView(R.id.button_sign_out) Button btnSignOut; // 로그아웃 버튼
    @BindView(R.id.button_join) Button btnJoin; // 회원가입 버튼
    @BindView(R.id.button_set) Button btnSet; // 이름 설정 버튼

    private FirebaseAuth mAuth; // 사용자 인증 처리 객체
    private FirebaseAuth.AuthStateListener mAuthStateListener; // 사용자의 로그인 상태 변화에 응답하는 리스너

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Fabric.with(this, new Crashlytics());

        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    // User is signed in
                    Log.d("HTJ_TAG", "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(MainActivity.this, "Signed in", Toast.LENGTH_SHORT).show();

                    editEmail.setText(user.getEmail());
                    editEmail.setEnabled(false);
                    editPassword.setEnabled(false);
                    editName.setVisibility(View.VISIBLE);
                    btnSignIn.setVisibility(View.GONE);
                    btnSignOut.setVisibility(View.VISIBLE);
                    btnJoin.setVisibility(View.GONE);
                    btnSet.setVisibility(View.VISIBLE);
                } else {
                    // User is signed out
                    Log.d("HTJ_TAG", "onAuthStateChanged:signed_out");
                    Toast.makeText(MainActivity.this, "Signed out", Toast.LENGTH_SHORT).show();

                    editEmail.setText("");
                    editEmail.setEnabled(true);
                    editPassword.setEnabled(true);
                    editName.setVisibility(View.GONE);
                    btnSignIn.setVisibility(View.VISIBLE);
                    btnSignOut.setVisibility(View.GONE);
                    btnJoin.setVisibility(View.VISIBLE);
                    btnSet.setVisibility(View.GONE);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthStateListener);
    }

    @OnClick({R.id.button_sign_in, R.id.button_sign_out, R.id.button_join, R.id.button_crash, R.id.button_set})
    public void onButtonClick(Button btn) {
        switch(btn.getId()) {
            case R.id.button_sign_in:
                signIn();
                break;
            case R.id.button_sign_out:
                signOut();
                break;
            case R.id.button_join:
                join();
                break;
            case R.id.button_crash:
                FirebaseCrash.report(new Exception("My first Android non-fatal error"));
                break;
            case R.id.button_set:
                requestDatabase();
                break;
        }
    }

    private void requestDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        DatabaseReference refUsers = myRef.child("users");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String email = editEmail.getText().toString(); // 사용자 이메일
        String name = editName.getText().toString(); // 사용자 이름

        refUsers.child(uid).child("email").setValue(email);
        refUsers.child(uid).child("name").setValue(name);
    }

    private void join() {
        String email = editEmail.getText().toString(); // 사용자 이메일
        String password = editPassword.getText().toString(); // 사용자 비밀번호

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("HTJ_TAG", "createUserWithEmail:onComplete: " + task.isSuccessful());
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Join complete!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signIn() {
        String email = editEmail.getText().toString(); // 사용자 이메일
        String password = editPassword.getText().toString(); // 사용자 비밀번호
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("HTJ_TAG", "signInUserWithEmail:onComplete: " + task.isSuccessful());
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Sign in complete!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
}
