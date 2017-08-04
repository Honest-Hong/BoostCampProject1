package com.project.boostcamp.staffdinner.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.project.boostcamp.publiclibrary.data.Contact;
import com.project.boostcamp.publiclibrary.data.ExtraType;
import com.project.boostcamp.publiclibrary.util.GeocoderHelper;
import com.project.boostcamp.publiclibrary.util.MarkerBuilder;
import com.project.boostcamp.staffdinner.R;

/**
 *  계약서를 자세히 볼 수 잇는 액티비티
 */
public class ContactDetailActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        if(getIntent() != null) {
            contact = getIntent().getParcelableExtra(Contact.class.getName());
            setupView();
        } else {
            finish();
        }
    }

    private void setupView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(R.string.contact_detail_activity_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        TextView textTitle = (TextView)findViewById(R.id.text_title);
        TextView textApplyDetail = (TextView)findViewById(R.id.text_apply_detail);
        TextView textEstimateDetail = (TextView)findViewById(R.id.text_estimate_detail);
        TextView textContactDetail = (TextView)findViewById(R.id.text_contact_detail);

        textTitle.setText(getString(R.string.text_contact_list_title, contact.getApplierName(), contact.getEstimaterName()));
        textApplyDetail.setText(getString(R.string.text_contact_apply_detail
                , contact.getApplyNumber()
                , GeocoderHelper.getAddress(this, new LatLng(contact.getApplyLat(), contact.getApplyLng()))
                , contact.getApplyDate()
                , contact.getApplierPhone()));
        textEstimateDetail.setText(getString(R.string.text_contact_estimate_detail
                , GeocoderHelper.getAddress(this, new LatLng(contact.getEstimateLat(), contact.getEstimateLng()))
                , contact.getEstimateDate()
                , contact.getEstimaterPhone()
                , contact.getEstimaterMessage()));
        textContactDetail.setText(getString(R.string.text_contact_contact_detail, contact.getContactDate()));

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setScrollGesturesEnabled(false);
        uiSettings.setZoomGesturesEnabled(false);
        LatLng latLng = new LatLng(contact.getApplyLat()
                , contact.getApplyLng());
        googleMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(latLng, 16));
        googleMap.addMarker(MarkerBuilder.simple(latLng));
        googleMap.setOnMapClickListener(this);
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
    public void onMapClick(LatLng latLng) {
        Intent intentMap = new Intent(this, MapDetailActivity.class);
        intentMap.putExtra(ExtraType.EXTRA_LATITUDE, contact.getEstimateLat());
        intentMap.putExtra(ExtraType.EXTRA_LONGITUDE, contact.getEstimateLng());
        intentMap.putExtra(ExtraType.EXTRA_READ_ONLY, true);
        startActivity(intentMap);
    }
}
