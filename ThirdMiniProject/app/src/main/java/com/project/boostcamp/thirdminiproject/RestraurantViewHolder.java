package com.project.boostcamp.thirdminiproject;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hong Tae Joon on 2017-07-19.
 */

public class RestraurantViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_name) TextView textName;
    @BindView(R.id.text_address) TextView textAddress;
    @BindView(R.id.text_tele) TextView textTele;
    @BindView(R.id.text_desc) TextView textDesc;

    public RestraurantViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void initLayout(Restraurant item) {
        textName.setText(item.getName());
        textAddress.setText(item.getAddress());
        textTele.setText(item.getTele());
        textDesc.setText(item.getDesc());
    }
}
