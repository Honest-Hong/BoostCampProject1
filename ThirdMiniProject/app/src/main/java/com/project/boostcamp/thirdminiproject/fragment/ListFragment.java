package com.project.boostcamp.thirdminiproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.boostcamp.thirdminiproject.R;
import com.project.boostcamp.thirdminiproject.Restraurant;
import com.project.boostcamp.thirdminiproject.RestraurantAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hong Tae Joon on 2017-07-19.
 */

public class ListFragment extends Fragment implements ValueEventListener {
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private RestraurantAdapter restraurantAdapter;
    private ArrayList<Restraurant> restraurants;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFirebaseDatabase();
        restraurantAdapter = new RestraurantAdapter(getContext());
        restraurants = new ArrayList<>();
        restraurantAdapter.setRestraurants(restraurants);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View cv = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, cv);
        initRecyclerView();
        return cv;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(restraurantAdapter);
    }

    private void initFirebaseDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("restraurant");
        ref.addValueEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // 변화한 맛집 데이터 받아오기
        restraurants = new ArrayList<>();
        for(DataSnapshot data: dataSnapshot.getChildren()) {
            restraurants.add(data.getValue(Restraurant.class));
        }
        restraurantAdapter.setRestraurants(restraurants);
        restraurantAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
    }
}
