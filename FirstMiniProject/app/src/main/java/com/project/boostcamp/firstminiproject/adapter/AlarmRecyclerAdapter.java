package com.project.boostcamp.firstminiproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.boostcamp.firstminiproject.data.Alarm;
import com.project.boostcamp.firstminiproject.R;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-04.
 */

public class AlarmRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    // 알림 데이터들
    private ArrayList<Alarm> alarms;

    public AlarmRecyclerAdapter(Context context, @NonNull ArrayList<Alarm> alarms) {
        this.context = context;
        this.alarms = alarms;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_alarm_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder)holder;
        Alarm alarm = alarms.get(position);
        vh.position = position;
        vh.textTitle.setText(alarm.getTitle());
        vh.imageThumb.setImageBitmap(alarm.getThumb());

        // 댓글 알림과 좋아요 알림 구분
        switch(alarm.getType()) {
            case Alarm.TYPE_COMMENT:
                vh.imageIcon.setImageResource(R.drawable.ic_comment_alarm);
                break;
            case Alarm.TYPE_LIKE:
                vh.imageIcon.setImageResource(R.drawable.ic_like_alarm);
                break;
        }
        vh.textTime.setText(alarm.getTime());
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        int position = -1;
        TextView textTitle, textContent, textTime;
        ImageView imageThumb, imageIcon;
        public ViewHolder(View v) {
            super(v);
            textTitle = (TextView)v.findViewById(R.id.text_title);
            textContent = (TextView)v.findViewById(R.id.text_content);
            textTime = (TextView)v.findViewById(R.id.text_time);
            imageThumb = (ImageView)v.findViewById(R.id.image_thumb);
            imageIcon = (ImageView)v.findViewById(R.id.image_icon);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, alarms.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}
