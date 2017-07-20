package com.project.boostcamp.serverexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ServerService.service.getUserList().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                for(int i=0; i<response.body().size(); i++) {
                    Log.d("HTJ", "name: " + response.body().get(i));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        findViewById(R.id.button_send).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        User user = new User();
        user.setName(((EditText)findViewById(R.id.edit_name)).getText().toString());
        user.setAge(Integer.valueOf(((EditText)findViewById(R.id.edit_age)).getText().toString()));
        user.setGen(((EditText)findViewById(R.id.edit_gen)).getText().toString());
        ServerService.service.createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "성공!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
