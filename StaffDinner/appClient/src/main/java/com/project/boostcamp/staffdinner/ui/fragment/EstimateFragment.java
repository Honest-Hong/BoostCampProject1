package com.project.boostcamp.staffdinner.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.boostcamp.staffdinner.R;
import com.project.boostcamp.publiclibrary.model.Estimate;
import com.project.boostcamp.publiclibrary.model.OnEstimateClickListener;
import com.project.boostcamp.publiclibrary.model.TestModel;
import com.project.boostcamp.staffdinner.ui.adapter.EstimateRecyclerAdapter;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class EstimateFragment extends Fragment {
    private RecyclerView recyclerView;
    private EstimateRecyclerAdapter adapter;
    private OnEstimateClickListener estimateClickListener;

    public static EstimateFragment newInstance() {
        EstimateFragment fragment = new EstimateFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        estimateClickListener = (OnEstimateClickListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_estimate, container, false);
        setupView(v);
        return v;
    }

    private void setupView(View v) {
        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EstimateRecyclerAdapter(estimateClickListener);
        adapter.setEstimates(TestModel.getEstimates());
        recyclerView.setAdapter(adapter);
    }
}
