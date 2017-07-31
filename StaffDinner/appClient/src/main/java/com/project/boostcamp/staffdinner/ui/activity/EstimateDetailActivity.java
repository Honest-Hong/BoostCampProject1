package com.project.boostcamp.staffdinner.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.project.boostcamp.staffdinner.GlideApp;
import com.project.boostcamp.staffdinner.R;
import com.project.boostcamp.publiclibrary.data.Estimate;
import com.project.boostcamp.publiclibrary.dialog.DialogResultListener;
import com.project.boostcamp.publiclibrary.dialog.MyAlertDialog;
import com.project.boostcamp.publiclibrary.util.GeocoderHelper;
import com.project.boostcamp.publiclibrary.util.MarkerBuilder;

public class EstimateDetailActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback, DialogResultListener, GoogleMap.OnMapClickListener {
    private Estimate estimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_detail);

        if(getIntent() != null) {
            estimate = getIntent().getParcelableExtra(Estimate.class.getName());
            setupView();
        } else {
            finish();
        }
    }

    private void setupView() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        toolbar.setTitle(R.string.estimate_detail_activity_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        ImageView imageView = (ImageView)findViewById(R.id.image_view);
        TextView textName = (TextView)findViewById(R.id.text_name);
        TextView textDate = (TextView)findViewById(R.id.text_date);
        TextView textMessage = (TextView)findViewById(R.id.text_message);
        TextView textStyle = (TextView)findViewById(R.id.text_style);
        TextView textMenu = (TextView)findViewById(R.id.text_menu);
        TextView textLocation = (TextView)findViewById(R.id.text_location);

        GlideApp.with(this)
                .load(estimate.getRestImgUrl())
                .centerCrop()
                .into(imageView);
        textName.setText(estimate.getRestName());
        textDate.setText(estimate.getWritedTime() + "");
        textMessage.setText(estimate.getMessage());
        textStyle.setText(estimate.getRestStyle());
        textMenu.setText(estimate.getRestMenu());
        textLocation.setText(GeocoderHelper.getAddress(this, new LatLng(estimate.getRestLat(), estimate.getRestLng())));

        findViewById(R.id.button_contact).setOnClickListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        MyAlertDialog.newInstance(getString(R.string.dialog_alert_title), getString(R.string.dialog_contact_message), this)
                .show(getSupportFragmentManager(), null);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setScrollGesturesEnabled(false);
        uiSettings.setZoomGesturesEnabled(false);
        LatLng latLng = new LatLng(estimate.getRestLat()
                , estimate.getRestLng());
        googleMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(latLng, 16));
        googleMap.addMarker(MarkerBuilder.simple(latLng));
        googleMap.setOnMapClickListener(this);
    }

    @Override
    public void onPositive() {
        Toast.makeText(this, "계약 완료", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onNegative() {
        Toast.makeText(this, "계약 중단", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        MapDetailActivity.show(this, estimate.getRestLat(), estimate.getRestLng(), true);
    }
}
