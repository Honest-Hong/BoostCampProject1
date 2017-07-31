package com.project.boostcamp.staffdinnerrestraurant.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.boostcamp.publiclibrary.data.Apply;
import com.project.boostcamp.publiclibrary.data.TestModel;
import com.project.boostcamp.staffdinnerrestraurant.R;
import com.project.boostcamp.staffdinnerrestraurant.ui.activity.ApplyDetailActivity;
import com.project.boostcamp.staffdinnerrestraurant.ui.adapter.ApplyAdapter;
import com.project.boostcamp.staffdinnerrestraurant.ui.adapter.OnClickItemListener;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public class ApplicationFragment extends Fragment implements OnClickItemListener<Apply> {
    private static ApplicationFragment instance;
    private RecyclerView recyclerView;
    private ApplyAdapter adapter;

    public static ApplicationFragment getInstance() {
        if(instance == null) {
            instance = new ApplicationFragment();
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
        adapter = new ApplyAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void loadData() {
        adapter.setData(TestModel.getApplies());
    }

    @Override
    public void onClickItem(Apply data) {
        Intent intent = new Intent(getContext(), ApplyDetailActivity.class);
        intent.putExtra(Apply.class.getName(), data);
        startActivity(intent);
    }
}
