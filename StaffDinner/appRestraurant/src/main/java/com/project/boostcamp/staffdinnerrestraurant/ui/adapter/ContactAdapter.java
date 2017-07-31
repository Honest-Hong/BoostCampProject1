package com.project.boostcamp.staffdinnerrestraurant.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.project.boostcamp.publiclibrary.data.Contact;
import com.project.boostcamp.publiclibrary.data.OnContactClickListener;
import com.project.boostcamp.staffdinnerrestraurant.R;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactVH> {
    private ArrayList<Contact> data;
    private OnClickItemListener<Contact> onClickItemListener;

    public ContactAdapter(OnClickItemListener<Contact> onClickItemListener) {
        data = new ArrayList<>();
        this.onClickItemListener = onClickItemListener;
    }

    public void setData(ArrayList<Contact> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ContactVH onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new ContactVH(LayoutInflater.from(context).inflate(R.layout.layout_contact_item, parent, false), context, onClickItemListener);
    }

    @Override
    public void onBindViewHolder(ContactVH holder, int position) {
        holder.setupView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
