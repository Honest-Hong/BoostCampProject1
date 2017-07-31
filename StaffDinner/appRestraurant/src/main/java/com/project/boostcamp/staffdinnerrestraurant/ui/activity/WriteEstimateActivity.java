package com.project.boostcamp.staffdinnerrestraurant.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.project.boostcamp.publiclibrary.dialog.DialogResultListener;
import com.project.boostcamp.publiclibrary.dialog.MyAlertDialog;
import com.project.boostcamp.staffdinnerrestraurant.R;

public class WriteEstimateActivity extends AppCompatActivity implements DialogResultListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_estimate);

        setupToolbar();

        // TODO: 2017-07-31 견적서를 작성하는 화면 완성
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.write_estimate_activity_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
    }

    public void sendEstimate() {
        MyAlertDialog.newInstance(
                getString(R.string.text_alert_dialog_title)
                , getString(R.string.text_alert_send_estimate)
                , this)
                .show(getSupportFragmentManager(), null);
    }

    @Override
    public void onPositive() {

    }

    @Override
    public void onNegative() {

    }
}
