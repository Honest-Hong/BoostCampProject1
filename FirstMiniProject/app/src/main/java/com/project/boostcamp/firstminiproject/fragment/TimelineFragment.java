package com.project.boostcamp.firstminiproject.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.boostcamp.firstminiproject.R;
import com.project.boostcamp.firstminiproject.data.Timeline;
import com.project.boostcamp.firstminiproject.adapter.TimelineRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-03.
 */

public class TimelineFragment extends Fragment{
    private RecyclerView recyclerView;
    // 리사이클러뷰 상단에서 스와이프를 하면 새로고침 애니메이션이 시작하도록 해주는 레이아웃
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View cv = inflater.inflate(R.layout.fragment_timeline, container, false);
        recyclerView = (RecyclerView)cv.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new TimelineRecyclerAdapter(getContext(), getTimelines()));

        swipeRefreshLayout = (SwipeRefreshLayout)cv.findViewById(R.id.swipe_refresh_layout);
        // 애니메이션의 색상을 지정함
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        // 새로고침이 시작되었을 때의 이벤트리스너
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 임시로 1.5초 후에 사라지도록 한다
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(), "새로고침 되었습니다", Toast.LENGTH_SHORT).show();
                    }
                }, 1500);
            }
        });
        return cv;
    }

    // 더미 데이터 받아오는 메소드
    private ArrayList<Timeline> getTimelines() {
        ArrayList<Timeline> timelines = new ArrayList<>();
        {
            Timeline t = new Timeline();
            t.setWriter("인천대학교 대나무숲");
            t.setTime("4시간 전");
            t.setThumb(BitmapFactory.decodeResource(getResources(), R.drawable.thumb_incheon));
            t.setContent("#171900번째 아우성\n" +
                    "동거에 대해서 어떻게 생각하는지 궁금합니다\n" +
                    "(제가 동거를 하고 있는 것도 아니며 할 예정이라 묻는 것도 아니에요..!)\n" +
                    "동거에 찬성반대하시나요? \n" +
                    "혹여 찬성한다면 나중에 현재A라는 남.여자친구와 헤어지고 다른사람이랑 결혼하더라도 사실대로 말할 수 있나요? \n" +
                    "또는 자신의 남.여자친구가 전에 동거를 했었다고 하더라도 아무렇지 않은가요? \n" +
                    "단순호기심에 물어봅니다!");
            t.setTimelineType(Timeline.TIMELINE_TYPE_TEXT);
            t.setLike(3);
            t.setLiked(false);
            t.setComment(2);
            t.setShare(0);
            t.setView(0);
            timelines.add(t);
        }
        {
            Timeline t = new Timeline();
            t.setWriter("인천대학교 대나무숲");
            t.setTime("7월 3일 오전2:02");
            t.setThumb(BitmapFactory.decodeResource(getResources(), R.drawable.thumb_incheon));
            t.setContent("2017. 07. 03 오전\n" +
                    "심심해! 나는 좋아하는 사람한테 정말정말 많이 연락하고 싶고" +
                    "그걸 잘 못 참는 편인데 오빠한테 너무너무 전화 하고싶어. 목소리" +
                    "듣고싶고 귀엽다는 말 듣고싶어. 하지만 오빠는 날 좋아하는 것" +
                    "같으면서도 무슨 일이 있지 않는 이상 연락은 먼저 절대 하지 않지. 힘들어...");
            t.setTimelineType(Timeline.TIMELINE_TYPE_TEXT);
            t.setLike(6);
            t.setLiked(false);
            t.setComment(12);
            t.setShare(0);
            t.setView(0);
            timelines.add(t);
        }
        {
            Timeline t = new Timeline();
            t.setWriter("비트윈");
            t.setTime("7월 3일 오후9:31");
            t.setThumb(BitmapFactory.decodeResource(getResources(), R.drawable.thumb_bitwin));
            t.setContent("\uD83D\uDD75여러분의 궁금증을 풀어주기 위한, 퀴~즈!\uD83D\uDD75\n" +
                    "\uD83D\uDC49 지난주, 메리밀크의 먹방 - 그 주인공은?\n" +
                    "슬프지만,오늘로써 이거모찌가 막을 내리게 되었어요ㅠ.ㅠ\n" +
                    "조만간 다른 코너로 다시 찾아올게요! \n" +
                    "재밌는 아이디어가 있으시다면 언제든지 제보해주세요 \uD83D\uDC95");
            t.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.timeline_example_1));
            t.setTimelineType(Timeline.TIMELINE_TYPE_TEXT_AND_IMAGE);
            t.setLike(71);
            t.setLiked(true);
            t.setComment(55);
            t.setShare(4);
            t.setView(0);
            timelines.add(t);
        }
        {
            Timeline t = new Timeline();
            t.setWriter("유재석 Yoo Jae-Suk Fanpage");
            t.setTime("1월 2일");
            t.setThumb(BitmapFactory.decodeResource(getResources(), R.drawable.thumb_yoo));
            t.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.timeline_example_2));
            t.setTimelineType(Timeline.TIMELINE_TYPE_IMAGE);
            t.setLike(1975);
            t.setLiked(false);
            t.setComment(17);
            t.setShare(22);
            t.setView(10);
            timelines.add(t);
        }
        {
            Timeline t = new Timeline();
            t.setWriter("패스트캠퍼스 - Programming");
            t.setTime("Sponsored");
            t.setThumb(BitmapFactory.decodeResource(getResources(), R.drawable.thumb_fast));
            t.setContent("어제 6/13(화) 저녁 7시 30분에 [안드로이드 앱 개발 프로젝트 CAMP] 5기가 개강했습니다!\n" +
                    "앱 구현부터 Google Firebase 연동 및 AdMob 연동으로 광고를 붙여 앱 출시까지 경험해 볼 8주 강의의 시작 현장을 담아 보았습니다. ♪\n" +
                    "▶ 안드로이드 앱 개발 프로젝트 캠프 자세히보기 : http://www.fastcampus.co.kr/dev_camp_adp/");
            t.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.timeline_example_3));
            t.setTimelineType(Timeline.TIMELINE_TYPE_TEXT_AND_IMAGE);
            t.setLike(2);
            t.setLiked(false);
            t.setComment(1);
            t.setShare(3);
            t.setView(1);
            timelines.add(t);
        }
        {
            Timeline t = new Timeline();
            t.setWriter("인천대학교 대나무숲");
            t.setTime("7월 1일 오후2:33");
            t.setThumb(BitmapFactory.decodeResource(getResources(), R.drawable.thumb_incheon));
            t.setContent("#171801번째 아우성\n" +
                    "어쩌다가 페이스북에서 보게 되었는데\n" +
                    "ㅊㅇㅎㅂ? ㅇㅎㅈ님 여자친구있으신가요...!!?");
            t.setTimelineType(Timeline.TIMELINE_TYPE_TEXT);
            t.setLike(5);
            t.setLiked(false);
            t.setComment(9);
            t.setShare(0);
            t.setView(0);
            timelines.add(t);
        }
        return timelines;
    }
}
