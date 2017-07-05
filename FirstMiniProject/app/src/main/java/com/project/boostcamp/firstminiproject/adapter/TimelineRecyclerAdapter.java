package com.project.boostcamp.firstminiproject.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.boostcamp.firstminiproject.BottomSheetDialog;
import com.project.boostcamp.firstminiproject.R;
import com.project.boostcamp.firstminiproject.data.Timeline;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-03.
 */

public class TimelineRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    // 타임라인 데이터들
    private ArrayList<Timeline> timelines;

    public TimelineRecyclerAdapter(@NonNull Context context, @NonNull ArrayList<Timeline> timelines) {
        this.context = context;
        this.timelines = timelines;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 뷰 타입에 따른 레이아웃
        switch(viewType) {
            // 가장 상단 (카메라와 내 스토리)
            case 0:
                return new TopViewHolder1(LayoutInflater.from(context).inflate(R.layout.view_timeline_top1, parent, false));
            // 글쓰기
            case 1:
                return new TopViewHolder2(LayoutInflater.from(context).inflate(R.layout.view_timeline_top2, parent, false));
            // 일반 타임라인
            default:
                return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_timeline_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 0번째와 1번째는 고정 레이아웃이고 2번째부터는 실제 타임라인들이다.
        if(position > 1) {
            ViewHolder vh = (ViewHolder) holder;
            setNormalView(vh, position - 2);
        }
    }

    private void setNormalView(ViewHolder vh, int position) {
        vh.position = position;
        Timeline timeline = timelines.get(position);

        // 기본 텍스트와 썸네일 이미지 표시
        vh.textWriter.setText(timeline.getWriter());
        vh.imageThumb.setImageBitmap(timeline.getThumb());
        vh.textTime.setText(timeline.getTime());
        vh.textLike.setText(String.format("%d명", timeline.getLike()));

        // 각각 숫자가 0이 아닐 경우에만 표시해주기
        if (timeline.getComment() != 0)
            vh.textComment.setText(String.format("댓글 %d개", timeline.getComment()));
        if (timeline.getShare() != 0)
            vh.textShare.setText(String.format("공유 %d회", timeline.getShare()));
        if (timeline.getView() != 0)
            vh.textView.setText(String.format("조회 %d회", timeline.getView()));

        // 글자만, 이미지만, 글자와 이미지 각각 타입에 맞게 표시해주기
        switch (timeline.getTimelineType()) {
            case Timeline.TIMELINE_TYPE_TEXT:
                vh.textContent.setVisibility(View.VISIBLE);
                vh.textContent.setText(timeline.getContent());
                vh.imageContent.setVisibility(View.GONE);
                break;
            case Timeline.TIMELINE_TYPE_IMAGE:
                vh.imageContent.setVisibility(View.VISIBLE);
                vh.imageContent.setImageBitmap(timeline.getImage());
                vh.textContent.setVisibility(View.GONE);
                break;
            case Timeline.TIMELINE_TYPE_TEXT_AND_IMAGE:
                vh.textContent.setVisibility(View.VISIBLE);
                vh.textContent.setText(timeline.getContent());
                vh.imageContent.setVisibility(View.VISIBLE);
                vh.imageContent.setImageBitmap(timeline.getImage());
                break;
        }

        // 좋아요를 눌렀는지에 따라서 색상 바꿔주기
        if(timeline.isLiked()) {
            vh.textButtonLike.setTextColor(ContextCompat.getColor(context, R.color.colorLightBlue));
            vh.imageButtonLike.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_like_blue));
        } else {
            vh.textButtonLike.setTextColor(ContextCompat.getColor(context, R.color.colorGreyBlue));
            vh.imageButtonLike.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_like_grey));
        }
    }

    @Override
    public int getItemViewType(int position) {
        // 가장 상단의 뷰(0번째, 1번째)를 만들기 위해 타입을 바꿔주기
        if(position == 0)
            return 0;
        else if (position == 1)
            return 1;
        else return 2;
    }

    @Override
    public int getItemCount() {
        // 가장 상단에 고정 뷰가 2개 있으므로 +2를 해주기
        return timelines.size() + 2;
    }


    // 상단 고정 뷰 전용 홀더
    public class TopViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TopViewHolder1(View v) {
            super(v);
            v.findViewById(R.id.button_camera).setOnClickListener(this);
            v.findViewById(R.id.button_my_story).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button_camera:
                    Toast.makeText(context, "카메라 버튼 클릭", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_my_story:
                    Toast.makeText(context, "내 스토리 버튼 클릭", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    // 상단 고정 뷰 전용 홀더
    public class TopViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TopViewHolder2(View v) {
            super(v);
            v.findViewById(R.id.button_write).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "글쓰기 버튼 클릭", Toast.LENGTH_SHORT).show();
        }
    }

    // 일반 타임라인 전용 홀더
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // 뷰 홀더의 데이터 위치
        int position = -1;
        TextView textWriter, textTime, textContent, textLike, textComment, textShare, textView;
        ImageView imageThumb, imageContent;
        TextView textButtonLike;
        ImageView imageButtonLike;

        public ViewHolder(View v) {
            super(v);
            textWriter = (TextView)v.findViewById(R.id.text_writer);
            textTime = (TextView)v.findViewById(R.id.text_time);
            textContent = (TextView)v.findViewById(R.id.text_content);
            textLike = (TextView)v.findViewById(R.id.text_like);
            textComment = (TextView)v.findViewById(R.id.text_comment);
            textShare = (TextView)v.findViewById(R.id.text_share);
            textView= (TextView)v.findViewById(R.id.text_view);
            imageThumb = (ImageView)v.findViewById(R.id.image_thumb);
            imageContent = (ImageView)v.findViewById(R.id.image_content);
            textButtonLike = (TextView)v.findViewById(R.id.button_like_text);
            imageButtonLike = (ImageView)v.findViewById(R.id.button_like_image);
            v.findViewById(R.id.button_like).setOnClickListener(this);
            v.findViewById(R.id.button_comment).setOnClickListener(this);
            v.findViewById(R.id.button_share).setOnClickListener(this);
            v.findViewById(R.id.button_more).setOnClickListener(this);
            textWriter.setOnClickListener(this);
            imageContent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button_like:
                    executeLike();
                    break;
                case R.id.button_comment:
                    Toast.makeText(context, "댓글 달기 버튼 클릭", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_share:
                    Toast.makeText(context, "공유하기 버튼 클릭", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_more:
                    BottomSheetDialog dialog = new BottomSheetDialog(context);
                    dialog.show();
                    break;
                case R.id.text_writer:
                    Toast.makeText(context, timelines.get(position).getWriter() + "님을 클릭하였습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.image_content:
                    Toast.makeText(context, timelines.get(position).getWriter() + "님의 사진을 클릭하였습니다.", Toast.LENGTH_SHORT).show();
            }
        }

        // 좋아요 버튼을 눌렀을때의 행동
        private void executeLike() {
            int like = timelines.get(position).getLike();
            // 이미 눌려져있었던 경우
            if(timelines.get(position).isLiked()) {
                like--;
                textButtonLike.setTextColor(ContextCompat.getColor(context, R.color.colorDarkGrey));
                imageButtonLike.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_like_grey));
                timelines.get(position).setLiked(false);
            }
            // 눌려있지 않은 경우
            else {
                like++;
                textButtonLike.setTextColor(ContextCompat.getColor(context, R.color.colorLightBlue));
                imageButtonLike.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_like_blue));
                timelines.get(position).setLiked(true);
            }
            // 좋아요 수 최신화
            textLike.setText(String.format("%d명", like));
            timelines.get(position).setLike(like);
        }
    }
}
