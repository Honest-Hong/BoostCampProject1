package com.project.boostcamp.firstminiproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.boostcamp.firstminiproject.R;

/**
 * Created by Hong Tae Joon on 2017-07-03.
 */

public class MoreFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View cv = inflater.inflate(R.layout.fragment_more, container, false);
        return cv;
    }
}
