package com.project.boostcamp.publiclibrary.domain;

import com.project.boostcamp.publiclibrary.data.Geo;

/**
 * Created by Hong Tae Joon on 2017-08-03.
 */

public class GeoDTO {
    private String type;
    private double[] coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public Geo toGeo() {
        Geo g = new Geo();
        g.setCoordinates(coordinates);
        g.setType(type);
        return g;
    }
}
