package com.project.boostcamp.staffdinnerrestraurant.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.boostcamp.publiclibrary.data.AdminApplication;
import com.project.boostcamp.publiclibrary.data.Application;
import com.project.boostcamp.publiclibrary.data.ApplyWithClient;
import com.project.boostcamp.publiclibrary.object.BaseVH;
import com.project.boostcamp.publiclibrary.util.TimeHelper;
import com.project.boostcamp.staffdinnerrestraurant.R;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public class ApplicationVH extends BaseVH<AdminApplication> {
    private AdminApplication data;
    private ImageView imageView;
    private TextView textName;
    private TextView textTitle;
    private TextView textContent;

    public ApplicationVH(View v, final OnClickItemListener<AdminApplication> onClickItemListener) {
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
    public void setupView(AdminApplication data) {
        this.data = data;
        textName.setText(data.getWriterName());
        textTitle.setText(data.getTitle());
        String time = TimeHelper.getTimeString(data.getTime(), "hh시 MM분");
        textContent.setText(textName.getContext().getString(R.string.text_apply_content, data.getNumber(), time, String.format("%.1f", data.getDistance())));
    }
}
