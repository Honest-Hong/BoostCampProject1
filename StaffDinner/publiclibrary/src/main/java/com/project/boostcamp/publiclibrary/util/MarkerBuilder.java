package com.project.boostcamp.publiclibrary.util;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.boostcamp.publiclibrary.R;

/**
 * Created by Hong Tae Joon on 2017-07-26.
 */

public class MarkerBuilder {
    @NonNull
    public static MarkerOptions simple(LatLng latLng) {
        return new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_place_red));
    }
}
