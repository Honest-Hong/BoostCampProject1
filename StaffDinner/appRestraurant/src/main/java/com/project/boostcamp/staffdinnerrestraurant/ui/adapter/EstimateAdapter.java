package com.project.boostcamp.staffdinnerrestraurant.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.project.boostcamp.publiclibrary.data.Estimate;
import com.project.boostcamp.staffdinnerrestraurant.R;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public class EstimateAdapter extends RecyclerView.Adapter<EstimateVH> {
    private ArrayList<Estimate> data;
    private OnClickItemListener<Estimate> onClickItemListener;

    public EstimateAdapter(OnClickItemListener<Estimate> onClickItemListener) {
        data = new ArrayList<>();
        this.onClickItemListener = onClickItemListener;
    }

    public void setData(ArrayList<Estimate> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public EstimateVH onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new EstimateVH(context, LayoutInflater.from(context).inflate(R.layout.layout_estimate_item, parent, false), onClickItemListener);
    }

    @Override
    public void onBindViewHolder(EstimateVH holder, int position) {
        holder.setupView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
