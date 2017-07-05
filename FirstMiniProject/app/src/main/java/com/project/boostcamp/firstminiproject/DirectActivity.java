package com.project.boostcamp.firstminiproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class DirectActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct);

        findViewById(R.id.button_return).setOnClickListener(this);
        findViewById(R.id.button_send).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_return:
                finish();
                break;
            case R.id.button_send:
                Toast.makeText(this, "보내기", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
