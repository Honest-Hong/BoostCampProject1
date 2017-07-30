package com.project.boostcamp.staffdinner.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.boostcamp.publiclibrary.util.StringHelper;
import com.project.boostcamp.staffdinner.GlideApp;
import com.project.boostcamp.staffdinner.R;
import com.project.boostcamp.publiclibrary.model.Estimate;
import com.project.boostcamp.publiclibrary.model.OnEstimateClickListener;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class EstimateVH extends DefaultVH<Estimate> implements View.OnClickListener{
    private ImageView imageView;
    private TextView textName;
    private TextView textMessage;
    private TextView textDate;
    private Estimate data;
    private OnEstimateClickListener estimateClickListener;

    public EstimateVH(View v, OnEstimateClickListener estimateClickListener) {
        super(v);
        this.estimateClickListener = estimateClickListener;
        imageView = (ImageView)v.findViewById(R.id.image_view);
        textName = (TextView)v.findViewById(R.id.text_name);
        textMessage = (TextView)v.findViewById(R.id.text_message);
        textDate = (TextView)v.findViewById(R.id.text_date);
        v.setOnClickListener(this);
    }

    @Override
    public void setupView(Estimate data) {
        this.data = data;
        GlideApp.with(imageView.getContext())
                .load(data.getRestImgUrl())
                .centerCrop()
                .into(imageView);
        textName.setText(data.getRestName());
        textDate.setText(data.getSendDate());
        textMessage.setText(StringHelper.cutEnd(data.getMessage(), 30));
    }

    @Override
    public void onClick(View view) {
        estimateClickListener.onEstimateClick(data);
    }
}
