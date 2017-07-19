package com.project.boostcamp.thirdminiproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-19.
 */

public class RestraurantAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Restraurant> restraurants;

    public RestraurantAdapter(Context context) {
        this.context = context;
        this.restraurants = new ArrayList<>();
    }

    public void setRestraurants(ArrayList<Restraurant> restraurants) {
        this.restraurants = restraurants;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RestraurantViewHolder(
                LayoutInflater.from(context)
                        .inflate(R.layout.view_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RestraurantViewHolder)holder).initLayout(restraurants.get(position));
    }

    @Override
    public int getItemCount() {
        return restraurants.size();
    }
}
