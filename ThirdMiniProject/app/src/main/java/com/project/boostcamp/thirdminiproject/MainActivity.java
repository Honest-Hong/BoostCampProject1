package com.project.boostcamp.thirdminiproject;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.boostcamp.thirdminiproject.fragment.AddFragment;
import com.project.boostcamp.thirdminiproject.fragment.ListFragment;
import com.project.boostcamp.thirdminiproject.fragment.MapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements onNextClickListener, GoogleApiClient.OnConnectionFailedListener, FragmentManager.OnBackStackChangedListener {
    @BindView(R.id.fab_add) FloatingActionButton fabAdd; // 맛집 추가 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initGoogleApi();
        initToolbar();
        initFragment();
    }

    private void initGoogleApi() {
        GoogleApiClient googleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
        googleApiClient.connect();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        TextView textTitle = (TextView)toolbar.findViewById(R.id.text_title);
        textTitle.setText(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, ListFragment.newInstance());
        transaction.commit();
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    // 다음 버튼 클릭 이벤트
    @Override
    public void onNext(Fragment fragment, Restraurant rest) {
        if(fragment instanceof AddFragment) {
            // 지도 화면 보여주기
            showMapFragment(rest);
        } else if(fragment instanceof MapFragment) {
            // 데이터베이스에 추가하기
            addRestraurant(rest);
            // 다시 처음화면으로 돌아가기
            getSupportFragmentManager().popBackStack();
            onBackPressed();
        }
    }

    // 메인 액티비티 버튼 클릭 이벤트
    // <- 화살표 버튼과 맛집 추가 버튼
    @OnClick({R.id.button_back, R.id.fab_add})
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_back:
                // 뒤로 가기
                onBackPressed();
                break;
            case R.id.fab_add:
                // 맛집 추가 프래그먼트 보여주기
                showAddFragment();
                break;
        }
    }

    private void showAddFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, AddFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void showMapFragment(Restraurant rest) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, MapFragment.newInstance(rest));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // 데이터베이스에 새로운 맛집 정보 추가하기
    private void addRestraurant(Restraurant rest) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("restraurant");
        ref = ref.push();
        ref.setValue(rest);
    }

    // 백스택 변환 상태 감지
    @Override
    public void onBackStackChanged() {
        // 프래그먼트를 replace할 때 사라지는 프래그먼트와 보여지는 프래그먼트 모두 불려진다.
        // 따라서 hide > show, show > hide 가 연속적으로 일어나는 경우가 생김
        // 이 문제는 어떻게 해결할지...
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        if(fragment instanceof ListFragment) {
            // 목록 프래그먼트는 맛집 추가 버튼을 보여준다
            fabAdd.show();
        } else {
            fabAdd.hide();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
