package com.project.boostcamp.staffdinnerrestraurant.ui.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public class EstimateFragment extends Fragment {
    private static EstimateFragment instance;

    public static EstimateFragment getInstance() {
        if(instance == null) {
            instance = new EstimateFragment();
        }
        return instance;
    }
}
