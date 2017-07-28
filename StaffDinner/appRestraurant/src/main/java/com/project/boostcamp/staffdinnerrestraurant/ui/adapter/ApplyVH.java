package com.project.boostcamp.staffdinnerrestraurant.ui.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.boostcamp.publiclibrary.model.Apply;
import com.project.boostcamp.staffdinnerrestraurant.R;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public class ApplyVH extends BaseVH<Apply> {
    private Apply data;
    private ImageView imageView;
    private TextView textName;
    private TextView textTitle;
    private TextView textContent;

    public ApplyVH(View v, final OnClickItemListener<Apply> onClickItemListener) {
        super(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemListener.onClickItem(data);
            }
        });
        imageView = (ImageView)v.findViewById(R.id.image_view);
        textName = (TextView)v.findViewById(R.id.text_name);
        textTitle = (TextView)v.findViewById(R.id.text_title);
        textContent = (TextView)v.findViewById(R.id.text_content);
    }

    @Override
    public void setupView(Apply data) {
        this.data = data;
        textName.setText(data.getWriterName());
        textTitle.setText(data.getTitle());
        textContent.setText(textName.getContext().getString(R.string.text_apply_content, data.getNumber(), data.getWantedTime() + "", data.getWantedLatitude() + "," + data.getWantedLongitude()));
    }
}
