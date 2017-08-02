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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.project.boostcamp.publiclibrary.api.RetrofitClient;
import com.project.boostcamp.publiclibrary.data.Geo;
import com.project.boostcamp.staffdinner.R;
import com.project.boostcamp.publiclibrary.data.Apply;
import com.project.boostcamp.staffdinner.ui.activity.MapDetailActivity;
import com.project.boostcamp.staffdinner.ui.adapter.TextWheelAdapter;
import com.project.boostcamp.publiclibrary.dialog.DialogResultListener;
import com.project.boostcamp.publiclibrary.dialog.MyAlertDialog;
import com.project.boostcamp.publiclibrary.wheelpicker.WheelPicker;
import com.project.boostcamp.publiclibrary.util.TimeHelper;
import com.project.boostcamp.publiclibrary.util.GeocoderHelper;
import com.project.boostcamp.publiclibrary.util.MarkerBuilder;
import com.project.boostcamp.publiclibrary.util.SharedPreperenceHelper;
import com.project.boostcamp.publiclibrary.util.StringHelper;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class ApplicationFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback, OnSuccessListener<Location>, DialogResultListener {
    private static final int REQUEST_PERMISSION = 0x100;
    public static final int MAX_NUMBER = 99;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_HOUR = 24;
    public static final int MAX_MINUTE = 60;
    public static final int MAX_DATE = 14;
    @BindView(R.id.scroll_view) NestedScrollView scrollView;
    @BindView(R.id.image_state) ImageView imageState; // 상단의 신청서 상태 이미지
    @BindView(R.id.text_state) TextView textState; // 상단의 신청서 상태 텍스트
    @BindView(R.id.edit_title) EditText editTitle; // 신청서의 제목 입력창
    @BindView(R.id.edit_number) EditText editNumber; // 신청서의 인원 입력창
    @BindView(R.id.auto_style) MultiAutoCompleteTextView autoStyle; // 신청서의 분위기 입력창
    @BindView(R.id.edit_menu) EditText editMenu; // 신청서의 메뉴 입력창
    @BindView(R.id.text_location) TextView textLocation; // 신청서의 위치 텍스트
    @BindView(R.id.button_apply) Button btnApply; // 신청 버튼
    @BindView(R.id.button_up) ImageButton btnUp;
    @BindView(R.id.button_down) ImageButton btnDown;
    @BindView(R.id.button_search) ImageButton btnSearch;
    @BindView(R.id.wheel_hour) WheelPicker wheelHour; // 선청서의 시간 선택 도구
    @BindView(R.id.wheel_minute) WheelPicker wheelMinute; // 신청서의 분 선택 도구
    @BindView(R.id.wheel_date) WheelPicker wheelDate;
    private TextWheelAdapter wheelAdapterHour; //  신청서의 시간 뷰 어댑터
    private TextWheelAdapter wheelAdapterMinute; // 신청서의 분 뷰 어댑터
    private TextWheelAdapter wheelAdapterDate;
    private GoogleMap googleMap; // 신청서의 위치 지도
    private Marker marker; // 신청서의 위치 지도 마커
    private FusedLocationProviderClient fusedLocationClient; // 현재 위치를 가져오는 서비스
    private Apply apply = new Apply(); // 현재 신청서 정보
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
        ButterKnife.bind(this, v);
        setupView(v);
        setupWheel(v);
        loadApplyData();
        return v;
    }

    private void setupView(View v) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());

        autoStyle.setAdapter(new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.styles)));
        autoStyle.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // TODO: 2017-07-28 키보드로 잘못 된 입력 예외 처리 하기
        // TODO: 2017-07-31 분위기를 선택하면서 해시태깅을 하도록 추가!
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setScrollGesturesEnabled(false);
        uiSettings.setZoomGesturesEnabled(false);
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, REQUEST_PERMISSION);
        } else {
            setMyLocation();
        }
    }

    private void setupWheel(View v) {
        wheelAdapterHour = new TextWheelAdapter();
        ArrayList<String> hours = new ArrayList<>();
        for(int i = 0; i< MAX_HOUR; i++) {
            hours.add(String.format("%02d", i));
        }
        wheelAdapterHour.setData(hours);
        wheelHour.setAdapter(wheelAdapterHour);

        wheelAdapterMinute = new TextWheelAdapter();
        ArrayList<String> minutes = new ArrayList<>();
        for(int i = 0; i< MAX_MINUTE; i+= 10) {
            minutes.add(String.format("%02d", i));
        }
        wheelAdapterMinute.setData(minutes);
        wheelMinute.setAdapter(wheelAdapterMinute);

        wheelAdapterDate = new TextWheelAdapter();
        ArrayList<String> dates = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        for(int i = 0; i< MAX_DATE; i++) {
            dates.add(String.format("%02d/%02d", calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE)));
            calendar.add(Calendar.DATE, 1);
        }
        wheelAdapterDate.setData(dates);
        wheelDate.setAdapter(wheelAdapterDate);
    }

    /**
     * 신청서의 데이터를 불러옵니다.
     * 서버에서 데이터를 불러올 수 있다면 서버의 데이터를 사용하고
     * 불러올 수 없다면 로컬 데이터를 사용합니다.
     */
    private void loadApplyData() {
        String id = SharedPreperenceHelper.getInstance(getContext()).getClient().getId();
        RetrofitClient.getInstance().clientService.getApplication(id).enqueue(new Callback<Apply>() {
            @Override
            public void onResponse(Call<Apply> call, Response<Apply> response) {
                Log.d("HTJ", "ApplicationFragment-loadApplyData-onReponse: " + response.body());
                Log.d("HTJ", "apply id: " + apply.getId());
                apply = response.body();
                setupTexts(apply);
            }

            @Override
            public void onFailure(Call<Apply> call, Throwable t) {
                Log.e("HTJ", "ApplicattionFragment-loadApplyData-onFailuer: " + t.getMessage());
                apply = SharedPreperenceHelper.getInstance(getContext()).getApply();
                if(apply == null) {
                    apply = new Apply();
                }
                setupTexts(apply);
            }
        });
    }

    /**
     * 신청서 데이터를 뷰에 뿌려주는 함수
     * @param apply 신청서 데이터
     */
    private void setupTexts(Apply apply) {
        if(apply.getNumber() == 0) {
            apply.setNumber(1);
        }
        if(apply.getWantedTime() == 0) {
            apply.setWantedTime(TimeHelper.now());
        }

        editTitle.setText(apply.getTitle());
        editNumber.setText(apply.getNumber() + "");
        autoStyle.setText(apply.getWantedStyle());
        editMenu.setText(apply.getWantedMenu());
        wheelHour.setSelectedIndex(TimeHelper.getHour(apply.getWantedTime()));
        wheelMinute.setSelectedIndex(TimeHelper.getMinute(apply.getWantedTime()) / 10);

        setState(apply.getState());
        // TODO: 2017-07-28 저장된 위치 맵에 출력하기
    }

    @OnClick({R.id.button_up, R.id.button_down, R.id.button_apply, R.id.button_search})
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
                handleApplyButton();
                break;
            case R.id.button_search:
                MapDetailActivity.show(this
                        , googleMap.getCameraPosition().target.latitude
                        , googleMap.getCameraPosition().target.longitude
                        , false);
                break;
        }
    }

    private void handleApplyButton() {
        if(apply.getState() == Apply.STATE_EDIT) {
            MyAlertDialog.newInstance(getString(R.string.dialog_alert_title), getString(R.string.dialog_apply_message), this)
                    .show(getChildFragmentManager(), null);
        } else if(apply.getState() == Apply.STATE_APPLY) {
            MyAlertDialog.newInstance(getString(R.string.dialog_alert_title), getString(R.string.dialog_cancel_message), this)
                    .show(getChildFragmentManager(), null);
        } else {
            apply = new Apply();
            setupTexts(apply);
            setState(Apply.STATE_EDIT);
        }
    }

    @Override
    public void onPositive() {
        if(apply.getState() == Apply.STATE_EDIT) {
            sendApply();
        } else {
            cancelApply();
        }
        scrollView.smoothScrollTo(0,0);
    }

    @Override
    public void onNegative() {
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
        } else {
            textLocation.setText(R.string.text_no_address);
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

    public void sendApply() {
        // 데이터 최신화 작업
        apply.setTitle(editTitle.getText().toString());
        apply.setNumber(Integer.parseInt(editNumber.getText().toString()));
        String hour = wheelAdapterHour.getItem(wheelHour.getSelectedIndex());
        String minute = wheelAdapterMinute.getItem(wheelMinute.getSelectedIndex());
        apply.setWantedTime(TimeHelper.getTime(
                Integer.parseInt(hour)
                , Integer.parseInt(minute)));
        apply.setWantedStyle(autoStyle.getText().toString());
        apply.setWantedMenu(editMenu.getText().toString());
        apply.setGeo(new Geo("Point",
                marker.getPosition().longitude,
                marker.getPosition().latitude));
        apply.setWritedTime(TimeHelper.now());
        apply.setState(Apply.STATE_APPLY);
        // 로컬에 저장
        SharedPreperenceHelper.getInstance(getContext()).saveApply(apply);
        // 서버에 저장
        String clientId = SharedPreperenceHelper.getInstance(getContext()).getClient().getId();
        RetrofitClient.getInstance().clientService.setApplication(clientId, apply).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("HTJ", "ApplicationFragment-sendApply-onResponse: " + response.body());
                String id = response.body();
                apply.setId(id);
                SharedPreperenceHelper.getInstance(getContext()).saveApply(apply);
                setState(Apply.STATE_APPLY);;
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("HTJ", "ApplicationFragment-sendApply-onFailure: " + t.getMessage());
            }
        });
    }

    private void cancelApply() {
        Log.d("HTJ", "cancelApply: " + apply.getId());
        RetrofitClient.getInstance().clientService.cancelApplication(apply.getId()).enqueue(new Callback<Apply>() {
            @Override
            public void onResponse(Call<Apply> call, Response<Apply> response) {
                // 취소 성공
                Log.d("HTJ", "ApplicationFragment-cancelApply-onResponse: " + response.body());
                // TODO: 2017-07-28 내용 모두 지우기
                apply = new Apply();
                setupTexts(apply);
                apply.setState(Apply.STATE_EDIT);
                setState(Apply.STATE_EDIT);
                SharedPreperenceHelper.getInstance(getContext()).saveApply(apply);
            }

            @Override
            public void onFailure(Call<Apply> call, Throwable t) {
                // 취소 실패
                Log.e("HTJ", "ApplicationFragment-cancelApply-onFailure: " + t.getMessage());
                Toast.makeText(getContext(), "서버 오류", Toast.LENGTH_SHORT).show();
                apply = new Apply();
                setupTexts(apply);
                apply.setState(Apply.STATE_EDIT);
                setState(Apply.STATE_EDIT);
                SharedPreperenceHelper.getInstance(getContext()).saveApply(apply);
            }
        });
    }

    private void setState(int state) {
        switch(state) {
            case Apply.STATE_EDIT:
                btnApply.setText(R.string.text_apply);
                imageState.setImageResource(R.drawable.ic_error_orange_24dp);
                textState.setText(R.string.text_need_input);
                textState.setTextColor(ContextCompat.getColor(getContext(), R.color.yellow));
                blockView(false);
                break;
            case Apply.STATE_APPLY:
                btnApply.setText(R.string.text_cancel);
                imageState.setImageResource(R.drawable.ic_check_circle_green_24dp);
                textState.setText(R.string.text_apply_success);
                textState.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                blockView(true);
                break;
            case Apply.STATE_FAIL:
                btnApply.setText(R.string.text_rewrite);
                imageState.setImageResource(R.drawable.ic_cancel_red_24dp);
                textState.setText(R.string.text_apply_canceled);
                textState.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
                blockView(true);
                break;
        }
    }

    /**
     * 입력 폼을 사용 불가능하게 하는 함수
     * @param block
     */
    private void blockView(boolean block) {
        editTitle.setEnabled(!block);
        editNumber.setEnabled(!block);
        wheelHour.setEnableScroll(!block);
        wheelMinute.setEnableScroll(!block);
        wheelDate.setEnableScroll(!block);
        autoStyle.setEnabled(!block);
        editMenu.setEnabled(!block);
        editMenu.setEnabled(!block);
        btnUp.setEnabled(!block);
        btnDown.setEnabled(!block);
        btnSearch.setEnabled(!block);
    }
}
