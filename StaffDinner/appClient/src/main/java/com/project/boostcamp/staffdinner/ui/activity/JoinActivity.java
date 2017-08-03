package com.project.boostcamp.staffdinner.ui.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;
import com.project.boostcamp.publiclibrary.api.RetrofitClient;
import com.project.boostcamp.publiclibrary.data.Client;
import com.project.boostcamp.publiclibrary.domain.ClientDTO;
import com.project.boostcamp.publiclibrary.domain.ClientJoinDTO;
import com.project.boostcamp.publiclibrary.domain.LoginDTO;
import com.project.boostcamp.publiclibrary.util.SharedPreperenceHelper;
import com.project.boostcamp.staffdinner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_NAME = "name";
    private View rootView;
    @BindView(R.id.edit_name) EditText editName;
    @BindView(R.id.edit_phone) EditText editPhone;
    @BindView(R.id.button_join) Button btnJoin;
    private String id;
    private int type;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        if(getIntent() != null) {
            id = getIntent().getStringExtra(EXTRA_ID);
            type = getIntent().getIntExtra(EXTRA_TYPE, -1);
            name = getIntent().getStringExtra(EXTRA_NAME);
        }
        ButterKnife.bind(this);
        setupView();
    }

    private void setupView() {
        rootView = getWindow().getDecorView().getRootView();
        btnJoin.setOnClickListener(this);

        CheckBox checkBox = (CheckBox)findViewById(R.id.check_box);
        checkBox.setOnCheckedChangeListener(this);

        editName.setText(name);
    }

    @Override
    public void onClick(View view) {
        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();

        if(name.equals("")) {
            Snackbar.make(rootView, R.string.snack_need_name, Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(phone.equals("")) {
            Snackbar.make(rootView, R.string.snack_need_phone, Snackbar.LENGTH_SHORT).show();
            return;
        }

        ClientJoinDTO dto = new ClientJoinDTO();
        dto.setId(id);
        dto.setType(type);
        dto.setName(name);
        dto.setPhone(phone);
        dto.setToken(FirebaseInstanceId.getInstance().getToken());
        RetrofitClient.getInstance().clientService.join(dto).enqueue(joinCallback);
    }

    private Callback<LoginDTO> joinCallback = new Callback<LoginDTO>() {
        @Override
        public void onResponse(Call<LoginDTO> call, Response<LoginDTO> response) {
            Log.d("HTJ", "join onResponse: " + response.body());
            LoginDTO dto = response.body();
            if(dto.getId() != null) {
                SharedPreperenceHelper.getInstance(JoinActivity.this).saveLogin(dto);
                startMainActivity();
            } else {
                Log.e("HTJ", "Fail to join");
            }
        }

        @Override
        public void onFailure(Call<LoginDTO> call, Throwable t) {
            Log.e("HTJ", "join onFailure: " + t.getMessage());
        }
    };

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        btnJoin.setEnabled(b);
    }
}
