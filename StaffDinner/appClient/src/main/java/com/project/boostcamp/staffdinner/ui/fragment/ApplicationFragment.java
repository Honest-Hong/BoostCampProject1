package com.project.boostcamp.staffdinner.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

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
import com.project.boostcamp.staffdinner.R;
import com.project.boostcamp.publiclibrary.model.Apply;
import com.project.boostcamp.staffdinner.ui.activity.MapDetailActivity;
import com.project.boostcamp.staffdinner.ui.adapter.TextWheelAdapter;
import com.project.boostcamp.staffdinner.ui.dialog.DialogResultListener;
import com.project.boostcamp.staffdinner.ui.dialog.MyAlertDialog;
import com.project.boostcamp.publiclibrary.wheelpicker.WheelPicker;
import com.project.boostcamp.publiclibrary.util.TimeHelper;
import com.project.boostcamp.publiclibrary.util.GeocoderHelper;
import com.project.boostcamp.publiclibrary.util.MarkerBuilder;
import com.project.boostcamp.publiclibrary.util.SharedPreperenceHelper;
import com.project.boostcamp.publiclibrary.util.StringHelper;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class ApplicationFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback, OnSuccessListener<Location>, DialogResultListener {
    private static final int REQUEST_PERMISSION = 0x100;
    public static final int MAX_NUMBER = 99;
    public static final int MIN_NUMBER = 1;
    private NestedScrollView scrollView;
    private ImageView imageState; // 상단의 신청서 상태 이미지
    private TextView textState; // 상단의 신청서 상태 텍스트
    private EditText editTitle; // 신청서의 제목 입력창
    private EditText editNumber; // 신청서의 인원 입력창
    private MultiAutoCompleteTextView autoStyle; // 신청서의 분위기 입력창
    private EditText editMenu; // 신청서의 메뉴 입력창
    private TextView textLocation; // 신청서의 위치 텍스트
    private GoogleMap googleMap; // 신청서의 위치 지도
    private Marker marker; // 신청서의 위치 지도 마커
    private FusedLocationProviderClient fusedLocationClient; // 현재 위치를 가져오는 서비스
    private WheelPicker wheelHour; // 선청서의 시간 선택 도구
    private TextWheelAdapter wheelAdapterHour; //  신청서의 시간 뷰 어댑터
    private WheelPicker wheelMinute; // 신청서의 분 선택 도구
    private TextWheelAdapter wheelAdapterMinute; // 신청서의 분 뷰 어댑터
    private Apply apply = new Apply(); // 현재 신청서 정보
    private Button btnApply;
    private static ApplicationFragment instance;

    public static ApplicationFragment getInstance() {
        if(instance == null) {
            instance = new ApplicationFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_application, container, false);
        setupView(v);
        setupButton(v);
        setupWheel(v);
        loadData();
        return v;
    }

    private void setupView(View v) {
        scrollView = (NestedScrollView)v.findViewById(R.id.scroll_view);
        imageState = (ImageView)v.findViewById(R.id.image_state);
        textState = (TextView)v.findViewById(R.id.text_state);
        editTitle = (EditText)v.findViewById(R.id.edit_title);
        editNumber= (EditText)v.findViewById(R.id.edit_number);
        autoStyle = (MultiAutoCompleteTextView)v.findViewById(R.id.auto_style);
        editMenu= (EditText)v.findViewById(R.id.edit_menu);
        textLocation = (TextView)v.findViewById(R.id.text_location);

        // // TODO: 2017-07-28 키보드로 잘못 된 입력 예외 처리 하기
        apply.setNumber(MIN_NUMBER);
        editNumber.setText(MIN_NUMBER + "");
        textLocation.setText(R.string.text_no_address);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());

        autoStyle.setAdapter(new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.styles)));
        autoStyle.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setupWheel(View v) {
        wheelAdapterHour = new TextWheelAdapter();
        ArrayList<Integer> hours = new ArrayList<>();
        for(int i=0; i<24; i++) {
            hours.add(i);
        }
        wheelAdapterHour.setData(hours);

        wheelAdapterMinute = new TextWheelAdapter();
        ArrayList<Integer> minutes = new ArrayList<>();
        for(int i=0; i< 60; i+= 10) {
            minutes.add(i);
        }
        wheelAdapterMinute.setData(minutes);

        wheelHour = (WheelPicker)v.findViewById(R.id.wheel_hour);
        wheelMinute = (WheelPicker)v.findViewById(R.id.wheel_minute);
        wheelHour.setAdapter(wheelAdapterHour);
        wheelMinute.setAdapter(wheelAdapterMinute);

        Calendar calendar = Calendar.getInstance();
        wheelHour.setSelectedIndex(calendar.get(Calendar.HOUR_OF_DAY));
        wheelMinute.setSelectedIndex(calendar.get(Calendar.MINUTE) / 10);
    }

    private void setupButton(View v) {
        v.findViewById(R.id.button_up).setOnClickListener(this);
        v.findViewById(R.id.button_down).setOnClickListener(this);
        v.findViewById(R.id.button_search).setOnClickListener(this);
        btnApply = (Button)v.findViewById(R.id.button_apply);
        btnApply.setOnClickListener(this);
    }

    private void loadData() {
        apply = SharedPreperenceHelper.getInstance(getContext()).getApply();
        if(apply == null) {
            apply = new Apply();
            return;
        } else {
            editTitle.setText(apply.getTitle());
            editNumber.setText(apply.getNumber() + "");
            autoStyle.setText(apply.getWantedStyle());
            editMenu.setText(apply.getWantedMenu());
            wheelHour.setSelectedIndex(TimeHelper.getHour(apply.getWantedTime()));
            wheelMinute.setSelectedIndex(TimeHelper.getMinute(apply.getWantedTime()) / 10);
            setState(apply.getState());
        }
        // // TODO: 2017-07-28 저장된 위치 맵에 출력하기
    }

    @Override
    public void onClick(View view) {
        int number = apply.getNumber();
        switch(view.getId()) {
            case R.id.button_up:
                if(number < MAX_NUMBER) {
                    number++;
                    editNumber.setText(Integer.toString(number));
                    apply.setNumber(number);
                }
                break;
            case R.id.button_down:
                if(number > MIN_NUMBER) {
                    number--;
                    editNumber.setText(Integer.toString(number));
                    apply.setNumber(number);
                }
                break;
            case R.id.button_apply:
                if(apply.getState() == Apply.STATE_EDIT) {
                    MyAlertDialog.newInstance(getString(R.string.dialog_alert_title), getString(R.string.dialog_apply_message), this)
                            .show(getChildFragmentManager(), null);
                } else {
                    MyAlertDialog.newInstance(getString(R.string.dialog_alert_title), getString(R.string.dialog_cancel_message), this)
                            .show(getChildFragmentManager(), null);
                }
                break;
            case R.id.button_search:
                MapDetailActivity.show(this
                        , googleMap.getCameraPosition().target.latitude
                        , googleMap.getCameraPosition().target.longitude
                        , false);
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        initGoogleMap();
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, REQUEST_PERMISSION);
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
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
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
        }
    }

    private void setLocation(LatLng latLng) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        marker = googleMap.addMarker(MarkerBuilder.simple(latLng));
        String add = GeocoderHelper.getAddress(getContext(), latLng);
        textLocation.setText(StringHelper.cutStart(add, 18));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PERMISSION) {
            setMyLocation();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MapDetailActivity.REQUEST_LOCATION) {
            if(resultCode == Activity.RESULT_OK) {
                LatLng latLng = new LatLng(
                        data.getDoubleExtra(MapDetailActivity.EXTRA_LATITUDE, 0)
                        , data.getDoubleExtra(MapDetailActivity.EXTRA_LONGITUDE, 0));
                if(marker != null) {
                    marker.remove();
                }
                setLocation(latLng);
            }
        }
    }

    public void requestApply() {
        apply.setTitle(editTitle.getText().toString());
        apply.setNumber(Integer.parseInt(editNumber.getText().toString()));
        int hour = wheelAdapterHour.getItem(wheelHour.getSelectedIndex());
        int minute = wheelAdapterMinute.getItem(wheelMinute.getSelectedIndex());
        apply.setWantedTime(TimeHelper.getTime(hour, minute));
        apply.setWantedStyle(autoStyle.getText().toString());
        apply.setWantedMenu(editMenu.getText().toString());
        apply.setWantedLatitude(marker.getPosition().latitude);
        apply.setWantedLongitude(marker.getPosition().longitude);
        apply.setWritedTime(TimeHelper.now());
        apply.setState(Apply.STATE_APPLY);
        SharedPreperenceHelper.getInstance(getContext()).saveApply(apply);

        setState(Apply.STATE_APPLY);
    }

    private void requestCancel() {
        // // TODO: 2017-07-28 내용 모두 지우기
        apply.setState(Apply.STATE_EDIT);
        setState(Apply.STATE_EDIT);
    }

    private void setState(int state) {
        switch(state) {
            case Apply.STATE_EDIT:
                btnApply.setText(R.string.text_apply);
                imageState.setImageResource(R.drawable.ic_error_orange_24dp);
                textState.setText(R.string.text_need_input);
                textState.setTextColor(ContextCompat.getColor(getContext(), R.color.yellow));
                break;
            case Apply.STATE_APPLY:
                btnApply.setText(R.string.text_cancel);
                imageState.setImageResource(R.drawable.ic_check_circle_green_24dp);
                textState.setText(R.string.text_apply_success);
                textState.setTextColor(ContextCompat.getColor(getContext(), R.color.green));

                // // TODO: 2017-07-28 내용 수정 못하게 하기
                break;
            case Apply.STATE_FAIL:
                break;
        }
    }

    @Override
    public void onPositive() {
        if(apply.getState() == Apply.STATE_EDIT) {
            requestApply();
        } else {
            requestCancel();
        }
        scrollView.smoothScrollTo(0,0);
    }

    @Override
    public void onNegative() {
    }
}
