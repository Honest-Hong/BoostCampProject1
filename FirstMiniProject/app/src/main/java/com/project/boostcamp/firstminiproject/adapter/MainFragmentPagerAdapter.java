package com.project.boostcamp.firstminiproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.project.boostcamp.firstminiproject.fragment.AlarmFragment;
import com.project.boostcamp.firstminiproject.fragment.MoreFragment;
import com.project.boostcamp.firstminiproject.fragment.FriendFragment;
import com.project.boostcamp.firstminiproject.fragment.TimelineFragment;

/**
 * Created by Hong Tae Joon on 2017-07-03.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    // 메뉴가 총 4개
    private final static int PAGE_COUNT = 4;

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            // 타임라인
            case 0:
                return new TimelineFragment();
            // 친구 요청
            case 1:
                return new FriendFragment();
            // 알림
            case 2:
                return new AlarmFragment();
            // 더보기
            case 3:
                return new MoreFragment();
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
