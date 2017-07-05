package com.project.boostcamp.firstminiproject.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.boostcamp.firstminiproject.data.Alarm;
import com.project.boostcamp.firstminiproject.adapter.AlarmRecyclerAdapter;
import com.project.boostcamp.firstminiproject.R;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-03.
 */

public class AlarmFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View cv = inflater.inflate(R.layout.fragment_alarm, container, false);

        RecyclerView recyclerView = (RecyclerView)cv.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AlarmRecyclerAdapter(getContext(), getAlarms()));
        return cv;
    }

    // 테스트 알림 데이터를 만드는 메소드
    private ArrayList<Alarm> getAlarms() {
        ArrayList<Alarm> alarms = new ArrayList<>();
        {
            Alarm a = new Alarm();
            a.setTitle("전홍재님이 회원님의 게시글에 댓글을 남겼습니다.");
            a.setThumb(BitmapFactory.decodeResource(getResources(), R.drawable.thumb_jeon));
            a.setTime("토 오후 3:45");
            a.setType(Alarm.TYPE_COMMENT);
            alarms.add(a);
        }
        {
            Alarm a = new Alarm();
            a.setThumb(BitmapFactory.decodeResource(getResources(), R.drawable.thumb_choi));
            a.setTitle("최민규님이 회원님의 댓글을 좋아합니다: \"땡큐 합니당~ ㅋㅋㅋ 방학 잘 보내시길!\"");
            a.setTime("토 오전 12:46");
            a.setType(Alarm.TYPE_LIKE);
            alarms.add(a);
        }
        {
            Alarm a = new Alarm();
            a.setThumb(BitmapFactory.decodeResource(getResources(), R.drawable.thumb_choi));
            a.setTitle("최민규님이 회원님의 게시글에 댓글을 남겼습니다.");
            a.setTime("금 오후 7:21");
            a.setType(Alarm.TYPE_COMMENT);
            alarms.add(a);
        }
        {
            Alarm a = new Alarm();
            a.setTitle("전홍재님이 회원님의 게시글을 좋아합니다: \"고기가 정말 먹고싶은 날이야!\"");
            a.setThumb(BitmapFactory.decodeResource(getResources(), R.drawable.thumb_jeon));
            a.setTime("금 오후 4:11");
            a.setType(Alarm.TYPE_LIKE);
            alarms.add(a);
        }
        return alarms;
    }
}
