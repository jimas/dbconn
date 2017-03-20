package com.jimas.dbconn.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Description eChart 图表 数据对象
 * @author weqinjia.liu
 * @Date 2017年3月20日
 */
public class EChartPojo implements Serializable {

    private static final long serialVersionUID = 66327962660547704L;

    private String[] axisCategory;// 图表 横轴 分类

    private String[] legends;// 图例

    private List<SeriesData> seriesDatas;//series  value 

    public String[] getAxisCategory() {
        return axisCategory;
    }

    public void setAxisCategory(String[] axisCategory) {
        this.axisCategory = axisCategory;
    }

    public String[] getLegends() {
        return legends;
    }

    public void setLegends(String[] legends) {
        this.legends = legends;
    }


    public List<SeriesData> getSeriesDatas() {
        return seriesDatas;
    }

    public void setSeriesDatas(List<SeriesData> seriesDatas) {
        this.seriesDatas = seriesDatas;
    }


    

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("eChartPojo [");
        if (axisCategory != null) builder.append("axisCategory=").append(Arrays.toString(axisCategory)).append(", ");
        if (legends != null) builder.append("legends=").append(Arrays.toString(legends)).append(", ");
        if (seriesDatas != null) builder.append("seriesDatas=").append(seriesDatas);
        builder.append("]");
        return builder.toString();
    }

    
    
}
