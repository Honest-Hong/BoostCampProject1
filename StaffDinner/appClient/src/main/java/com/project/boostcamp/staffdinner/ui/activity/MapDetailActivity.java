package com.project.boostcamp.staffdinner.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.boostcamp.staffdinner.R;
import com.project.boostcamp.publiclibrary.util.GeocoderHelper;

public class MapDetailActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener, View.OnClickListener{
    public static final String EXTRA_LATITUDE = "latitude";
    public static final String EXTRA_LONGITUDE = "longitude";
    public static final String EXTRA_READ_ONLY = "readOnly";
    public static final int REQUEST_LOCATION = 0x100;
    private double latitude;
    private double longitude;
    private boolean readOnly;

    private TextView textLocation;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_detail);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        latitude = getIntent().getDoubleExtra(EXTRA_LATITUDE, 0);
        longitude = getIntent().getDoubleExtra(EXTRA_LONGITUDE, 0);
        readOnly = getIntent().getBooleanExtra(EXTRA_READ_ONLY, false);

        textLocation = (TextView)findViewById(R.id.text_location);
        findViewById(R.id.button_select).setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        if(readOnly) {
            googleMap.addMarker(new MarkerOptions()
                    .position(latLng));
        } else {
            UiSettings uiSettings = googleMap.getUiSettings();
            uiSettings.setMyLocationButtonEnabled(true);
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                uiSettings.setZoomControlsEnabled(true);
                googleMap.setMyLocationEnabled(true);
            }
            googleMap.setOnCameraIdleListener(this);
        }
    }

    @Override
    public void onCameraIdle() {
        latitude = googleMap.getCameraPosition().target.latitude;
        longitude = googleMap.getCameraPosition().target.longitude;
        textLocation.setText(GeocoderHelper.getAddress(this, new LatLng(latitude, longitude)));
    }

    public static void show(Fragment fragment, double startLatitude, double startLongitude, boolean readOnly) {
        Intent intent = new Intent(fragment.getContext(), MapDetailActivity.class);
        intent.putExtra(EXTRA_LATITUDE, startLatitude);
        intent.putExtra(EXTRA_LONGITUDE, startLongitude);
        intent.putExtra(EXTRA_READ_ONLY, readOnly);
        if(readOnly) {
            fragment.startActivity(intent);
        } else {
            fragment.startActivityForResult(intent, REQUEST_LOCATION);
        }
    }

    public static void show(Activity activity, double latitude, double longitude, boolean readOnly) {
        Intent intent = new Intent(activity, MapDetailActivity.class);
        intent.putExtra(EXTRA_LATITUDE, latitude);
        intent.putExtra(EXTRA_LONGITUDE, longitude);
        intent.putExtra(EXTRA_READ_ONLY, readOnly);
        if(readOnly) {
            activity.startActivityForResult(intent, REQUEST_LOCATION);
        }
    }

    @Override
    public void onClick(View view) {
        if(!readOnly) {
            getIntent().putExtra(EXTRA_LATITUDE, latitude);
            getIntent().putExtra(EXTRA_LONGITUDE, longitude);
            setResult(RESULT_OK, getIntent());
        }
        finish();
    }
}
