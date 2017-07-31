package com.project.boostcamp.publiclibrary.object;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Hong Tae Joon on 2017-07-28.
 */

public abstract class BaseVH<T> extends RecyclerView.ViewHolder {
    public BaseVH(View itemView) {
        super(itemView);
    }

    public abstract void setupView(T data);
}
