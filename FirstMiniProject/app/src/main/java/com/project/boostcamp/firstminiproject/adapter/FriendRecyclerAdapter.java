package com.project.boostcamp.firstminiproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.project.boostcamp.firstminiproject.R;
import com.project.boostcamp.firstminiproject.data.Friend;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-04.
 */

public class FriendRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    // 친구 요청 데이터들
    private ArrayList<Friend> friends;

    public FriendRecyclerAdapter(Context context, ArrayList<Friend> friends) {
        this.context = context;
        this.friends = friends;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_friend_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder)holder;
        Friend friend = friends.get(position);
        vh.position = position;
        vh.textName.setText(friend.getName());
        vh.textSame.setText(String.format("함께 아는 친구 %d명", friend.getSame()));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // 데이터의 위치를 저장하는 변수
        int position = -1;

        TextView textName, textSame;

        public ViewHolder(View v) {
            super(v);
            textName = (TextView)v.findViewById(R.id.text_name);
            textSame = (TextView)v.findViewById(R.id.text_same);
            // 수락과 삭제 버튼
            v.findViewById(R.id.button_accept).setOnClickListener(this);
            v.findViewById(R.id.button_delete).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button_accept:
                    Toast.makeText(context, friends.get(position).getName() + "님의 신청을 수락하였습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_delete:
                    Toast.makeText(context, friends.get(position).getName() + "님의 신청을 삭제하였습니다.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
