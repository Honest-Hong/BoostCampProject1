package com.project.boostcamp.staffdinnerrestraurant.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.project.boostcamp.publiclibrary.data.AdminApplication;
import com.project.boostcamp.staffdinnerrestraurant.R;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationVH> {
    private ArrayList<AdminApplication> data;
    private OnClickItemListener<AdminApplication> onClickItemListener;

    public ApplicationAdapter(OnClickItemListener<AdminApplication> onClickItemListener) {
        data = new ArrayList<>();
        this.onClickItemListener = onClickItemListener;
    }

    public void setData(ArrayList<AdminApplication> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ApplicationVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ApplicationVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_apply_item, parent, false), onClickItemListener);
    }

    @Override
    public void onBindViewHolder(ApplicationVH holder, int position) {
        holder.setupView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
