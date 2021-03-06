package com.project.boostcamp.secondminiproject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
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

        setupDrawer(); // 드로우어 설정
        setupTabLayout(); // 탭 레이아웃 설정
        setupRecyclerView(); // 리사이클러뷰 설정
        setupDatabase(); // 데이터베이스 설정
    }

    private void setupDrawer() {
        // 네비게이션 드로우어 토글 버튼 설정
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
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
    }

    @OnClick(R.id.button_change_view)
    public void OnChangeView(ImageView v) {
        showVertical = !showVertical;
        // 버튼 이미지 변환하기
        v.setImageResource(showVertical
                ? R.drawable.ic_staggered_grid
                : R.drawable.ic_vertical);
        // 리사이클러 숨기기 애니메이션
        hideRecycler(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 레이아웃 매니져 변환하기
                recyclerView.setLayoutManager(showVertical
                        ? new LinearLayoutManager(MainActivity.this)
                        : new StaggeredGridLayoutManager(LIST_COLUMN, StaggeredGridLayoutManager.VERTICAL));
                // 리사이클러 보여주기 애니메이션
                showRecycler();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setupDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("shop");
        ref.addValueEventListener(shopValueEventListener);
    }

    private ValueEventListener shopValueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            ArrayList<Shop> shops = new ArrayList<>();
            for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                shops.add(postSnapshot.getValue(Shop.class));
            }
            recyclerAdapter = new ShopRecyclerAdapter(MainActivity.this, shops);
            recyclerView.setAdapter(recyclerAdapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };

    // 리사이클려뷰 숨겨주기
    private void hideRecycler(AnimatorListenerAdapter adapter) {
        int cx = (recyclerView.getLeft() + recyclerView.getRight()) / 2;
        int cy = (recyclerView.getTop() + recyclerView.getBottom()) / 2;

        int finalRadius = Math.max(recyclerView.getWidth(), recyclerView.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(recyclerView, cx, cy, finalRadius, 0);
        anim.addListener(adapter);
        anim.start();
    }

    // 리사이클러뷰 나타내기
    private void showRecycler() {
        int cx = (recyclerView.getLeft() + recyclerView.getRight()) / 2;
        int cy = (recyclerView.getTop() + recyclerView.getBottom()) / 2;

        int finalRadius = Math.max(recyclerView.getWidth(), recyclerView.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(recyclerView, cx, cy, 0, finalRadius);
        recyclerView.setVisibility(View.VISIBLE);
        anim.start();
    }
}
