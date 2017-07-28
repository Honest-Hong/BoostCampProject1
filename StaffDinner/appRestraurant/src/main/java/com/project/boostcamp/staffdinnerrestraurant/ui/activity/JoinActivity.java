package com.project.boostcamp.staffdinnerrestraurant.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.project.boostcamp.staffdinnerrestraurant.R;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{
    private EditText editName;
    private EditText editPhone;
    private Button btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        setupView();
    }

    private void setupView() {
        btnJoin = (Button)findViewById(R.id.button_join);
        btnJoin.setOnClickListener(this);

        editName = (EditText)findViewById(R.id.edit_name);
        editPhone = (EditText)findViewById(R.id.edit_phone);

        CheckBox checkBox = (CheckBox)findViewById(R.id.check_box);
        checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();

//        if(name.equals("")) {
//            // 이름을 입력해주세요.
//            return;
//        }
//
//        if(phone.equals("")) {
//            // 전화번호를 입력해주세요.
//            return;
//        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        btnJoin.setEnabled(b);
    }
}
