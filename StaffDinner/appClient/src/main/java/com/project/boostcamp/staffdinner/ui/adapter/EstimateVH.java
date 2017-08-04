package com.project.boostcamp.staffdinner.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.boostcamp.publiclibrary.data.DataEvent;
import com.project.boostcamp.publiclibrary.domain.ClientEstimateDTO;
import com.project.boostcamp.publiclibrary.object.BaseVH;
import com.project.boostcamp.publiclibrary.util.StringHelper;
import com.project.boostcamp.publiclibrary.util.TimeHelper;
import com.project.boostcamp.staffdinner.GlideApp;
import com.project.boostcamp.staffdinner.R;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class EstimateVH extends BaseVH<ClientEstimateDTO> implements View.OnClickListener{
    private Context context;
    private ImageView imageView;
    private TextView textName;
    private TextView textMessage;
    private TextView textDate;

    public EstimateVH(View v, DataEvent<ClientEstimateDTO> dataEvent, Context context) {
        super(v, dataEvent);
        this.context = context;
        imageView = (ImageView)v.findViewById(R.id.image_view);
        textName = (TextView)v.findViewById(R.id.text_name);
        textMessage = (TextView)v.findViewById(R.id.text_message);
        textDate = (TextView)v.findViewById(R.id.text_date);
        v.setOnClickListener(this);
    }

    @Override
    public void setupView(ClientEstimateDTO data) {
        this.data = data;
        GlideApp.with(imageView.getContext())
                .load(data.getAdmin().getImage())
                .centerCrop()
                .into(imageView);
        textName.setText(data.getAdmin().getName());
        textDate.setText(TimeHelper.getTimeString(data.getWritedTime(), context.getString(R.string.default_time)));
        textMessage.setText(StringHelper.cutEnd(data.getMessage(), 30));
    }

    @Override
    public void onClick(View view) {
        dataEvent.onClick(data);
    }
}
