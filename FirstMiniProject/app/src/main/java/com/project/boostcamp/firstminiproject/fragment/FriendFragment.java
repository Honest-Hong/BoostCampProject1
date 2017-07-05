package com.project.boostcamp.firstminiproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.boostcamp.firstminiproject.R;
import com.project.boostcamp.firstminiproject.adapter.FriendRecyclerAdapter;
import com.project.boostcamp.firstminiproject.data.Friend;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-03.
 */

public class FriendFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View cv = inflater.inflate(R.layout.fragment_friend, container, false);

        RecyclerView recyclerView = (RecyclerView)cv.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new FriendRecyclerAdapter(getContext(), getFriends()));
        return cv;
    }

    // 테스트 친구 요청 데이터를 받아오는 메소드
    private ArrayList<Friend> getFriends() {
        ArrayList<Friend> friends = new ArrayList<>();
        {
            Friend f = new Friend();
            f.setName("SH Gwon");
            f.setSame(7);
            friends.add(f);
        }
        {
            Friend f = new Friend();
            f.setName("김병준");
            f.setSame(24);
            friends.add(f);
        }
        {
            Friend f = new Friend();
            f.setName("석민호");
            f.setSame(18);
            friends.add(f);
        }
        {
            Friend f = new Friend();
            f.setName("원지연");
            f.setSame(22);
            friends.add(f);
        }
        {
            Friend f = new Friend();
            f.setName("정세윤");
            f.setSame(12);
            friends.add(f);
        }
        return friends;
    }
}
