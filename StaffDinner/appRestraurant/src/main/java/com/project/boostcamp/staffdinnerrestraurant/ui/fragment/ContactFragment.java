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

import com.project.boostcamp.publiclibrary.data.Contact;
import com.project.boostcamp.publiclibrary.data.DataEvent;
import com.project.boostcamp.staffdinnerrestraurant.R;
import com.project.boostcamp.staffdinnerrestraurant.ui.activity.ContactDetailActivity;
import com.project.boostcamp.staffdinnerrestraurant.ui.adapter.ContactAdapter;
import com.project.boostcamp.staffdinnerrestraurant.ui.adapter.OnClickItemListener;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public class ContactFragment extends Fragment {
    private static ContactFragment instance;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;

    public static ContactFragment getInstance() {
        if(instance == null) {
            instance = new ContactFragment();
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
        adapter = new ContactAdapter(getContext(), dataEvent);
        recyclerView.setAdapter(adapter);
    }

    private DataEvent<Contact> dataEvent = new DataEvent<Contact>() {
        @Override
        public void onClick(Contact data) {

        }
    };

    private void loadData() {
    }
}
