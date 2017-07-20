package com.project.boostcamp.thirdminiproject.fragment;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
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
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.boostcamp.thirdminiproject.MainActivity;
import com.project.boostcamp.thirdminiproject.onNextClickListener;
import com.project.boostcamp.thirdminiproject.R;
import com.project.boostcamp.thirdminiproject.Restraurant;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hong Tae Joon on 2017-07-18.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener{
    @BindView(R.id.edit_address) TextView textAddress; // 맵 상단의 주소를 보여주는 텍스트
    private onNextClickListener onNextClickListener; // 다음 버튼을 클릭하였을 때의 이벤트 처리
    private Restraurant rest; // 현재 맛집 정보
    private Geocoder geocoder; // 위도, 경도 <-> 주소 변환해주는 용도
    private GoogleMap googleMap; // 구글 맵

    public static MapFragment newInstance(@NonNull Restraurant rest) {
        Bundle arg = new Bundle();
        arg.putParcelable("rest", rest);
        MapFragment mapFragment = new MapFragment();
        mapFragment.setArguments(arg);
        return mapFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onNextClickListener = (MainActivity)context;
        geocoder = new Geocoder(context);
        rest = getArguments() != null
                ? (Restraurant)getArguments().getParcelable("rest")
                : new Restraurant();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View cv = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, cv);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        textAddress.setText(rest.getAddress());
        return cv;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_map, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.button_next})
    public void onClick(View view) {
        if(view.getId() == R.id.button_next) {
            onNextClickListener.onNext(this, rest);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        try {
            // 맛집의 주소로 위도와 경도 검색
            List<Address> addresses = geocoder.getFromLocationName(rest.getAddress(), 1);
            LatLng latLng = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
            // 검색 결과에 맞게 마커와 카메라 위치
            googleMap.addMarker(new MarkerOptions()
                    .title(rest.getName())
                    .snippet(rest.getDesc())
                    .draggable(true)
                    .position(latLng));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            googleMap.setOnMarkerDragListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
    }

    @Override
    public void onMarkerDrag(Marker marker) {
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        // 마커 위치 저장
        LatLng latLng = marker.getPosition();
        try {
            // 마커 위치의 주소 탐색
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            String address = "";
            for(int i = 0; i<addresses.get(0).getMaxAddressLineIndex(); i++) {
                address += addresses.get(0).getAddressLine(i) + " ";
            }
            // 위치 갱신
            rest.setAddress(address);
            textAddress.setText(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 마커 위치로 카메라 이동
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
