package com.project.boostcamp.staffdinnerrestraurant.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.project.boostcamp.publiclibrary.data.Apply;
import com.project.boostcamp.staffdinnerrestraurant.R;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public class ApplyAdapter extends RecyclerView.Adapter<ApplyVH> {
    private ArrayList<Apply> data;
    private OnClickItemListener<Apply> onClickItemListener;

    public ApplyAdapter(OnClickItemListener<Apply> onClickItemListener) {
        data = new ArrayList<>();
        this.onClickItemListener = onClickItemListener;
    }

    public void setData(ArrayList<Apply> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ApplyVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ApplyVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_apply_item, parent, false), onClickItemListener);
    }

    @Override
    public void onBindViewHolder(ApplyVH holder, int position) {
        holder.setupView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}