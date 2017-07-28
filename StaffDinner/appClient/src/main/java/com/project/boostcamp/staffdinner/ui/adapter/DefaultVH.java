package com.project.boostcamp.staffdinner.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public abstract class DefaultVH<T> extends RecyclerView.ViewHolder {
    public DefaultVH(View itemView) {
        super(itemView);
    }

    public abstract void setupView(T data);
}
