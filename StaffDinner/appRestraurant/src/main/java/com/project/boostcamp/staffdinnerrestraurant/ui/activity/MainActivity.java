package com.project.boostcamp.staffdinnerrestraurant.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.project.boostcamp.staffdinnerrestraurant.R;
import com.project.boostcamp.staffdinnerrestraurant.ui.adapter.MainViewPagerAdapter;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    public static final String EXTRA_NOTIFICATION_TYPE = "noti_type";
    public static final int NOTIFICATION_TYPE_NONE = 0x00;
    public static final int NOTIFICATION_TYPE_ESTIMATE = 0x01;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        setupTabLayout();
        setupViewPager();
        handleIntent();

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    private void setupTabLayout() {
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_title_application));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_title_estimate));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_title_contact));
    }

    private void setupViewPager() {
        pagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void handleIntent() {
        int type = getIntent().getIntExtra(EXTRA_NOTIFICATION_TYPE, NOTIFICATION_TYPE_NONE);
        switch(type) {
            case NOTIFICATION_TYPE_NONE:
                viewPager.setCurrentItem(0);
                break;
            case NOTIFICATION_TYPE_ESTIMATE:
                viewPager.setCurrentItem(1);
                break;
            default:
                viewPager.setCurrentItem(0);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("HTJ", "onConnectionFailed:" + connectionResult.getErrorMessage());
    }
}
