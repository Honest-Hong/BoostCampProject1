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
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.project.boostcamp.publiclibrary.api.RetrofitClient;
import com.project.boostcamp.publiclibrary.data.Client;
import com.project.boostcamp.publiclibrary.util.SharedPreperenceHelper;
import com.project.boostcamp.staffdinner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{
    private View rootView;
    @BindView(R.id.edit_name) EditText editName;
    @BindView(R.id.edit_phone) EditText editPhone;
    @BindView(R.id.button_join) Button btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ButterKnife.bind(this);
        setupView();
    }

    private void setupView() {
        rootView = getWindow().getDecorView().getRootView();
        btnJoin.setOnClickListener(this);

        CheckBox checkBox = (CheckBox)findViewById(R.id.check_box);
        checkBox.setOnCheckedChangeListener(this);

        Client client = SharedPreperenceHelper.getInstance(this).getClient();
        editName.setText(client.getName());
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

        Client client = SharedPreperenceHelper.getInstance(this).getClient();
        client.setName(name);
        client.setPhone(phone);
        client.setToken(FirebaseInstanceId.getInstance().getToken());
        SharedPreperenceHelper.getInstance(this).saveClient(client);
        requestJoin(client);
    }

    private void requestJoin(Client client) {
        RetrofitClient.getInstance().clientService.join(client).enqueue(joinCallback);
    }

    private Callback<Client> joinCallback = new Callback<Client>() {
        @Override
        public void onResponse(Call<Client> call, Response<Client> response) {
            Log.d("HTJ", "join onResponse: " + response.body().toString());
            startMainActivity();
        }

        @Override
        public void onFailure(Call<Client> call, Throwable t) {
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
