package com.project.boostcamp.staffdinnerrestraurant.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.boostcamp.publiclibrary.api.RetrofitAdmin;
import com.project.boostcamp.publiclibrary.data.Admin;
import com.project.boostcamp.publiclibrary.data.Apply;
import com.project.boostcamp.publiclibrary.data.ApplyWithClient;
import com.project.boostcamp.publiclibrary.util.SharedPreperenceHelper;
import com.project.boostcamp.staffdinnerrestraurant.R;
import com.project.boostcamp.staffdinnerrestraurant.ui.activity.ApplyDetailActivity;
import com.project.boostcamp.staffdinnerrestraurant.ui.adapter.ApplyAdapter;
import com.project.boostcamp.staffdinnerrestraurant.ui.adapter.OnClickItemListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public class ApplicationFragment extends Fragment implements OnClickItemListener<ApplyWithClient> {
    private static final float MAX_DISTANCE = 2.0f;
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
        // TODO: 2017-08-02 서버로부터 데이터 불러오기 (근접한 신청서)
        Admin admin = SharedPreperenceHelper.getInstance(getContext()).getAdmin();
        RetrofitAdmin.getInstance().adminService.get(admin.getGeo().getCoordinates()[1], admin.getGeo().getCoordinates()[0], MAX_DISTANCE).enqueue(new Callback<List<ApplyWithClient>>() {
            @Override
            public void onResponse(Call<List<ApplyWithClient>> call, Response<List<ApplyWithClient>> response) {
                Log.d("HTJ", "ApplicationFragment-loadData-onResponse: " + response.body());
                adapter.setData((ArrayList<ApplyWithClient>)response.body());
            }

            @Override
            public void onFailure(Call<List<ApplyWithClient>> call, Throwable t) {
                Log.e("HTJ", "ApplicationFragment-loadData-onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onClickItem(ApplyWithClient data) {
        Intent intent = new Intent(getContext(), ApplyDetailActivity.class);
        intent.putExtra(ApplyWithClient.class.getName(), data);
        startActivity(intent);
    }
}
