package com.project.boostcamp.secondminiproject;

import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity{
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout; // 네비게이션 드로우어
    @BindView(R.id.tool_bar) Toolbar toolbar; // 툴바
    @BindView(R.id.tab_layout) TabLayout tabLayout; // 탭 레이아웃
    @BindView(R.id.button_change_view) ImageView btnChangeView; // 보기 방식 바꾸기 버튼
    @BindView(R.id.recycler_view) RecyclerView recyclerView; // 리사이클러뷰
    private ShopRecyclerAdapter recyclerAdapter; // 리사이클러뷰 어댑터
    private ActionBarDrawerToggle drawerToggle; // 드로우어 토글 버튼

    private boolean showVertical = false;

    public final static int LIST_COLUMN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        setupDrawer();
        setupTabLayout();
        setupRecyclerView();
    }

    private void setupDrawer() {
        // 네비게이션 드로우어 토글 버튼 설정
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_distance));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_like));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_time));
        // 탭에 따라서 데이터 순서 변화
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0: // 거리순
                        recyclerAdapter.changeSorting(ShopRecyclerAdapter.SORTING_DISTANCE);
                        break;
                    case 1: // 인기순
                        recyclerAdapter.changeSorting(ShopRecyclerAdapter.SORTING_LIKE);
                        break;
                    case 2: // 시간순
                        recyclerAdapter.changeSorting(ShopRecyclerAdapter.SORTING_TIME);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(LIST_COLUMN, StaggeredGridLayoutManager.VERTICAL));
        recyclerAdapter = new ShopRecyclerAdapter(this, ShopTestData.get());
        recyclerView.setAdapter(recyclerAdapter);
    }

    @OnClick(R.id.button_change_view)
    public void OnChangeView(ImageView v) {
        showVertical = !showVertical;
        v.setImageResource(showVertical
                ? R.drawable.ic_staggered_grid
                : R.drawable.ic_vertical);
        recyclerView.setLayoutManager(showVertical
                ? new LinearLayoutManager(this)
                : new StaggeredGridLayoutManager(LIST_COLUMN, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
