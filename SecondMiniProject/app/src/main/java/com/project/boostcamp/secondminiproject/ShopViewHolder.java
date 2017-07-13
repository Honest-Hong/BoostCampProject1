package com.project.boostcamp.secondminiproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.module.AppGlideModule;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by Hong Tae Joon on 2017-07-11.
 */

public class ShopViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_view) ImageView imageView; // 상점 이미지
    @BindView(R.id.text_name) TextView textName; // 상점 이름
    @BindView(R.id.check_box) CheckBox checkBox; // 체크 박스
    @BindView(R.id.text_text) TextView textText; // 상점 설명
    private Shop shop; // 상점 데이터

    public ShopViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void initLayout(Context context, Shop shop) {
        this.shop = shop;
        GlideApp.with(context)
                .load(shop.getImageUrl())
                .fitCenter()
                .placeholder(R.drawable.place_holder)
                .into(imageView);
        textName.setText(shop.getName());
        textText.setText(shop.getText());
        checkBox.setChecked(shop.isChecked());
    }

    @OnCheckedChanged(R.id.check_box)
    public void onCheckedChanged(boolean checked) {
        shop.setChecked(checked);
    }
}
