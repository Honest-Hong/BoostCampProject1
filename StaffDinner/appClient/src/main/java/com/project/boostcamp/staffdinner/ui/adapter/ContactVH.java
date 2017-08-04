package com.project.boostcamp.staffdinner.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.project.boostcamp.publiclibrary.data.DataEvent;
import com.project.boostcamp.publiclibrary.object.BaseVH;
import com.project.boostcamp.staffdinner.R;
import com.project.boostcamp.publiclibrary.data.Contact;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class ContactVH extends BaseVH<Contact> implements View.OnClickListener {
    private Context context;
    private TextView textTitle;
    private TextView textSubTitle;

    public ContactVH(View itemView, DataEvent<Contact> dataEvent, Context context) {
        super(itemView, dataEvent);
        this.context = context;
    }

    @Override
    public void setupView(Contact data) {
        this.data = data;
        textTitle.setText(context.getString(R.string.text_contact_list_title, data.getApplierName(), data.getEstimaterName()));
        textSubTitle.setText(context.getString(R.string.text_contact_list_subtitle, data.getApplyDate(), data.getEstimateDate()));
    }

    @Override
    public void onClick(View view) {
        dataEvent.onClick(data);
    }
}
