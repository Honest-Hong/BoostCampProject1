package com.project.boostcamp.staffdinnerrestraurant.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.boostcamp.publiclibrary.data.Estimate;
import com.project.boostcamp.staffdinnerrestraurant.R;
import com.project.boostcamp.staffdinnerrestraurant.ui.adapter.EstimateAdapter;
import com.project.boostcamp.staffdinnerrestraurant.ui.adapter.OnClickItemListener;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public class EstimateFragment extends Fragment implements OnClickItemListener<Estimate> {
    private static EstimateFragment instance;
    private RecyclerView recyclerView;
    private EstimateAdapter adapter;

    public static EstimateFragment getInstance() {
        if(instance == null) {
            instance = new EstimateFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout, container, false);
        setupView(v);
        loadData();
        return v;
    }

    private void setupView(View v) {
        recyclerView = (RecyclerView)v;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EstimateAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void loadData() {
    }

    @Override
    public void onClickItem(Estimate data) {

    }
}
