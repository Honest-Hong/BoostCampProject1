package com.project.boostcamp.staffdinner.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.project.boostcamp.staffdinner.R;
import com.project.boostcamp.publiclibrary.model.Contact;
import com.project.boostcamp.publiclibrary.model.OnContactClickListener;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class ContactVH extends DefaultVH<Contact> implements View.OnClickListener {
    private Context context;
    private TextView textTitle;
    private TextView textSubTitle;
    private Contact data;
    private OnContactClickListener contactClickListener;

    public ContactVH(View v, Context context, OnContactClickListener contactClickListener) {
        super(v);
        this.context = context;
        this.contactClickListener = contactClickListener;
        textTitle = (TextView)v.findViewById(R.id.text_title);
        textSubTitle = (TextView)v.findViewById(R.id.text_sub_title);
        v.setOnClickListener(this);
    }

    @Override
    public void setupView(Contact data) {
        this.data = data;
        textTitle.setText(context.getString(R.string.text_contact_title, data.getApplierName(), data.getEstimaterName()));
        textSubTitle.setText(context.getString(R.string.text_contact_subtitle, data.getApplyDate(), data.getEstimateDate()));
    }

    @Override
    public void onClick(View view) {
        contactClickListener.onContactClick(data);
    }
}
