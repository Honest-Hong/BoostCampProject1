package com.project.boostcamp.staffdinnerrestraurant.ui.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public class ContactFragment extends Fragment {
    private static ContactFragment instance;

    public static ContactFragment getInstance() {
        if(instance == null) {
            instance = new ContactFragment();
        }
        return instance;
    }
}
