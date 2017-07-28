package com.project.boostcamp.staffdinner.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.boostcamp.staffdinner.R;
import com.project.boostcamp.publiclibrary.wheelpicker.WheelPickerAdapter;

import java.util.Locale;

/**
 * Created by Hong Tae Joon on 2017-07-27.
 */

public class TextWheelAdapter extends WheelPickerAdapter<Integer> {
    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent, int pos) {
        TextView textView = (TextView)inflater.inflate(R.layout.layout_wheel_item, parent, false);
        textView.setText(String.format(Locale.getDefault(), "%02d", data.get(pos)));
        return textView;
    }
}
