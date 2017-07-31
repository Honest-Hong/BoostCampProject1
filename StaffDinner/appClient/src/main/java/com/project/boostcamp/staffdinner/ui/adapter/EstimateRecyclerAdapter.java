package com.project.boostcamp.staffdinner.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.project.boostcamp.staffdinner.R;
import com.project.boostcamp.publiclibrary.data.Estimate;
import com.project.boostcamp.publiclibrary.data.OnEstimateClickListener;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class EstimateRecyclerAdapter extends RecyclerView.Adapter<EstimateVH> {
    private ArrayList<Estimate> estimates;
    private OnEstimateClickListener estimateClickListener;

    public EstimateRecyclerAdapter(OnEstimateClickListener estimateClickListener) {
        estimates = new ArrayList<>();
        this.estimateClickListener = estimateClickListener;
    }

    public void setEstimates(ArrayList<Estimate> estimates) {
        this.estimates = estimates;
        notifyDataSetChanged();
    }

    @Override
    public EstimateVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EstimateVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_estimate_item, parent, false), estimateClickListener);
    }

    @Override
    public void onBindViewHolder(EstimateVH holder, int position) {
        holder.setupView(estimates.get(position));
    }

    @Override
    public int getItemCount() {
        return estimates.size();
    }
}
