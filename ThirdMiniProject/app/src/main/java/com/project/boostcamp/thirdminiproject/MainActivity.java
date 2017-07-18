package com.project.boostcamp.thirdminiproject;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnButtonClickListener, GoogleApiClient.OnConnectionFailedListener {
    public static final int BUTTON_CLOSE = 0x100;
    public static final int BUTTON_PREV = 0x101;
    public static final int BUTTON_NEXT = 0x102;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, AddFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void onClick(Fragment fragment, int id) {
        if(fragment instanceof AddFragment) {
            switch(id) {
                case BUTTON_CLOSE:
                    onBackPressed();
                    break;
                case BUTTON_NEXT:
                    Restraurant rest = ((AddFragment)fragment).getRest();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.fragment, MapFragment.newInstance(rest));
                    transaction.commit();
                    break;
                case BUTTON_PREV:
                    Toast.makeText(this, "이전", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if(fragment instanceof MapFragment) {
            switch(id) {
                case BUTTON_CLOSE:
                    onBackPressed();
                    break;
                case BUTTON_NEXT:
                    break;
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("HTJ", "onConnectionFailed");
    }
}
