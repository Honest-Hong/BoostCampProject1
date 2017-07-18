package com.project.boostcamp.thirdminiproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hong Tae Joon on 2017-07-18.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private OnButtonClickListener onButtonClickListener;
    private Restraurant rest;

    public static MapFragment newInstance(@NonNull Restraurant rest) {
        Bundle arg = new Bundle();
        arg.putSerializable("rest", rest);
        MapFragment mapFragment = new MapFragment();
        mapFragment.setArguments(arg);
        return mapFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(getArguments() != null) {
            rest = (Restraurant)getArguments().getSerializable("rest");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onButtonClickListener = (MainActivity)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View cv = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, cv);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return cv;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_map, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onButtonClickListener.onClick(this, MainActivity.BUTTON_CLOSE);
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.button_next})
    public void onClick(View view) {
        if(view.getId() == R.id.button_next) {
            onButtonClickListener.onClick(this, MainActivity.BUTTON_NEXT);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(rest.getLatitude(), rest.getLongitude());
        googleMap.addMarker(new MarkerOptions()
                .title(rest.getName())
                .snippet(rest.getDesc())
                .position(latLng));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
