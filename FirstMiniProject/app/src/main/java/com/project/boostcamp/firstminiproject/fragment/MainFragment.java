package com.project.boostcamp.firstminiproject.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.boostcamp.firstminiproject.adapter.MainFragmentPagerAdapter;
import com.project.boostcamp.firstminiproject.R;

/**
 * Created by Hong Tae Joon on 2017-07-04.
 */

public class MainFragment extends Fragment {
    // 뷰 페이저
    private ViewPager viewPager;
    // 뷰 페이저의 탭
    private TabLayout tabLayout;
    // 탭을 옮기기 전의 탭 번호
    private int oldPosition = 0;
    // 선택한 탭 이미지와 선택하지 않은 탭 이미지
    private Drawable[] selectDrawables, defaultDrawables;
    // 친구 추가 FAB 버튼
    private FloatingActionButton fabAdd;

    // 친구 요청 탭으로 이동하였을 때 보여주기 위해 FAB을 설정하는 과정이 필요함
    public void setFabAdd(FloatingActionButton fabAdd) {
        this.fabAdd = fabAdd;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        initDrawables();
        initTabLayout(v);

        viewPager = (ViewPager)v.findViewById(R.id.view_pager);
        viewPager.setAdapter(new MainFragmentPagerAdapter(getChildFragmentManager()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setSelectedTabIcon(oldPosition);
    }

    // 배열에 그림 초기화
    private void initDrawables() {
        // 선택한 탭의 이미지
        selectDrawables = new Drawable[4];
        selectDrawables[0] = ContextCompat.getDrawable(getContext(), R.drawable.ic_timeline_blue);
        selectDrawables[1] = ContextCompat.getDrawable(getContext(), R.drawable.ic_people_blue);
        selectDrawables[2] = ContextCompat.getDrawable(getContext(), R.drawable.ic_alarm_blue);
        selectDrawables[3] = ContextCompat.getDrawable(getContext(), R.drawable.ic_3bar_blue);

        // 선택하지 않은 탭의 이미지
        defaultDrawables = new Drawable[4];
        defaultDrawables[0] = ContextCompat.getDrawable(getContext(), R.drawable.ic_timeline_grey);
        defaultDrawables[1] = ContextCompat.getDrawable(getContext(), R.drawable.ic_people_grey);
        defaultDrawables[2] = ContextCompat.getDrawable(getContext(), R.drawable.ic_alarm_grey);
        defaultDrawables[3] = ContextCompat.getDrawable(getContext(), R.drawable.ic_3bar_grey);
    }

    // 탭 레이아웃 초기화
    private void initTabLayout(View v) {
        tabLayout = (TabLayout)v.findViewById(R.id.tab_layout);
        // 탭 4개 추가
        for(int i=0; i<4; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setIcon(defaultDrawables[i]);
            tabLayout.addTab(tab);
        }
        // 탭 선택 이벤트 리스너 추가
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 탭을 선택하였을 때 이미지 색상을 바꿔주기
                setSelectedTabIcon(tab.getPosition());
                if(fabAdd == null) {
                    Log.e("HTJ", "Fab add is null");
                    return;
                }
                if(tab.getPosition() == 1)
                    fabAdd.show();
                else
                    fabAdd.hide();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    // 탭을 선택하였을 때의 이미지 변화
    private void setSelectedTabIcon(int selected) {
        tabLayout.getTabAt(oldPosition).setIcon(defaultDrawables[oldPosition]);
        tabLayout.getTabAt(selected).setIcon(selectDrawables[selected]);
        viewPager.setCurrentItem(selected);
        oldPosition = selected;
    }
}
