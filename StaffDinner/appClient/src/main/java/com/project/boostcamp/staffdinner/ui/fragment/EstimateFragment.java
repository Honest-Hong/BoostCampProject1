package com.project.boostcamp.staffdinner.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.boostcamp.publiclibrary.api.DataReceiver;
import com.project.boostcamp.publiclibrary.api.RetrofitClient;
import com.project.boostcamp.publiclibrary.data.DataEvent;
import com.project.boostcamp.publiclibrary.domain.ClientEstimateDTO;
import com.project.boostcamp.publiclibrary.util.SharedPreperenceHelper;
import com.project.boostcamp.staffdinner.R;
import com.project.boostcamp.staffdinner.ui.activity.EstimateDetailActivity;
import com.project.boostcamp.staffdinner.ui.adapter.EstimateRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class EstimateFragment extends Fragment {
    private RecyclerView recyclerView;
    private EstimateRecyclerAdapter adapter;

    public static EstimateFragment newInstance() {
        EstimateFragment fragment = new EstimateFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_estimate, container, false);
        setupView(v);
        loadData();
        return v;
    }

    private void setupView(View v) {
        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EstimateRecyclerAdapter(getContext(), dataEvent);
        recyclerView.setAdapter(adapter);
    }

    private void loadData() {
        String clientId = SharedPreperenceHelper.getInstance(getContext()).getLoginId();
        RetrofitClient.getInstance().getEstimateList(clientId, dataReceiver);
    }

    private DataEvent<ClientEstimateDTO> dataEvent = new DataEvent<ClientEstimateDTO>() {
        @Override
        public void onClick(ClientEstimateDTO data) {
            Intent intent = new Intent(getContext(), EstimateDetailActivity.class);
            intent.putExtra(ClientEstimateDTO.class.getName(), data);
            startActivity(intent);
        }
    };

    private DataReceiver<ArrayList<ClientEstimateDTO>> dataReceiver = new DataReceiver<ArrayList<ClientEstimateDTO>>() {
        @Override
        public void onReceive(ArrayList<ClientEstimateDTO> data) {
            if(data == null) {
                data = new ArrayList<>();
            }
            adapter.setData(data);
        }

        @Override
        public void onFail() {

        }
    };
}
