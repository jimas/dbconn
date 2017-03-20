package com.jimas.dbconn.pojo;

import java.util.Arrays;

public class SeriesData {

    private String legend;

    private String[] series;

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    public String[] getSeries() {
        return series;
    }

    public void setSeries(String[] series) {
        this.series = series;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SeriesData [");
        if (legend != null) builder.append("legend=").append(legend).append(", ");
        if (series != null) builder.append("series=").append(Arrays.toString(series));
        builder.append("]");
        return builder.toString();
    }

}