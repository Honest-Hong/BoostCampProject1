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
import com.project.boostcamp.publiclibrary.data.OnContactClickListener;
import com.project.boostcamp.staffdinner.ui.adapter.ContactRecyclerAdapter;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class ContactFragment extends Fragment {
    private RecyclerView recyclerView;
    private ContactRecyclerAdapter adapter;
    private OnContactClickListener contactClickListener;

    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        contactClickListener = (OnContactClickListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        setupView(v);
        // TODO: 2017-07-31 로컬로 저장하고 맵은 사진으로 임시 저장하도록
        return v;
    }

    private void setupView(View v) {
        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ContactRecyclerAdapter(contactClickListener);
        recyclerView.setAdapter(adapter);
    }
}
