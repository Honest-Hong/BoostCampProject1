package com.project.boostcamp.staffdinnerrestraurant.ui.adapter;

import android.content.Context;
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
    private Context context;
    private AdminApplication data;
    private ImageView imageView;
    private TextView textName;
    private TextView textTitle;
    private TextView textContent;

    public ApplicationVH(View v, final OnClickItemListener<AdminApplication> onClickItemListener) {
        super(v);
        context = v.getContext();
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
        textName.setText(context.getString(R.string.text_application_name, data.getWriterName()));
        textTitle.setText(data.getTitle());
        String time = TimeHelper.getTimeString(data.getTime(), "HH시 mm분");
        textContent.setText(context.getString(R.string.text_apply_content, data.getNumber(), time, String.format("%.2f", data.getDistance())));
    }
}
