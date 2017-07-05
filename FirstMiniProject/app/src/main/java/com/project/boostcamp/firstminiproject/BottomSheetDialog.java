package com.project.boostcamp.firstminiproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hong Tae Joon on 2017-07-05.
 * 타임라인의 더보기 버튼을 눌렀을 때 하단에서 올라오는 다이얼로그
 */

public class BottomSheetDialog extends Dialog implements AdapterView.OnItemClickListener{
    public BottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bottom_sheet);

        // 다이얼로그를 하단으로 옮기고 크기를 조정한다
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;

        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(new ListAdapter());
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(position) {
            case 0: Toast.makeText(getContext(), "게시물을 숨깁니다", Toast.LENGTH_SHORT).show(); break;
            case 1: Toast.makeText(getContext(), "팔로우를 취소합니다", Toast.LENGTH_SHORT).show(); break;
            case 2: Toast.makeText(getContext(), "게시물을 신고합니다", Toast.LENGTH_SHORT).show(); break;
            case 3: Toast.makeText(getContext(), "알림을 켰습니다", Toast.LENGTH_SHORT).show(); break;
        }
        dismiss();
    }

    public class ListAdapter extends BaseAdapter {
        private String[] titles = { "게시물 숨기기", "팔로우 취소", "게시물 신고", "이 게시물에 대한 알림 켜기"};
        private String[] descriptions = { "이 게시물 유형 적게 표시", "친구 사이를 유지하고 게시물 그만 보기", "", ""};

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View cv, ViewGroup parent) {
            cv = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_bottom_sheet_item, parent, false);
            ImageView imageView = (ImageView)cv.findViewById(R.id.image_view);
            switch(position) {
                case 0: imageView.setImageResource(R.drawable.ic_hide_timeline); break;
                case 1: imageView.setImageResource(R.drawable.ic_cancel_follow); break;
                case 2: imageView.setImageResource(R.drawable.ic_report); break;
                case 3: imageView.setImageResource(R.drawable.ic_earth_grey); break;
            }
            TextView textTitle = (TextView)cv.findViewById(R.id.text_title);
            textTitle.setText(titles[position]);
            TextView textDescription = (TextView)cv.findViewById(R.id.text_description);
            if(descriptions[position].equals(""))
                textDescription.setVisibility(View.GONE);
            else {
                textDescription.setVisibility(View.VISIBLE);
                textDescription.setText(descriptions[position]);
            }
            return cv;
        }
    }
}
