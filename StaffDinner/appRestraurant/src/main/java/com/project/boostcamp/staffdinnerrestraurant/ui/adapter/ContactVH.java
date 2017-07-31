package com.project.boostcamp.staffdinnerrestraurant.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.project.boostcamp.publiclibrary.data.Contact;
import com.project.boostcamp.publiclibrary.data.OnContactClickListener;
import com.project.boostcamp.publiclibrary.object.BaseVH;
import com.project.boostcamp.staffdinnerrestraurant.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class ContactVH extends BaseVH<Contact> implements View.OnClickListener {
    private Context context;
    @BindView(R.id.text_title) TextView textTitle;
    @BindView(R.id.text_sub_title) TextView textSubTitle;
    private Contact data;
    private OnClickItemListener<Contact> onClickItemListener;

    public ContactVH(View v, Context context, OnClickItemListener<Contact> onClickItemListener) {
        super(v);
        this.context = context;
        this.onClickItemListener = onClickItemListener;
        ButterKnife.bind(this, v);
        v.setOnClickListener(this);
    }

    @Override
    public void setupView(Contact data) {
        this.data = data;
        textTitle.setText(context.getString(R.string.text_contact_list_title, data.getEstimaterName(), data.getApplierName()));
        textSubTitle.setText(context.getString(R.string.text_contact_list_subtitle, data.getApplyDate(), data.getEstimateDate()));
    }

    @Override
    public void onClick(View view) {
        onClickItemListener.onClickItem(data);
    }
}
