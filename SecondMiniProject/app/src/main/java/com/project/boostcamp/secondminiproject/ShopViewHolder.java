package com.project.boostcamp.secondminiproject;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hong Tae Joon on 2017-07-11.
 */

public class ShopViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_view) ImageView imageView; // 상점 이미지
    @BindView(R.id.text_name) TextView textName; // 상점 이름
    @BindView(R.id.image_check) ImageView imageCheck; // 체크 이미지
    @BindView(R.id.text_text) TextView textText; // 상점 설명
    private Shop shop; // 상점 데이터

    public ShopViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void initLayout(Shop shop) {
        this.shop = shop;
        imageView.setImageResource(shop.getImage());
        textName.setText(shop.getName());
        textText.setText(shop.getText());
        setupCheckImage();
    }

    private void setupCheckImage() {
        if(shop.isChecked()) {
            imageCheck.setImageResource(R.drawable.ic_checked_true);
        } else {
            imageCheck.setImageResource(R.drawable.ic_checked_false);
        }
    }

    @OnClick(R.id.image_check)
    public void onClick() {
        shop.setChecked(!shop.isChecked());
        setupCheckImage();
    }
}
