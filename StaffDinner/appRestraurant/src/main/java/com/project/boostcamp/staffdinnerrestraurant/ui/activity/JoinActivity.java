package com.project.boostcamp.staffdinnerrestraurant.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.project.boostcamp.publiclibrary.util.GeocoderHelper;
import com.project.boostcamp.publiclibrary.util.MarkerBuilder;
import com.project.boostcamp.publiclibrary.util.StringHelper;
import com.project.boostcamp.staffdinnerrestraurant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class JoinActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, OnMapReadyCallback, OnSuccessListener<Location>, GoogleApiClient.OnConnectionFailedListener {
    private static final int REQUEST_PERMISSION = 0x100;
    @BindView(R.id.edit_name) EditText editName;
    @BindView(R.id.edit_phone) EditText editPhone;
    @BindView(R.id.edit_style) EditText editStyle;
    @BindView(R.id.edit_menu) EditText editMenu;
    @BindView(R.id.edit_menu_cost) EditText editCost;
    @BindView(R.id.text_location) TextView textLocation;
    @BindView(R.id.button_join) Button btnJoin;
    @BindView(R.id.check_box) CheckBox checkBox;
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationClient; // 현재 위치를 가져오는 서비스
    private Marker marker; // 신청서의 위치 지도 마커

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ButterKnife.bind(this);

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();

        setupView();
    }

    private void setupView() {
        checkBox.setOnCheckedChangeListener(this);

        // TODO: 2017-07-31 위치 기능이 켜있는지 확인
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @OnClick(R.id.button_join)
    public void doJoin() {
        // TODO: 2017-07-31 입력한 값들의 유효성 판단

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        btnJoin.setEnabled(checkBox.isChecked());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        initGoogleMap();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, REQUEST_PERMISSION);
        } else {
            setMyLocation();
        }
    }

    private void initGoogleMap() {
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setScrollGesturesEnabled(false);
        uiSettings.setZoomGesturesEnabled(false);
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(16));
    }

    private void setMyLocation() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(this);
        }
    }

    @Override
    public void onSuccess(Location location) {
        if(location != null) {
            LatLng latLng = new LatLng(
                    location.getLatitude(),
                    location.getLongitude());
            setLocation(latLng);
        } else {
            Log.e("HTJ", "onSuccess: location is null");
        }
    }

    private void setLocation(LatLng latLng) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        String add = GeocoderHelper.getAddress(this, latLng);
        textLocation.setText(StringHelper.cutStart(add, 18));
        if(marker != null) {
            marker.remove();
        }
        marker = googleMap.addMarker(MarkerBuilder.simple(latLng));
    }

    @OnClick(R.id.button_search)
    public void searchLocation() {
        LatLng latLng = marker.getPosition();
        MapDetailActivity.show(this, latLng.latitude, latLng.longitude, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MapDetailActivity.REQUEST_LOCATION) {
            if(resultCode == RESULT_OK) {
                setLocation(new LatLng(
                        data.getDoubleExtra(MapDetailActivity.EXTRA_LATITUDE, 0),
                        data.getDoubleExtra(MapDetailActivity.EXTRA_LONGITUDE, 0)
                ));
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("HTJ", connectionResult.getErrorMessage());
    }
}
